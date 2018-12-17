package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
	// 설계 항목에 포함되는 인터페이스는
	// 후에 작성할 Implement(상속받을 객체)에게
	// 구현할 수 있는 하나의 가이드를 제공할 수 있으며
	// 여러 디자인 패턴을 구현할 수 잇릿는 등의 결합도를
	// 낮춰줄 수 있을 뿐만 아니라
	// 개발 및 배포 이후 유지 보수에 있어서 하나의
	// 목차역할을 수행할 수 있다.
	
	public Member selectOne(Member m);
	
	public int insertMember(Member m);
	
	public int updateMember(Member m);
	
	public int deleteMember(String userId);
	
	public int idDupCheck(String userId);
}
