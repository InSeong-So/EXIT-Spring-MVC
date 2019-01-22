package com.spring.basic.member.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.basic.member.MemPhone;
import com.spring.basic.member.Member;
import com.spring.basic.member.dao.MemberDao;

//@Service
//@Service("memService")
//@Component
//@Component("memService")
//@Repository
@Repository("memService")
public class MemberService implements IMemberService {

	@Autowired
	MemberDao dao;

	@Override
	public void memberRegister(Member member) {
		printMembers(dao.memberInsert(member));
	}

	@Override
	public void memberSearch(Member member) {
		printMember(dao.memberSelect(member));
	}

	@Override
	public Member[] memberModify(Member member) {
		
		Member memBef = dao.memberSelect(member);
		Member memAft = dao.memberUpdate(member);
		printMember(memAft);
		
		return new Member[]{memBef, memAft};
	}

	@Override
	public void memberRemove(Member member) {
		printMembers(dao.memberDelete(member));
	}
	
	private void printMembers(Map<String, Member> map) {
		
		Set<String> keys = map.keySet();
		Iterator<String> iterator = keys.iterator();
		
		while (iterator.hasNext()) {
			String key = iterator.next();
			Member mem = map.get(key);
			printMember(mem);
		}
		
	}
	
	private void printMember(Member mem) {
		
		System.out.print("ID:" + mem.getMemId() + "\t");
		System.out.print("|PW:" + mem.getMemPw() + "\t");
		System.out.print("|MAIL:" + mem.getMemMail() + "\t");
		
		List<MemPhone> memPhones = mem.getMemPhones();
		for(MemPhone memPhone : memPhones) {
			System.out.print("|PHONE:" + memPhone.getMemPhone1() + " - " + 
											   memPhone.getMemPhone2() + " - " + 
											   memPhone.getMemPhone3() + "\t");
		}
		
		System.out.print("|AGE:" + mem.getMemAge() + "\t");
		System.out.print("|ADULT:" + mem.isMemAdult() + "\t");
		System.out.print("|GENDER:" + mem.getMemGender() + "\t");
		System.out.print("|FAVORITE SPORTS:" + Arrays.toString(mem.getMemFSports()) + "\n");
		
	}
	
//	@Override
//	public void memberRegister(String memId, String memPw, String memMail,
//			String memPhone1, String memPhone2, String memPhone3) {
//		System.out.println("memberRegister()");
//		System.out.println("memId : " + memId);
//		System.out.println("memPw : " + memPw);
//		System.out.println("memMail : " + memMail);
//		System.out.println("memPhone : " + memPhone1 + " - " + memPhone2 + " - " + memPhone3);
//		
//		dao.memberInsert(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);
//	}
//
//	@Override
//	public Member memberSearch(String memId, String memPw) {
//		System.out.println("memberSearch()");
//		System.out.println("memId : " + memId);
//		System.out.println("memPw : " + memPw);
//		
//		Member member = dao.memberSelect(memId, memPw);
//		
//		return member;
//	}


}