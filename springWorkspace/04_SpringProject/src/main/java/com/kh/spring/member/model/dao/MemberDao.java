package com.kh.spring.member.model.dao;

import java.util.HashMap;

import com.kh.spring.member.model.vo.Member;

public interface MemberDao {
	public int insertMember(Member member);

	public Member selectOne(String userId);

	public int updateMember(Member m);

	public int deleteMember(String userId);

	int checkIdDuplicate(HashMap<String, Object> hmap);
}
