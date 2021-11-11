package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Message;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ListMessageController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
//    	if (!UserSessionUtils.hasLogined(request.getSession())) {
//            return "redirect:/user/login/form";		// login form ��û���� redirect
//        }
    	
    	//���� DAO�� ��� �ӽ÷� ��ü�� ����
    	List<Message> messageList = new ArrayList<Message>();
    	User sender = new User();
    	sender.setName("�躸�½��ϴ�");
    	User receiver = new User();
    	receiver.setName("�ڹڵ���");
    	Message msg = new Message(0, sender, receiver, "���� ���Ϥ�", "�޼�������������. �� ��Ź�帳�ϴٴٴ�");
    	messageList.add(msg);
    	request.setAttribute("messageList", messageList);
		return "/message/messageList.jsp";
	}

}
