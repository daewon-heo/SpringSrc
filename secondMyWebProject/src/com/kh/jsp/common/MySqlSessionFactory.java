package com.kh.jsp.common;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {
	// 마이 바티스 설정파일을 읽어와서 
	// DB와 자바를 연결하는 Connection 객체 생성
	
	private static SqlSessionFactory factory;
	
	public MySqlSessionFactory(){}
	
	public static SqlSessionFactory getSqlSessionFactory(){
		
		try {
			factory = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsStream("/config/mybatis/mybatis-config.xml"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
				
		return factory;
	}
}
