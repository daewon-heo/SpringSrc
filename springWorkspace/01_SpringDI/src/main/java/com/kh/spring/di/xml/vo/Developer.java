package com.kh.spring.di.xml.vo;

public class Developer implements Job {

	@Override
	public void jobInfo(String compName) {
		System.out.println(compName + "에서 개발 업무를 수행합니다.");

	}

}
