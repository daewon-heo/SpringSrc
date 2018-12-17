package com.kh.spring.member.model.service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.dao.MemberDaoImpl;
import com.kh.spring.member.model.vo.Member;

public class MemberServiceImpl implements MemberService{
	// 설계된 인터페이스를 기반으로 구현된
	// 실제 비즈니스 로깆을 수행하는 객체
	
	@Override
	public Member selectOne(Member m) {
		MemberDao mDao = new MemberDaoImpl();
		
		return mDao.selectOne(m);
	}

	@Override
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int idDupCheck(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
