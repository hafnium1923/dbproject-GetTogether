package model.dao;

import java.sql.SQLException;

import model.Member;
import model.service.ExistingUserException;
import model.service.PasswordMismatchException;
import model.service.UserNotFoundException;
import model.service.*;

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
		if (memberDAO.existingMember(member.getMid())) {
			throw new ExistingUserException(member.getMid()+"�� �����ϴ� ���̵��Դϴ�.");
		}
		
		return memberDAO.create(member);
	}
	
	public int update(Member member) throws SQLException {
		return memberDAO.update(member);
	}
	
	public int remove(String mid) throws SQLException {
		return memberDAO.remove(mid);
	}
	
	public Member findMember(String mid) throws SQLException, UserNotFoundException {
		Member member = memberDAO.findMember(mid);
		if (member == null) {
			throw new UserNotFoundException(mid + "�� �������� �ʴ� ���̵��Դϴ�.");
		}
		return member;
	}
	
	public Member findMember(int mNum) throws SQLException, UserNotFoundException {
		Member member = memberDAO.findMember(mNum);
		if (member == null) {
			throw new UserNotFoundException(mNum + "�� �������� �ʴ� ��ȣ�Դϴ�.");
		}
		return member;
	}
	
	public boolean login(String mid, String passwd)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
		Member member = findMember(mid);

		if (member.checkPassword(passwd) == false) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}

		return true;
	}
	 
}
