package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
				sender.setMnum(rs.getInt("sender"));
				Member receiver = new Member();
				receiver.setMnum(rs.getInt("receiver"));
				boolean checked;
				if (rs.getString("checked").equals("F")) {
					checked = false;
				}
				else {
					checked = true;
				}
				msg = new Message (
					rs.getInt("msg_id"),
					receiver,
					sender,
					rs.getString("title"),
					rs.getDate("sendDate"),
					rs.getString("content"),
					checked
					);
				return msg;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {	
			jdbcUtil.close();	// resource ��ȯ
		}		
		return null;
	}
	
	/**
	 * receiver�� ���� User�� �޼������� ��ȯ.
	 */
	public List<Message> findReceivedMessageList(int mNum) throws SQLException {
		String sql = "SELECT MSG_ID, TITLE, SENDDATE, CONTENT, CHECKED, SENDER FROM MESSAGE WHERE RECEIVER=? "
				+ "ORDER BY SENDDATE DESC";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {mNum});
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Message> msgList = new ArrayList<Message>();
			while (rs.next()) {
				int msgid= rs.getInt("msg_id");
				String title = rs.getString("title");
				Date sendDate = rs.getDate("sendDate");
				String content = rs.getString("content");
				boolean checked;
				if (rs.getString("checked").equals("F")) {
					checked = false;
				}
				else {
					checked = true;
				}
				Member sender = new Member();
				int snum = rs.getInt("sender");
				sender.setMnum(snum);
				Member receiver = new Member();
				Message msg = new Message (
					msgid,
					receiver,
					sender,
					title,
					sendDate,
					content,
					checked
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
	
	/**
	 * sender�� ���� User�� �޼������� ��ȯ.
	 */
	public List<Message> findSentMessageList(int mNum) throws SQLException {
		String sql = "SELECT MSG_ID, TITLE, SENDDATE, CONTENT, CHECKED, SENDER, "
				+ "RECEIVER FROM MESSAGE WHERE SENDER=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {mNum});
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Message> msgList = new ArrayList<Message>();
			while (rs.next()) {
				Member sender = new Member();
				sender.setMnum(rs.getInt("sender"));
				Member receiver = new Member();
				receiver.setMnum(rs.getInt("receiver"));
				boolean checked;
				if (rs.getString("checked").equals("F")) {
					checked = false;
				}
				else {
					checked = true;
				}
				Message msg = new Message (
					rs.getInt("msg_id"),
					receiver,
					sender,
					rs.getString("title"),
					rs.getDate("sendDate"),
					rs.getString("content"),
					checked				
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
