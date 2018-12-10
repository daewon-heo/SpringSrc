package com.kh.spring.dev.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.dev.model.dao.DevDao;
import com.kh.spring.dev.model.vo.Dev;

@Service
public class DevServiceImpl implements DevService {
	
	@Autowired
	private DevDao devDao;

	@Override
	public int insertDev(Dev dev) {

		return devDao.insertDev(dev);
		
	}

	@Override
	public List<Dev> selectList() {

		return devDao.selectList();
	}

	@Override
	public Dev selectOne(int no) {
		
		return devDao.selectOne(no);
	}

	@Override
	public int updateDev(Dev dev) {

		return devDao.updateDev(dev);
	}

	@Override
	public int deleteDev(int no) {
		
		return devDao.deleteDev(no);
	}

}
