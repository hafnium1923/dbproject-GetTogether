package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;
import model.MemberRecommand;
import model.Project;
import model.TeamRecommand;

public class TeamRecommandDAO {
private JDBCUtil jdbcUtil = null;
	
	public TeamRecommandDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}

	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<TeamRecommand> findRecomandTeam() throws SQLException {
        String sql = "SELECT title, subtitle, lookupcnt " 
        		   + "FROM project" ;
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<TeamRecommand> teamRecommandList = new ArrayList<TeamRecommand>();	
			while (rs.next()) {
				TeamRecommand teamRecommand = new TeamRecommand(		
					rs.getString("title"),
					rs.getString("subtitle"),
					rs.getInt("lookupcnt"));
				teamRecommandList.add(teamRecommand);			
			}		
			return teamRecommandList;					
			
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
