package com.kh.dc.test.model.dao;

import com.kh.dc.test.model.vo.TestDc;

public interface TestDao {
	public void testPrint();
	
	public TestDc selectOne();
}
