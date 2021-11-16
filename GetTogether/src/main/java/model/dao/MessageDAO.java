package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Message;

public class MessageDAO {
	private JDBCUtil jdbcUtil = null;
	
	public MessageDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * create new message
	 */
	public int create(Message message) throws SQLException {
		String sql = "INSERT INTO Message VALUES (?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {message.getId(), message.getReceiver(), 
						message.getSender(), message.getTitle(), message.getSendDate(),
						message.getContent(), message.isChecked() };				
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
}
