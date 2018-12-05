package com.kh.spring.di.anno.vo;

import org.springframework.stereotype.Component;

import com.kh.spring.di.anno.Job;

@Component("policeOfficer")
public class PoliceOfficer implements Job {

	@Override
	public void jobInfo(String compName) {
		System.out.println(compName + "에서 범죄 대응 합니다.");
	}

}
