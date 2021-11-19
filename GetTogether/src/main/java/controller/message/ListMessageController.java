package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Message;
import model.dao.MessageDAO;
import model.service.MessageManager;

import java.util.List;

public class ListMessageController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// �츮�� Member���� �ӽ÷� �س����ϴ�..
//    	if (!UserSessionUtils.hasLogined(request.getSession())) {
//            return "redirect:/user/login/form";		// login form ��û���� redirect
//        }
    	//mnum�� �������� �Լ��� �ʿ��ϴ�..
    	int mnum = 2;
    	//UserSessionUtils.getLoginUserNumber(request.getSession());
    	MessageManager msgManager = MessageManager.getInstance();
    	List<Message> messageList = msgManager.findReceivedMessageList(mnum);
    	request.setAttribute("messageList", messageList);
		return "/message/messageList.jsp";
	}

}
