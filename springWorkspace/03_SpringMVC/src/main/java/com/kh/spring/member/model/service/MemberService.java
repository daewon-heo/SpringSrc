package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
		Member selectOne(Member m);
		
		int insertMember(Member m);
		
		int updateMmember(Member m);
		
		int deleteMember(String userId);
}
