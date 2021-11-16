package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Message;
import model.User;

public class ViewMessageDetailController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//�α��� üũ
    	User sender = new User();
    	sender.setName("�躸�½��ϴ�");
    	User receiver = new User();
    	receiver.setName("�ڹڵ���");
    	Message msg = new Message(0, sender, receiver, "���� ���Ϥ�", "�޼�������������. �� ��Ź�帳�ϴٴٴ�");
    	request.setAttribute("message", msg);
		
		return "/message/messageDetail.jsp";
	}

}
