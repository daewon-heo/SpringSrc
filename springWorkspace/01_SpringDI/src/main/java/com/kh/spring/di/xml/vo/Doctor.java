package com.kh.spring.di.xml.vo;

public class Doctor implements Job {

	@Override
	public void jobInfo(String compName) {
		System.out.println(compName + "에서 환자 진료 중");

	}

}
