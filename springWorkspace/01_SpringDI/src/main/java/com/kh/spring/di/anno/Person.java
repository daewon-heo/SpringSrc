package com.kh.spring.di.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.spring.di.anno.Job;

@Component
public class Person {
	// 어노테이션을 활용한 객체 의존성 주입
	
	// value 어노테이션
	// @value("값")을 통해서 내부에서 XML화 되어 처리되는 어노테이션
	@Value("홍길동")
	private String name;
	
	// 특정 객체를 의존성 주입하기 위한 어노테이션
	// @Autowired[@Qualifier("값")]
	// 특정 객체를 이름에 따라 자동으로 매칭하여 가져오는 어노테이션
	
	@Autowired
	@Qualifier("magician")
	private Job myJob;
	
	public Person() {}
	
	public Person(String name, Job myJob) {
		this.name = name;
		this.myJob = myJob;
	}
	
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
