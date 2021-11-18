package controller.memberrecommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.MemberRecommand;
import model.service.MemberRecommandManager;


public class ListMemberRecommandController implements Controller{
	
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
	    	MemberRecommandManager manager = MemberRecommandManager.getInstance();
			List<MemberRecommand> memberRecommandList = manager.findRecommandMemberList();
			
			// commList ��ü�� request�� �����Ͽ� Ŀ�´�Ƽ ����Ʈ ȭ������ �̵�(forwarding)
			request.setAttribute("memberRecommandList", memberRecommandList);				
			return "/memberrecommand/memberreocmmand.jsp";        
	    }
	
}
