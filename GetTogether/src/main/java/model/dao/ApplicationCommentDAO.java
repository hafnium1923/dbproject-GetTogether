package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ApplicationComment;

public class ApplicationCommentDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ApplicationCommentDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}

	public int create(ApplicationComment comment) throws SQLException {
		String sql = "INSERT INTO ApplicationComment VALUES (seq_appid.nextval, ?, ?, SYSDATE, ?)";		
		Object[] param = new Object[] {
			comment.getContent(),
			comment.getApplicant().getMnum(),
			comment.getProject().getPid()
		};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
		try {    
			int result = jdbcUtil.executeUpdate();  // insert �� ����
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
