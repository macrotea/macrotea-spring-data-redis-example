package com.mtea.msdre.byspring.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.mtea.msdre.byspring.model.User;

/**
 * String -> User Json 
 * @author macrotea@qq.com
 * @date 2013-3-26 下午8:46:21
 * @version 1.0
 * @note
 */
public class UserRepository {

	@Autowired
	private RedisTemplate<String, User> template;	
	
	public void add(User user) {
		template.opsForValue().set(user.getLogin(), user);
	}
	
	public User get(String key) {
		return template.opsForValue().get(key);
	}
	
}
