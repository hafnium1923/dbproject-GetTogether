package controller.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class SearchMemberController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//�α��� üũ�ϴ� ����....
		//�˻���� user���� ã��
		//ã������ ����Ʈ�� request�� ��´�		

    	if (request.getMethod().equals("GET")) {	
    		// GET request: �˻���� �˻��ϴ� ���
    		String query = request.getParameter("query");
    		
//    		UserManager manager = UserManager.getInstance(); ���� ���ۼ��� ã�� Ŭ����..
			List<User> userList = new ArrayList<User>();
    		//= manager.findUserList();
			User user = new User();
			user.setName("���׽�Ʈ");
			userList.add(user);
			User user2 = new User();
			user2.setName("���׽�Ʈ");
			userList.add(user2);
			User user3 = new User();
			user3.setName("���׽�Ʈ");
			userList.add(user3);
			User user4 = new User();
			user4.setName("���׽�Ʈ");
			userList.add(user4);
			User user5 = new User();
			user5.setName("�����׽�Ʈ");
			userList.add(user5);
//			userList = manager.findUserList(query);//�̸� or �Ұ��������� �˻�
			request.setAttribute("userList", userList);
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
