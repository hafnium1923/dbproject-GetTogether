package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.MemberManager;

public class MemberDuplicateCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mid = request.getParameter("mid");
		System.out.println("mid: "+mid);
	
		try {
			MemberManager manager = MemberManager.getInstance();
			manager.findMemberByMid(mid);
			
			System.out.println("���� ���̵�� �ߺ��Դϴ�.");

			request.setAttribute("mid", null);
			
			return "/member/signup/inputForm.jsp";
		}
		catch (Exception e) {
			System.out.println("���� ���̵�� ��� �����մϴ�.");
			request.setAttribute("duplicate", true);
			request.setAttribute("exception", e);
			
			request.setAttribute("mid", mid);
			
			System.out.println("request.getAttribute(mid): "+request.getAttribute("mid"));
			
			return "/member/signup/inputForm.jsp";
		}
	}
	
}
