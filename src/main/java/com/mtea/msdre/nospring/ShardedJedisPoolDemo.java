package com.mtea.msdre.nospring;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 主从
 * @author macrotea@qq.com
 * @date 2013-3-26 下午8:49:37
 * @version 1.0
 * @note
 */
public class ShardedJedisPoolDemo {

	private static ShardedJedisPool pool;
	
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("redisShareInfo");
		if (bundle == null) {
			throw new IllegalArgumentException("[redisShareInfo.properties] is not found!");
		}
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
		
		JedisShardInfo jedisShardInfo1 = new JedisShardInfo(bundle.getString("redis.ip1"), Integer.valueOf(bundle.getString("redis.port")));
		JedisShardInfo jedisShardInfo2 = new JedisShardInfo(bundle.getString("redis.ip2"), Integer.valueOf(bundle.getString("redis.port")));

		List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
		list.add(jedisShardInfo1);
		list.add(jedisShardInfo2);
		
		pool = new ShardedJedisPool(config, list);  
		
		
	}

	public static void main(String[] args) {
	    // 从池中获取一个Jedis对象  
	    ShardedJedis jedis = pool.getResource();  
	    String keys = "name";  
	    String value = "macrotea";  
	    // 删数据  
	    jedis.del(keys);  
	    // 存数据  
	    jedis.set(keys, value);  
	    // 取数据  
	    String v = jedis.get(keys);  
	      
	    System.out.println(v);  
	      
	    // 释放对象池  
	    pool.returnResource(jedis);  
	}
}
