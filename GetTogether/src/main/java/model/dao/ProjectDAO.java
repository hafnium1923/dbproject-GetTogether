package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import model.Member;
import model.Project;

public class ProjectDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public ProjectDAO() {jdbcUtil = new JDBCUtil(); }
	/**
	 * ���� ���̺� ���ο� �� ���� (PK ���� Sequence�� �̿��Ͽ� �ڵ� ����)
	 */
	public Project create(Project project) throws  SQLException{
		/*pid ������ ����*/
		String sql = "INSERT INTO Project VALUES (seq_pid.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?, 0, 0)";
		long time = project.getExecutionStart().getTime();
		java.sql.Date ExecutionStart = new java.sql.Date(time);
		time = project.getExecutionEnd().getTime();
		java.sql.Date ExecutionEnd = new java.sql.Date(time);
		time = project.getApplicationStart().getTime();
		java.sql.Date ApplicationStart = new java.sql.Date(time);
		time = project.getApplicationEnd().getTime();
		java.sql.Date ApplicationEnd = new java.sql.Date(time);
		Object[] param = new Object[] {project.getTitle(),project.getField(),project.getLanguage(),project.getSubtitle()
				,ExecutionStart,ExecutionEnd,ApplicationStart
				,ApplicationEnd,project.getGoal(),project.getApplicationNum(),project.getDescription()
				,project.getMnum()};
		jdbcUtil.setSqlAndParameters(sql, param);
		String key[] = {"pid"};
		try {
			jdbcUtil.executeUpdate(key);  // insert �� ����
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // ������ PK ��
		   		project.setPid(generatedKey); 	// id�ʵ忡 ����  
		   	}
		   	return project;
		}  	catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return null;
	}
	/**
	 * ������ ������Ʈ ������ ����
	 */
	public int update (Project project) throws SQLException{
		String sql = "UPDATE Project "
				+ "SET title=?, field=?, language=?, subtitle=?, executionStart=?, executionEnd=?,applicationStart=?,applicationEnd=?,goal=?,applicationNum=?,description=?"
				+ "WHERE pid=?";
		long time = project.getExecutionStart().getTime();
		java.sql.Date ExecutionStart = new java.sql.Date(time);
		time = project.getExecutionEnd().getTime();
		java.sql.Date ExecutionEnd = new java.sql.Date(time);
		time = project.getApplicationStart().getTime();
		java.sql.Date ApplicationStart = new java.sql.Date(time);
		time = project.getApplicationEnd().getTime();
		java.sql.Date ApplicationEnd = new java.sql.Date(time);
		
		Object[] param = new Object[] {
				project.getTitle(),
				project.getField(),
				project.getLanguage(),
				project.getSubtitle(),
				ExecutionStart,
				ExecutionEnd,
				ApplicationStart,
				ApplicationEnd,
				project.getGoal(),
				project.getApplicationNum(),
				project.getDescription(),
				project.getPid()};
		jdbcUtil.setSqlAndParameters(sql, param);
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
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
	/**
	 * �־��� pid�� �ش��ϴ� ������Ʈ ������ ����.
	 */
	public int remove(int pid) throws SQLException {
		String sql = "DELETE FROM Project WHERE pid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {pid});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
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

	  	public Project findProject(int pid) throws SQLException {
        String sql = "SELECT title, field, language, subtitle, executionStart, executionEnd, applicationStart, applicationEnd, goal, applicationNum, description, approve, mnum, recommendCnt, lookupCnt "
        			+ "FROM Project "
        			+ "WHERE pid=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {pid});	// JDBCUtil�� query���� �Ű� ���� ����
		Project project = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				project = new Project(		// project ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
					pid,
					rs.getString("title"),
					rs.getString("field"),
					rs.getString("language"),
					rs.getString("subtitle"),
					rs.getDate("executionStart"),
					rs.getDate("executionEnd"),
					rs.getDate("applicationStart"),
					rs.getDate("applicationEnd"),
					rs.getString("goal"),
					rs.getInt("applicationNum"),
					rs.getString("description"),
					rs.getBoolean("approve"),
					rs.getInt("mnum"),
					rs.getInt("recommendCnt"),
					rs.getInt("lookupCnt"));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return project;
	}

	  	public List<Project> searchProject(String query) throws SQLException {
			String sql = "SELECT * FROM PROJECT WHERE TITLE LIKE ? OR SUBTITLE LIKE ?";
			Object[] param = new Object[] { "%"+query+"%" , "%"+query+"%" };

			jdbcUtil.setSqlAndParameters(sql, param);

			System.out.println("sql: "+sql + param[0] + param[1]);
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				List<Project> projectList = new ArrayList<Project>();

				while (rs.next()) {
					Project project = new Project();

					project.setPid(rs.getInt("pid"));
					project.setTitle(rs.getString("title"));
					project.setField(rs.getString("field"));
					project.setLanguage(rs.getString("language"));
					project.setSubtitle(rs.getString("subtitle"));
					project.setExecutionStart(rs.getDate("executionStart"));
					project.setExecutionEnd(rs.getDate("executionEnd"));
					project.setApplicationStart(rs.getDate("applicationStart"));
					project.setApplicationEnd(rs.getDate("applicationEnd"));
					project.setApplicationNum(rs.getInt("applicationNum"));
					project.setGoal(rs.getString("goal"));
					project.setDescription(rs.getString("description"));
					project.setApprove(rs.getBoolean("approve"));
					project.setMnum(rs.getInt("mnum"));
					project.setRecommendCnt(rs.getInt("recommendCnt"));
					project.setLookupCnt(rs.getInt("lookupcnt"));

					projectList.add(project);
				}
				return projectList;
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close();
			}
			return null;		
		}

}
