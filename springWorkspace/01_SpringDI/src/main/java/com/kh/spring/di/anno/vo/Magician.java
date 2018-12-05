package com.kh.spring.di.anno.vo;

import org.springframework.stereotype.Component;

import com.kh.spring.di.anno.Job;

@Component("magician")
public class Magician implements Job {

	@Override
	public void jobInfo(String compName) {
		System.out.println(compName + "에서 마술 공연합니다.");
	}

}
