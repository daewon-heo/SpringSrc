package com.kh.spring.member.model.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.kh.spring.member.model.vo.Member;


public interface MemberService {

	public int insertMember(Member member);

	public Member selectOne(String userId);

	public int deleteMember(String userId);

	public int updateMmeber(Member m);
	
	int checkIdDuplicate(String userId);
	
}
