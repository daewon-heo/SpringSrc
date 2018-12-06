package com.kh.spring.member.model.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.kh.spring.member.model.vo.Member;

public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public Member selectOne(Member m) {
		return sqlSession.selectOne("Member_mapper.loginMember", m);
	}

}
