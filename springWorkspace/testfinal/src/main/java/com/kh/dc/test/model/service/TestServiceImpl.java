package com.kh.dc.test.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dc.test.model.dao.TestDao;
import com.kh.dc.test.model.vo.TestDc;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestDao testDao;
	
	@Override
	public void testPrint() {
		System.out.println("testService > testprint 실행");
		
	}

	@Override
	public TestDc selectOne() {
		System.out.println("testservice > selectOne 실행");
		return testDao.selectOne();
	}

}
