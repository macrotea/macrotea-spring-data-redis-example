package com.mtea.msdre.nospring;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 池化
 * @author macrotea@qq.com
 * @date 2013-3-26 下午8:48:52
 * @version 1.0
 * @note
 */
public class JedisPoolDemo {

	private static JedisPool pool;
	
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			throw new IllegalArgumentException("[redis.properties] is not found!");
		}
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
		pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")));
	}

	public static void main(String[] args) {
		Jedis jedis = pool.getResource();
		
		String keys = "name";
		// 删数据
		jedis.del(keys);
		// 存数据
		jedis.set(keys, "macrotea");
		// 取数据
		String value = jedis.get(keys);
		System.out.println(value);
		
		pool.returnResource(jedis);  
	}
}
