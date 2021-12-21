package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;
import model.MemberRecommend;
import model.MemberSplit;
import model.Project;
import model.TeamRecommend;

public class TeamRecommendDAO {
private JDBCUtil jdbcUtil = null;
	
	public TeamRecommendDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}

	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<TeamRecommend> findRecomendTeam() throws SQLException {
        String sql = "SELECT pid, title, subtitle, lookupcnt, recommendcnt " 
        		   + "FROM project "
        		   + "ORDER BY lookupcnt DESC";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<TeamRecommend> teamRecommendList = new ArrayList<TeamRecommend>();	
			while (rs.next()) {
				TeamRecommend teamRecommend = new TeamRecommend(
					rs.getInt("pid"),
					rs.getString("title"),
					rs.getString("subtitle"),
					rs.getInt("lookupcnt"),
					rs.getInt("recommendcnt"));
				teamRecommendList.add(teamRecommend);			
			}		
			return teamRecommendList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public MemberSplit memberSplit(int mnum) throws SQLException{
		String sql = "SELECT mnum, field, language " 
     		   + "FROM member "
     		   + "WHERE mnum = ? ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {mnum});
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			MemberSplit memberSplit = null;	
			while (rs.next()) {
				memberSplit = new MemberSplit(
					mnum,
					rs.getString("field"),
					rs.getString("language"));
			}		
			return memberSplit;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	
	public List<TeamRecommend> findIdentifyRecommendTeam(String field) throws SQLException {
        String sql1 = "SELECT pid, title, subtitle, lookupcnt, recommendcnt " 
        		   + "FROM project "
        		   + "WHERE field = ?";
        
        jdbcUtil.setSqlAndParameters(sql1, new Object[] {field});
		//jdbcUtil.setSqlAndParameters(sql2, new Object[] {language});
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<TeamRecommend> teamRecommendList = new ArrayList<TeamRecommend>();	
			while (rs.next()) {
				TeamRecommend teamRecommend = new TeamRecommend(
					rs.getInt("pid"),
					rs.getString("title"),
					rs.getString("subtitle"),
					rs.getInt("lookupcnt"),
					rs.getInt("recommendcnt"));
				teamRecommendList.add(teamRecommend);			
			}		
			return teamRecommendList;					
			
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
