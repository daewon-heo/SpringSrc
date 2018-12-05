package com.kh.spring.di.model.vo;

public class BeanFactory {
	// 빈팩토리를 통해 개발자가 원하는 객체를
	// 선택하여 생성해 줄 수 있는 로직
	public static Object getBean(String beanName) {
		TV tv = null;
		
		if(beanName.equals("samsung")) {
			tv = new SamsungTV();
		}else {
			tv = new LgTV();
		}
		
		return tv;
	}
}
