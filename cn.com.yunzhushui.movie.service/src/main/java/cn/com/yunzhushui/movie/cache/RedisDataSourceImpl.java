package cn.com.yunzhushui.movie.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月6日上午10:15:15
 **/
public class RedisDataSourceImpl implements RedisDataSource{

	private static final Logger logger = LoggerFactory.getLogger(RedisDataSourceImpl.class);
	
	@Autowired
    private ShardedJedisPool  shardedJedisPool;
	
	@Override
	public ShardedJedis getRedisClient() {
		try {
			return shardedJedisPool.getResource();
		} catch (Exception e) {
			logger.error("调用shardedJedisPool.getResource()出现异常，异常原因:",new Object[]{JSON.toJSONString(e)});
		}
		return null;
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.close();
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis, boolean broken) {
		 if (broken) {
	            shardedJedisPool.returnBrokenResource(shardedJedis);
	        } else {
	            shardedJedisPool.returnResource(shardedJedis);
	        }
	}
}
