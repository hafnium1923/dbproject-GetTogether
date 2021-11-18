package controller.memberrecommend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.MemberRecommend;
import model.service.MemberRecommendManager;


public class ListMemberRecommendController implements Controller{
	
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
	    	MemberRecommendManager manager = MemberRecommendManager.getInstance();
			List<MemberRecommend> memberRecommendList = manager.findRecommendMemberList();
			
			// commList ��ü�� request�� �����Ͽ� Ŀ�´�Ƽ ����Ʈ ȭ������ �̵�(forwarding)
			request.setAttribute("memberRecommendList", memberRecommendList);				
			return "/memberrecommend/memberreocmmend.jsp";        
	    }
	
}
