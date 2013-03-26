package com.mtea.msdre.byspring.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * String -> String
 * @author macrotea@qq.com
 * @date 2013-3-26 下午8:45:34
 * @version 1.0
 * @note
 */
public class StringStringRepository {
	
	@Autowired
	private RedisTemplate<String, String> template;	
	
	public void add(String key, String value) {
		template.opsForValue().set(key, value);
	}
	
	public String getValue(String key) {
		return template.opsForValue().get(key);
	}
	
	public void delete(String key) {
		template.opsForValue().getOperations().delete(key);
	}
	
	public Boolean exists(String key) {
		return template.opsForValue().getOperations().hasKey(key);
	}
	
	public DataType getType(String key) {
		return template.opsForValue().getOperations().type(key);
	}
	
}
