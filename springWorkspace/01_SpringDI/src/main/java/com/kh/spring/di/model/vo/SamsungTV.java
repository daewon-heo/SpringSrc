package com.kh.spring.di.model.vo;

public class SamsungTV implements TV {

	@Override
	public void turnOn() {
		 System.out.println("SamsungTV 전원을 켭니다!");
	}

	@Override
	public void turnOff() {
		System.out.println("SamsungTV 전원을 끕니다!");
	}

	@Override
	public void volumeUp() {
		System.out.println("SamsungTV 소리를 키웁니다...");
	}

	@Override
	public void volumeDown() {
		System.out.println("samsungTV 소리를 줄입니다...");
	}
}
