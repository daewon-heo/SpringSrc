package com.kh.spring.dev.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.dev.model.vo.Dev;

@Repository
public class DevDaoImpl implements DevDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public int insertDev(Dev dev) {
		return sqlSession.insert("dev.insertDev", dev);
	}

	@Override
	public List<Dev> selectList() {
		return sqlSession.selectList("dev.selectList");
	}

	@Override
	public Dev selectOne(int no) {
		return sqlSession.selectOne("dev.selectDev", no);
	}

	@Override
	public int updateDev(Dev dev) {
		return sqlSession.update("dev.updateDev", dev);
	}

	@Override
	public int deleteDev(int no) {
		return sqlSession.delete("dev.deleteDev", no);
	}

}
