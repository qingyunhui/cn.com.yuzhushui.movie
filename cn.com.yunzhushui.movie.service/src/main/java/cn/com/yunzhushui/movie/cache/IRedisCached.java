package cn.com.yunzhushui.movie.cache;

import java.util.List;
import java.util.Set;

/***
 ** @category redis组件要实现的接口...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月3日下午11:46:59
 **/
public interface IRedisCached {
	
	/**
     * 删除 缓存
     * @param key
     * @return 
     * @throws Exception
     */
    String deleteCached(byte[] key) throws Exception;

    /**
     * 更新 缓存
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    Object updateCached(byte[] key, byte[] value, Long expire) throws Exception;

    /**
     * 获取缓存
     *
     * @param key
     * @return
     * @throws Exception
     */
    Object getCached(byte[] key) throws Exception;

    /**
     * 根据 正则表达式key 获取 列表
     *
     * @param keys
     * @return
     * @throws Exception
     */
    Set<Object> getKeys(byte[] keys) throws Exception;

    /**
     * 根据 正则表达式key 获取 列表
     *
     * @param key
     * @return
     * @throws Exception
     */
    Set<Object> getHashKeys(byte[] key) throws Exception;


    /**
     * 更新 缓存
     *
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    Boolean updateHashCached(byte[] key, byte[] mapkey, byte[] value, Long expire) throws Exception;


    /**
     * 获取缓存
     *
     * @param key
     * @return
     * @throws Exception
     */
    Object getHashCached(byte[] key, byte[] mapkey) throws Exception;


    /**
     * 删除 缓存
     *
     * @param key
     * @param mapkey
     * @return
     * @throws Exception
     */
    Long deleteHashCached(byte[] key, byte[] mapkey) throws Exception;

    /**
     * 获取 map的长度
     *
     * @param key
     * @return
     * @throws Exception
     */
    Long getHashSize(byte[] key) throws Exception;

    /**
     * 获取 map中的所有值
     *
     * @param key
     * @return
     * @throws Exception
     */
    List<Object> getHashValues(byte[] key) throws Exception;


    /**
     * 获取 map的长度
     *
     * @param
     * @return
     * @throws Exception
     */
    Long getDBSize() throws Exception;

    /**
     * 获取 map的长度
     *
     * @param
     * @return
     * @throws Exception
     */
    void clearDB() throws Exception;
    
    
    /**
     * 删除 缓存
     *
     * @param key
     * @return
     * @throws Exception
     */
    String deleteStringCached(String key) throws Exception;

    /**
     * 更新 缓存
     *
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    String updateStringCached(String key, String value, Long expire) throws Exception;

    /**
     * 获取缓存
     *
     * @param key
     * @return
     * @throws Exception
     */
    String getStringCached(String key) throws Exception;

}
