package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Member;
import model.service.MemberManager;

public class SearchMemberController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";
        }

    	if (request.getMethod().equals("GET")) {	
    		// GET request: �˻���� �˻��ϴ� ���
    		String query = request.getParameter("query");
    		
    		
    		MemberManager memberManager = MemberManager.getInstance();
			List<Member> searchedMemberList = memberManager.searchMember(query);
			request.setAttribute("memberList", searchedMemberList);
    	}
    	else {
    		//POST -> language�� ���� �о߷� search �� ���
    		String language = request.getParameter("language");
    		String field = request.getParameter("field");
    		
    		//search ���� (,�� �������� ������ �ϳ��� ã�´ٴ���,,)
    		//findUserList(language, field);
    	}
		
 		return "/member/searchResult.jsp";
	}

}
