package controller.member;

import javax.servlet.http.HttpSession;

public class MemberSessionUtils {

	public static final String MEMBER_SESSION_KEY = "memberId";
	public static final String MEMBER_SESSION_NUM = "mnum";

	/* ���� �α����� ������� ID�� ���� */
	public static String getLoginMemberId(HttpSession session) {
		String mId = (String) session.getAttribute(MEMBER_SESSION_KEY);
		return mId;
	}

	/* ���� �α����� ������� mnum */
	public static int getLoginMemberNum(HttpSession session) {
		int mnum = (int) session.getAttribute(MEMBER_SESSION_NUM); 
		return mnum;
	}
	/* �α����� ���������� �˻� */
	public static boolean hasLogined(HttpSession session) {
		if (getLoginMemberId(session) != null) {
			return true;
		}
		return false;
	}

	/* ���� �α����� ������� ID�� memberId���� �˻� */
	public static boolean isLoginMember(String mId, HttpSession session) {
		String loginMember= getLoginMemberId(session);
		if (loginMember == null) {
			return false;
		}
		return loginMember.equals(mId);
	}

}
