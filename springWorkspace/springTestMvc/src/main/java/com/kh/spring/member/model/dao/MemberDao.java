package com.kh.spring.member.model.dao;

import com.kh.spring.member.model.vo.Member;

public interface MemberDao {
	// 설계 과정에 생성되는 DAO 인터페이스
	
	Member selectOne(Member m);
}
