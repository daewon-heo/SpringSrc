package com.kh.spring.dev.model.service;

import java.util.List;

import com.kh.spring.dev.model.vo.Dev;

public interface DevService {
	
	public int insertDev(Dev dev);
	
	public List<Dev> selectList();
	
	public Dev selectOne(int no);
	
	public int updateDev(Dev dev);
	
	public int deleteDev(int no);
}
