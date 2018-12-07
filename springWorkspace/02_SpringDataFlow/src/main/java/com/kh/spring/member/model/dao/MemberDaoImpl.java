package com.kh.spring.member.model.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kh.spring.member.model.vo.Member;

public class MemberDaoImpl implements MemberDao {
	
	SqlSessionTemplate sqlSession;
	
	public MemberDaoImpl() {
		sqlSession = (SqlSessionTemplate)
				(new GenericXmlApplicationContext("/root-context.xml")
						.getBean("sqlSessionTemplate"));
	}

	@Override
	public Member selectOne(Member m) {
		return sqlSession.selectOne("Member_mapper.loginMember", m);
	}

}
