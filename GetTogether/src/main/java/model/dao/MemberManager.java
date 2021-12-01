package model.dao;

import java.sql.SQLException;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import model.Member;
import model.service.ExistingUserException;
import model.service.PasswordMismatchException;
import model.service.UserNotFoundException;

public class MemberManager {
	private static MemberManager memMan = new MemberManager();
	private MemberDAO memberDAO;
	
	private MemberManager() {
		try
		{
			memberDAO = new MemberDAO();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static MemberManager getInstance()
	{
		return memMan;
	}
	
	public int create(Member member) throws SQLException, ExistingUserException {
		System.out.println("create in");
		if (memberDAO.existingMember(member.getMid())) {
			throw new ExistingUserException(member.getMid()+"는 이미 존재하는 회원입니다.");
		}
		
		return memberDAO.create(member);
	}
	
	public int update(Member member) throws SQLException {
		return memberDAO.update(member);
	}
	
	public int remove(String mid) throws SQLException {
		return memberDAO.remove(mid);
	}
	
	//mnum으로 회원 찾기
	public Member findMemberByMnum(int mnum) throws SQLException, UserNotFoundException {
		Member member = memberDAO.findMemberByMnum(mnum);
		
		if(mnum < 0) {
			throw new UserNotFoundException(mnum + "는 존재하지 않는 회원번호입니다.");
		}
		
		return member;
	}
	
	//mid로 회원 찾기
	public Member findMemberByMid(String mid) throws SQLException, UserNotFoundException {
		Member member = memberDAO.findMemberByMid(mid);
		
		if (member == null) {
			throw new UserNotFoundException(mid + "는 존재하지 않는 회원 아이디입니다.");
		}
		
		return member;
	}
	
	public Member findMember(int mNum) throws SQLException, UserNotFoundException {
		Member member = memberDAO.findMember(mNum);
		if (member == null) {
			throw new UserNotFoundException(mNum + "은 존재하지 않는 회원번호입니다.");
		}
		return member;
	}
	
	public boolean login(String mid, String passwd)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
		Member member = findMemberByMid(mid);
		
		//비밀번호 체크
		if (member.checkPassword(passwd) == false) {
			System.out.println("in checkPassword");
			System.out.println(member.checkPassword(passwd));
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}

		return true;
	}
	 
}
