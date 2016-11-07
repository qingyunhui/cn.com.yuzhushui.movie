package cn.com.yunzhushui.movie.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
public class RedisComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisComponent.class);
	
	private Long LOCK_EXPIRED_TIME = 5 * 1000L;// 分布式锁的失效时间： 5秒
	
	private ShardedJedisPool pool;
	
	public ShardedJedisPool getPool() {
		return pool;
	}
	
	public void setPool(ShardedJedisPool pool) {
		this.pool = pool;
	}
	
	/**
	 * 获取一个锁
	 * 
	 * @param lock
	 * @return
	 */
	public boolean acquireLock(String lock) {
		return acquireLock(lock, LOCK_EXPIRED_TIME);
	}
	
	/**
	 * 获取一个锁 必须保证分布式环境的多个主机的时钟是一致的
	 * 
	 * @param lockKey
	 * @expired 锁的失效时间（毫秒）
	 * @return
	 */
	public boolean acquireLock(String lockKey, long expired) {
		ShardedJedis jedis = null;
		boolean success = false;
		try {
			jedis = pool.getResource();
			long value = System.currentTimeMillis() + expired + 1;
			// 通过setnx获取一个lock
			long acquired = jedis.setnx(lockKey, String.valueOf(value));
			if (acquired == 1) {
				// setnx成功，则成功获取一个锁
				success = true;
			} else {
				// setnx失败，说明锁仍然被其他对象保持，检查其是否已经超时
				long oldValue = Long.valueOf(jedis.get(lockKey));
				if (oldValue < System.currentTimeMillis()) {
					// 超时
					String getValue = jedis.getSet(lockKey, String.valueOf(value));
					// 获取锁成功
					if (Long.valueOf(getValue) == oldValue) {
						success = true;
					} else {
						// 已被其他进程捷足先登了
						success = false;
					}
				} else {
					// 未超时，则直接返回失败
					success = false;
				}
			}
		}
		catch (Throwable e) {
			logger.error("acquireLock error", e);
		}
		finally {
			returnResource(jedis);
		}
		return success;
	}
	
	/**
	 * 释放锁
	 * 
	 * @param lockKey
	 *            key
	 */
	public void releaseLock(String lockKey) {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			long current = System.currentTimeMillis();
			// 避免删除非自己获取到的锁
			if (current < Long.valueOf(jedis.get(lockKey))) {
				jedis.del(lockKey);
			}
		}
		catch (Throwable e) {
			logger.error("releaseLock error", e);
		}
		finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 执行有返回结果的action。
	 */
	public <T> T execute(JedisAction<T> jedisAction) throws JedisException {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedisAction.action(jedis);
		}
		catch (JedisException e) {
			logger.error("", e);
			returnBrokenResource(jedis);
		}
		catch (Throwable e) {
			logger.error("", e);
			returnResource(jedis);
		}
		finally {
			returnResource(jedis);
		}
		return null;
	}
	
	/**
	 * 执行有返回结果,并且返回结果是List的action。
	 */
	public <T> List<T> executeForList(JedisActionForList<T> jedisAction) throws JedisException {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedisAction.action(jedis);
		}
		catch (JedisException e) {
			logger.error("", e);
			returnBrokenResource(jedis);
		}
		catch (Throwable e) {
			logger.error("", e);
			returnResource(jedis);
		}
		finally {
			returnResource(jedis);
		}
		return null;
	}
	
	/**
	 * 执行无返回结果的action。
	 */
	public void execute(JedisActionNoResult jedisAction) throws JedisException {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			jedisAction.action(jedis);
		}
		catch (JedisException e) {
			logger.error("", e);
			returnBrokenResource(jedis);
		}
		catch (Throwable e) {
			logger.error("", e);
			returnResource(jedis);
		}
		finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 有返回结果的回调接口定义。
	 */
	public interface JedisAction<T> {
		
		T action(ShardedJedis jedis);
	}
	
	/**
	 * 有返回结果的回调接口定义。
	 */
	public interface JedisActionForList<T> {
		
		List<T> action(ShardedJedis jedis);
	}
	
	/**
	 * 无返回结果的回调接口定义。
	 */
	public interface JedisActionNoResult {
		
		void action(ShardedJedis jedis);
	}
	
	/****************** 常用方法 *******************/
	
	/**
	 * 获取 key-value 的 value
	 */
	public <T> T get(final String key, final Class<T> c) {
		return this.execute(new JedisAction<T>() {
			
			@Override
			public T action(ShardedJedis jedis) {
				String value = jedis.get(key);
				return deserialization(value, c);
			}
		});
	}
	
	/**
	 * 获取 key-value 的 value. <br>
	 * 如果 value 是一个 list, 请使用此方法.
	 */
	public <T> List<T> getList(final String key, final Class<T> c) {
		return this.executeForList(new JedisActionForList<T>() {
			
			@Override
			public List<T> action(ShardedJedis jedis) {
				String value = jedis.get(key);
				return deserializationList(value, c);
			}
		});
	}
	
	/**
	 * 缓存 key-value
	 */
	public void set(final String key, final Object value) {
		this.execute(new JedisActionNoResult() {
			
			@Override
			public void action(ShardedJedis jedis) {
				jedis.set(key, serialzation(value));
			}
		});
	}
	
	/**
	 * 缓存 key-value , seconds 过期时间,单位为秒.
	 */
	public void set(final String key, final Object value, final int seconds) {
		this.execute(new JedisActionNoResult() {
			
			@Override
			public void action(ShardedJedis jedis) {
				jedis.setex(key, seconds, serialzation(value));
			}
		});
	}
	
	/**
	 * 获取 key mapKey mapValue 中的 mapValue 列表.
	 */
	public <T> List<T> hvals(final String key, final Class<T> c) {
		return this.executeForList(new JedisActionForList<T>() {
			
			@Override
			public List<T> action(ShardedJedis jedis) {
				Collection<String> value = jedis.hvals(key);
				List<T> list = new ArrayList<T>(value.size());
				for (String b : value) {
					list.add(deserialization(b, c));
				}
				return list;
			}
		});
	}
	
	/**
	 * 获取 key mapKey mapValue 中指定的 mapValue.
	 */
	public <T> T hget(final String key, final Object mapKey, final Class<T> c) {
		return this.execute(new JedisAction<T>() {
			
			@Override
			public T action(ShardedJedis jedis) {
				String value = jedis.hget(key, serialzation(mapKey));
				return deserialization(value, c);
			}
		});
	}
	
	/**
	 * 获取 key mapKey mapValue 中指定的 mapValue.<br>
	 * 如果 mapValue 是一个 list, 请使用此方法.
	 */
	public <T> List<T> hgetList(final String key, final Object mapKey, final Class<T> c) {
		return this.executeForList(new JedisActionForList<T>() {
			
			@Override
			public List<T> action(ShardedJedis jedis) {
				String value = jedis.hget(key, serialzation(mapKey));
				return deserializationList(value, c);
			}
		});
	}
	
	/**
	 * 缓存 key mapKey mapValue.
	 */
	public void hset(final String key, final Object mapKey, final Object mapValue) {
		this.execute(new JedisActionNoResult() {
			
			@Override
			public void action(ShardedJedis jedis) {
				jedis.hset(key, serialzation(mapKey), serialzation(mapValue));
			}
		});
	}
	
	public void hset(final String key, final Object mapKey, final Object mapValue, final int second) {
		this.execute(new JedisActionNoResult() {
			
			@Override
			public void action(ShardedJedis jedis) {
				jedis.hset(key, serialzation(mapKey), serialzation(mapValue));
				jedis.expire(key, second);
			}
		});
	}
	
	/**
	 * 删除集合中对应的key/value
	 */
	public void hdel(final String key, final Object mapKey) {
		this.execute(new JedisActionNoResult() {
			
			@Override
			public void action(ShardedJedis jedis) {
				jedis.hdel(key, serialzation(mapKey));
			}
		});
	}
	
	/**
	 * 缓存 key map<mapKey,mapValue>.
	 */
	public void hmset(final String key, final Map<Object, Object> map) {
		this.execute(new JedisActionNoResult() {
			
			@Override
			public void action(ShardedJedis jedis) {
				if (null!=map) {
					Map<String, String> m = new HashMap<String, String>(map.size());
					
					Iterator<Entry<Object, Object>> it = map.entrySet().iterator();
					while (it.hasNext()) {
						Entry<Object, Object> next = it.next();
						m.put(serialzation(next.getKey()), serialzation(next.getValue()));
					}
					jedis.hmset(key, m);
				}
			}
		});
	}
	
	/**
	 * 删除一个 Key.
	 */
	public Long del(final String key) {
		return this.execute(new JedisAction<Long>() {
			
			@Override
			public Long action(ShardedJedis jedis) {
				return jedis.del(key);
			}
		});
	}
	
	/**
	 * redis zadd command.
	 */
	public Long zadd(final String key, final double score, final Object member) {
		return this.execute(new JedisAction<Long>() {
			
			@Override
			public Long action(ShardedJedis jedis) {
				return jedis.zadd(key, score, serialzation(member));
			}
		});
	}
	
	/**
	 * redis zrange command.
	 */
	public <T> List<T> zrange(final String key, final long start, final long end, final Class<T> clazz) {
		return this.executeForList(new JedisActionForList<T>() {
			
			@Override
			public List<T> action(ShardedJedis jedis) {
				Collection<String> value = jedis.zrange(key, start, end);
				List<T> list = new ArrayList<T>(value.size());
				for (String b : value) {
					list.add(deserialization(b, clazz));
				}
				return list;
			}
		});
	}
	
	/**
	 * redis zrangeByScore command.
	 */
	public <T> List<T> zrangeByScore(final String key, final double min, final double max, final Class<T> clazz) {
		return this.executeForList(new JedisActionForList<T>() {
			
			@Override
			public List<T> action(ShardedJedis jedis) {
				Collection<String> value = jedis.zrangeByScore(key, min, max);
				List<T> list = new ArrayList<T>(value.size());
				for (String b : value) {
					list.add(deserialization(b, clazz));
				}
				return list;
			}
		});
	}
	
	/**
	 * redis zremrangeByScore command.
	 */
	public Long zremrangeByScore(final String key, final double start, final double end) {
		return this.execute(new JedisAction<Long>() {
			
			@Override
			public Long action(ShardedJedis jedis) {
				return jedis.zremrangeByScore(key, start, end);
			}
		});
	}
	
	/**
	 * redis incr command.
	 */
	public Long incr(final String key) {
		return this.execute(new JedisAction<Long>() {
			
			@Override
			public Long action(ShardedJedis jedis) {
				return jedis.incr(key);
			}
		});
	}
	
	/**
	 * redis incrby command.
	 */
	public Long incrBy(final String key, final long integer) {
		return this.execute(new JedisAction<Long>() {
			
			@Override
			public Long action(ShardedJedis jedis) {
				return jedis.incrBy(key, integer);
			}
		});
	}
	
	/**
	 * redis expire command.
	 */
	public Long expire(final String key, final int seconds) {
		return this.execute(new JedisAction<Long>() {
			
			@Override
			public Long action(ShardedJedis jedis) {
				return jedis.expire(key, seconds);
			}
		});
	}

    public Long expireAt(final String key, final long seconds) {
        return this.execute(new JedisAction<Long>() {

            @Override
            public Long action(ShardedJedis jedis) {
                return jedis.expireAt(key,seconds);
            }
        });
    }
	
	// private method
	// -----------------------------------------------------------------------
	private String serialzation(Object object) {
		if (object == null)
			return null;
		return JSONObject.toJSONString(object);
	}
	
	private <T> T deserialization(String json, Class<T> c) {
		if (json == null)
			return null;
		return JSON.parseObject(json, c);
	}
	
	private <E> List<E> deserializationList(String json, Class<E> element) {
		if (json == null)
			return null;
		return JSON.parseArray(json, element);
	}
	
	private void returnResource(ShardedJedis jedis) {
		// 返还到连接池
		if (jedis != null) {
			try {
				pool.returnResource(jedis);
			}
			catch (Throwable e) {
				returnBrokenResource(jedis);
			}
		}
	}
	
	private void returnBrokenResource(ShardedJedis jedis) {
		if (jedis != null) {
			try {
				pool.returnBrokenResource(jedis);
			}
			catch (Throwable e) {
				logger.error("", e);
			}
		}
	}
	
	@PreDestroy
	public void destory() {
		try {
			pool.destroy();
		}
		catch (Throwable e) {
			logger.error("", e);
		}
	}
}
