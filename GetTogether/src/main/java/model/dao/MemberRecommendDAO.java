package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;
import model.MemberRecommend;

public class MemberRecommendDAO {
private JDBCUtil jdbcUtil = null;
	
	public MemberRecommendDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}

	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<MemberRecommend> findRecommendMember() throws SQLException {
        String sql = "SELECT mnum, mname, language " 
        		   + "FROM member" ;
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<MemberRecommend> memberRecommend = new ArrayList<MemberRecommend>();	
			while (rs.next()) {
				MemberRecommend memberRecommendList = new MemberRecommend(		
					rs.getInt("mnum"), 
					rs.getString("mname"),
					rs.getString("language"));
				memberRecommend.add(memberRecommendList);			
			}
			System.out.print(memberRecommend);
			return memberRecommend;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	/**
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingMember(String mid) throws SQLException {
		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {mid});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}
}
