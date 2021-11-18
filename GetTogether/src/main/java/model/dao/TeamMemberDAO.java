package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;
import model.Project;
import model.TeamMember;

public class TeamMemberDAO {
	private JDBCUtil jdbcUtil = null;
	
	public TeamMemberDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	public int create(Project project, Member member) throws SQLException{
		String sql = "INSERT INTO USERINFO VALUES (?, ?, false)";
		Object[] param = new Object[] {project.getPid(), member.getMnum()};
		jdbcUtil.setSqlAndParameters(sql, param);
		
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
	

	public List<TeamMember> findMembersInProject(int pid) throws SQLException {
        String sql = "SELECT Mnum FROM TeamMember "
     				+ "WHERE pid = ? and approve = true";                         
		jdbcUtil.setSqlAndParameters(sql, new Object[] {pid});	// JDBCUtil�� query���� �Ű� ���� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			List<TeamMember> memList = new ArrayList<TeamMember>();	// member���� ����Ʈ ����
			while (rs.next()) {
				TeamMember member = new TeamMember(			// ��ü�� �����Ͽ� ���� ���� ������ ����
					pid,
					rs.getInt("mnum"),
					rs.getBoolean("approve"));
				memList.add(member);			// List�� ���� ��ü ����
			}		
			return memList;					
				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public int getNumberOfUsersInProject(int pid) {
		String sql = "SELECT COUNT(mnum) FROM TeamMember "
     				+ "WHERE pid = ? and approve = true";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {pid});	// JDBCUtil�� query���� �Ű� ���� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			rs.next();										
			return rs.getInt(1);			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return 0;
	}
}
