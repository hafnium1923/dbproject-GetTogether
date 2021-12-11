package controller.message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Member;
import model.Message;
import model.dao.MessageDAO;

public class DeleteMessageController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String title = request.getParameter("title");
		String content = request.getParameter("msgcontent");
		String receiverId = request.getParameter("receiver");
		
		Member sender = new Member();//id�� num���� Membe��ü�� ���Ť�����..
		Member receiver = new Member();//id�� Member��ü�� �������� Manager
		sender.setMnum(3);
		receiver.setMnum(2);
		
		Message message = new Message(receiver, sender, title, content);
		MessageDAO msgDAO = new MessageDAO();
		msgDAO.create(message);
		return "redirect:/message/list";
	}
}
