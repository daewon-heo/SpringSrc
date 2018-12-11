package com.kh.spring.member.model.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	SqlSessionTemplate sqlsession;

	@Override
	public int insertMember(Member member) {
		return sqlsession.insert("member.insertMember", member);
	}

	@Override
	public Member selectOne(String userId) {
		return sqlsession.selectOne("member.loginMember", userId);
	}

	@Override
	public int updateMember(Member m) {
		return sqlsession.update("member.updateMember", m);
	}

	@Override
	public int deleteMember(String userId) {
		return sqlsession.delete("member.deleteMember", userId);
	}

	@Override
	public int checkIdDuplicate(HashMap<String, Object> hmap) {
		sqlsession.selectOne("member.checkIdDuplicate", hmap);
		
		return (int)hmap.get("result");
	}

}
