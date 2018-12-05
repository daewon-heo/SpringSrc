package com.kh.spring.di.xml.vo;

public class Person {
	// xml 을 통한  의존성 주입 객체
	
	// 1.Field
	private String name;
	private Job myJob;
	
	// 2. Construnc
	public Person() {}
	
	public Person(String name, Job myJob) {
		this.name = name;
		this.myJob = myJob;
	}
	
	// 3. 사용하는 메소드
	public void printPerson() {
		System.out.println(name + " : " + myJob);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Job getMyJob() {
		return myJob;
	}

	public void setMyJob(Job myJob) {
		this.myJob = myJob;
	}
	
	
}
