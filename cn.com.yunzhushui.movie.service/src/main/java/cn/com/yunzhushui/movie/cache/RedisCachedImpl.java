package cn.com.yunzhushui.movie.cache;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月3日下午11:51:45
 **/
@Service("redisCached")
public class RedisCachedImpl implements IRedisCached{

	public RedisCachedImpl() {}

	// -1 - never expire
	private int expire=-1;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;


	@Override
    public String deleteCached(final byte[] sessionId) throws Exception {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
			public Long doInRedis(RedisConnection connection)throws DataAccessException {
                connection.del(sessionId);
                return null;
            }
        });
        return null;
    }

    @Override
    public String updateCached(final byte[] key, final byte[] session, final Long expireSec) throws Exception {
        return (String) redisTemplate.execute(new RedisCallback<Object>() {
            @Override
			public String doInRedis(final RedisConnection connection) throws DataAccessException {
                connection.set(key, session);
                if (expireSec != null) {
                    connection.expire(key, expireSec);
                } else {
                    connection.expire(key, expire);
                }
                return new String(key);
            }
        });
    }

    @Override
    public Object getCached(final byte[] sessionId) throws Exception {
        return redisTemplate.execute(new RedisCallback<Object>() {
            @Override
			public Object doInRedis(RedisConnection connection)throws DataAccessException {
                byte[] bs = connection.get(sessionId);
                return SerializeUtil.deserialize(bs);
            }
        });
    }

    @Override
    public Boolean updateHashCached(final byte[] key, final byte[] mapkey, final byte[] value, Long expire)throws Exception {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Boolean hSet = connection.hSet(key, mapkey, value);
                return hSet;
            }
        });
    }

    @Override
    public Object getHashCached(final byte[] key, final byte[] mapkey) throws Exception {
        return redisTemplate.execute(new RedisCallback<Object>() {
            @Override
			public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] hGet = connection.hGet(key, mapkey);
                return SerializeUtil.deserialize(hGet);
            }
        });
    }

    @Override
    public Long deleteHashCached(final byte[] key, final byte[] mapkey) throws Exception {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
			public Long doInRedis(RedisConnection connection)throws DataAccessException {
                Long hDel = connection.hDel(key, mapkey);
                return hDel;
            }
        });
    }

    @Override
    public Long getHashSize(final byte[] key) throws Exception {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
			public Long doInRedis(RedisConnection connection)throws DataAccessException {
                Long len = connection.hLen(key);
                return len;
            }
        });
    }

    @Override
    public Long getDBSize() throws Exception {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Long len = connection.dbSize();
                return len;
            }
        });
    }

    @Override
    public void clearDB() throws Exception {
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return null;
            }
        });
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    @Override
    public List<?> getHashValues(final byte[] key) throws Exception {
        return redisTemplate.execute(new RedisCallback<List>() {
            @Override
			public List doInRedis(RedisConnection connection)throws DataAccessException {
                List<byte[]> hVals = connection.hVals(key);
                if (hVals == null || hVals.size() < 1) {
                    return null;
                }
                List list = new ArrayList();
                for (byte[] bs : hVals) {
                    list.add(SerializeUtil.deserialize(bs));
                }
                return list;
            }
        });
    }

	@Override
	public String deleteStringCached(final String key) throws Exception {
		 redisTemplate.execute(new RedisCallback<Object>() {
	            @Override
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
	                connection.del(key.getBytes());
	                return null;
	            }
	        });
	        return null;
	}

	@Override
	public String updateStringCached(final String key,final String value, final Long expireSec)throws Exception {
		 return (String) redisTemplate.execute(new RedisCallback<Object>() {
	            @Override
				public String doInRedis(final RedisConnection connection) throws DataAccessException {
	                connection.set(key.getBytes(), SerializeUtil.serialize(value.getBytes()));
	                if (expireSec != null) {
	                    connection.expire(key.getBytes(), expireSec);
	                } else {
	                    connection.expire(key.getBytes(), expire);
	                }
	                return key;
	            }
	        });
	}

	@Override
	public String getStringCached(final String key) throws Exception {
	  return redisTemplate.execute(new RedisCallback<String>() {
          @Override
		public String doInRedis(RedisConnection connection)throws DataAccessException {
              byte[] bs = connection.get(key.getBytes());
              return SerializeUtil.unserializeRetString(bs);
          }
      });
	}
}
