package com.mtea.msdre.byspring.model;

import java.util.Date;

/**
 * 
 * @author macrotea@qq.com
 * @date 2013-3-26 下午8:45:15
 * @version 1.0
 * @note
 */
public class User {
	
	private String login;
	
	private String fullName;
	
	private Date lastLogin;

	public User() {}
	
	public User(String login, String fullName) {
		this.login = login;
		this.fullName = fullName;
		this.lastLogin = new Date();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}		
	
}
