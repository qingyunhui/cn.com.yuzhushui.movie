package cn.com.yunzhushui.movie.cache;

import redis.clients.jedis.ShardedJedis;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月6日上午10:12:09
 **/
public interface RedisDataSource {
	
	/**
	 * <p>取得redis的客户端，可以执行命令</p>
	 * @return ShardedJedis
	 * */
	public abstract ShardedJedis getRedisClient();
	
	/**
	 * <p>将资源返还给pool</p>
	 * @param shardedJedis
	 * @return void
	 * */
    public void returnResource(ShardedJedis shardedJedis);
    
    /**
     * <p>出现异常后，将资源返还给pool （其实不需要第二个方法）</p>
     * @param shardedJedis
     * @param broken
     * */
    public void returnResource(ShardedJedis shardedJedis,boolean broken);

}
