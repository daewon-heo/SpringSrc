package com.kh.spring.di.model.vo;

public class LgTV implements TV {
	@Override
	public void turnOn() {
		System.out.println("LgTV 전원이 켜집니다.");
	}
	
	@Override
	public void turnOff() {
		System.out.println("LgTV 전원이 꺼집니다.");
	}

	@Override
	public void volumeUp() {
		System.out.println("LgTV 소리를 키웁니다 ...");
	}

	@Override
	public void volumeDown() {
		System.out.println("LgTV 소리를 줄입니다...");
	}
}
