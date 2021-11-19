package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.Member;
import model.Message;

public class MessageDAO {
	private JDBCUtil jdbcUtil = null;
	java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());

	public MessageDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * create new message
	 */
	public int create(Message message) throws SQLException {
		/**
		 *	msg_id, title, senddate, content, checked ,sender, receiver
		 */
		String sql = "INSERT INTO MESSAGE VALUES (SEQ_MSGID.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		long time = message.getSendDate().getTime();
		java.sql.Date date1 = new java.sql.Date(time);
		Object[] param = new Object[] {message.getTitle(), date1,
				message.getContent(), message.isChecked()?'T':'F', message.getSender().getMnum(),
						message.getReceiver().getMnum()};		
		logger.info(message.getReceiver().getMnum() +"/" +  
				message.getSender().getMnum()+"/" + message.getTitle()+ message.getSendDate()+
				message.getContent()+ message.isChecked());
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}

	/**
	 * �޼�������(������)
	*/
	
	public int removeMessageList(int[] msgId) throws SQLException {
		String sql = "DELETE FROM MESSAGE WHERE msgid=?";

		try {	
			int result = 0;
			for (int id: msgId) {
				jdbcUtil.setSqlAndParameters(sql, new Object[] {id});
				result += jdbcUtil.executeUpdate();
			}// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	/**>
	 * message �ϳ��� ã�Ƽ� ��ȯ.
	 */
	public Message findMessage(int msgId){
		String sql = "SELECT MSG_ID, TITLE, SENDDATE, CONTENT, CHECKED, SENDER, "
				+ "RECEIVER FROM MESSAGE WHERE MSG_ID=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {msgId});	
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Message msg;
			if (rs.next()) {
				Member sender = new Member();
				sender.setMnum(2);
				sender.setMname("2��");
				Member receiver = new Member();
				receiver.setMnum(3);
				receiver.setMname("3��");
				msg = new Message (
					rs.getInt("msg_id"),
					sender,
					receiver,
					rs.getString("title"),
					rs.getDate("sendDate"),
					rs.getString("content"),
					rs.getBoolean("checked")
					);
				return msg;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return null;			
	}
	
	/**
	 * receiver�� ���� User�� �޼������� ��ȯ.
	 */
	public List<Message> findReceivedMessageList(int mNum) throws SQLException {
		String sql = "SELECT MSG_ID, TITLE, SENDDATE, CONTENT, CHECKED, SENDER FROM MESSAGE WHERE RECEIVER=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {mNum});
		try {
//			MemberManager memberManager = MemberManager.getInstance();
			ResultSet rs = jdbcUtil.executeQuery();
			List<Message> msgList = new ArrayList<Message>();
			while (rs.next()) {
//				Member sender = memberManager.findMember(rs.getInt("sender"));
				Member sender = new Member();
				sender.setMnum(2);
				sender.setMname("2��");
				Member receiver = new Member();
				receiver.setMnum(3);
				receiver.setMname("3��");
//				Member receiver = memberManager.findMember(mNum);
				Message msg = new Message (
					rs.getInt("msg_id"),
					sender,
					receiver,
					rs.getString("title"),
					rs.getDate("sendDate"),
					rs.getString("content"),
					rs.getBoolean("checked")				
					);
				logger.info("added");
				msgList.add(msg);
			}
			return msgList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * sender�� ���� User�� �޼������� ��ȯ.
	 */
	public List<Message> findSendedMessageList(int mNum) throws SQLException {
		String sql = "SELECT MSG_ID, TITLE, SENDDATE, CONTENT, CHECKED, SENDER, "
				+ "RECEIVER FROM MESSAGE WHERE SENDER=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {mNum});

		//		MemberDAO memberManager = new MemberDAO();
		try {
			MemberManager memberManager = MemberManager.getInstance();
			ResultSet rs = jdbcUtil.executeQuery();
			List<Message> msgList = new ArrayList<Message>();
			while (rs.next()) {
				Member sender = memberManager.findMember(rs.getInt("sender"));
				Member receiver = memberManager.findMember(rs.getInt("receiver"));
				Message msg = new Message (
					rs.getInt("msg_id"),
					sender,
					receiver,
					rs.getString("title"),
					rs.getDate("sendDate"),
					rs.getString("content"),
					rs.getBoolean("checked")				
					);
				msgList.add(msg);
			}
			return msgList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
}
