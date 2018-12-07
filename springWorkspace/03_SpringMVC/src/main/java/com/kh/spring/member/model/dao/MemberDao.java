package com.kh.spring.member.model.dao;

import com.kh.spring.member.model.vo.Member;

public interface MemberDao {
	
	Member selectOne(Member m);
	
	int insertMember(Member m);
	
	int updateMember(Member m);
	
	int deleteMember(String userId);
}
