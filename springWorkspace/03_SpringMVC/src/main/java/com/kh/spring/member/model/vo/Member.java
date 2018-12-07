package com.kh.spring.member.model.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Member implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5812860547707025788L;
	private String userId;
	private String password;
	private String userName;
	
	public Member() {
		super();
	}
	
	public Member(String userId, String password, String userName) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", userName=" + userName + "]";
	}
}
