package com.kh.dc.test.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.dc.test.model.vo.TestDc;

@Repository
public class TestDaoImpl implements TestDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public void testPrint() {
		System.out.println("testDao > testprint 실행");
	}

	@Override
	public TestDc selectOne() {
		System.out.println("testDao > selectOne 실행");
		return sqlSession.selectOne("test_mapper.selectTest");
	}

}
