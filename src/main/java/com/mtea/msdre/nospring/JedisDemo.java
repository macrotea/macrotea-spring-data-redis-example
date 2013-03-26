package com.mtea.msdre.nospring;

import redis.clients.jedis.Jedis;

/**
 * 入门
 * @author macrotea@qq.com
 * @date 2013-3-26 下午8:52:16
 * @version 1.0
 * @note
 */
public class JedisDemo {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1");
		
		String keys = "name";
		
		// 删数据
		jedis.del(keys);
		
		// 存数据
		jedis.set(keys, "macrotea");
		
		// 取数据
		String value = jedis.get(keys);
		
		System.out.println(value);
	}
}
