package com.kh.jsp.member.model.vo;

import java.sql.Date;

public class Member implements java.io.Serializable {
	
	private String userId;
	private String password;
	private String userName;
	private String gender;
	private int age;
	private String email;
	private String phone;
	private String address;
    private String hobby;
    private Date enrollDate;
	
    /*
     * Oracle DBMS의 
     * Date 자료형은 날짜의 정보만을 저장한다.
     * 따라서 오라클로부터 날짜 데이터를
     * 받아 올 때에도 날짜 정보만을 받아온다.
     * 
     * 만약, 시간 정보까지 필요하다면
     * Date 자료형이 아닌
     * Timestamp 자료형을 사용해주어야 한다.
     * 이 때에 timestamp 자료형은 DB와 VO
     * 모두 동일하게  timestamp 여야 한다.
     */
    
    // 생성자
    // - 기본 생성자
    // - id와 비밀번호를 받는 생성자
    // - enrollDate를 뺀 모든 필드 정보를 받는 생성자
    // - enrollDate까지 받는 생성자
    
    public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public Member(String userId, String password, String userName, String gender, int age, String email, String phone,
			String address, String hobby) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}

	public Member(String userId, String password, String userName, String gender, int age, String email, String phone,
			String address, String hobby, Date enrollDate) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}
    
    // getter & setter

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
    // toString()

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", userName=" + userName + ", gender=" + gender
				+ ", age=" + age + ", email=" + email + ", phone=" + phone + ", address=" + address + ", hobby=" + hobby
				+ ", enrollDate=" + enrollDate + "]";
	}
}