package com.kh.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public Member selectOne(Member m) {
		return memberDao.selectOne(m);
	}

	@Override
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMmember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
