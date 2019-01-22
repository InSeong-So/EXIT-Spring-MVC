package com.spring.basic.member.service;

import com.spring.basic.member.Member;

public interface IMemberService {
//	void memberRegister(String memId, String memPw, String memMail, String memPhone1, String memPhone2, String memPhone3);
//	Member memberSearch(String memId, String memPw);
//	void memberModify();
//	void memberRemove();
	void memberRegister(Member member);
	void memberSearch(Member member);
	Member[] memberModify(Member member);
	void memberRemove(Member member);
}
