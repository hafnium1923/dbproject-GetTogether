package model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import model.Member;

public class MemberDAO {
	private JDBCUtil jdbcUtil = new JDBCUtil();

	public int create(Member member) throws SQLException {
		String query = "INSERT INTO Member VALUES (seq_mnum.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { member.getMid(), member.getPasswd(), member.getMname(),
				member.getDate(), member.getPhonenum(), member.getEmail(), member.getSchool(), member.getMajor(),
				member.getField(), member.getLanguage(), member.getExperience() };

		jdbcUtil.setSqlAndParameters(query, param);

		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	public boolean existingMember(String mid) throws SQLException {
		String sql = "SELECT count(*) FROM MEMBER WHERE mid=?";      
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
	
	public Member findMember(String mid) throws SQLException {
		String query = "SELECT seq_mnum, mid, passwd, mname, date, phonenum, email, school, major, field, language, experience"
				+ "FROM Member WHERE mnum=?";
		
		jdbcUtil.setSql(query);
		
		Object[] param = new Object[] {mid};
		jdbcUtil.setParameters(param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Member member = null;
			
			
			if(rs.next()) {
				member = new Member();
				
				member.setMnum(rs.getInt("mnum"));
				member.setMid(rs.getString("mid"));
				member.setPasswd(rs.getString("passwd"));
				member.setMname(rs.getString("mname"));
				
				//dateŸ�� ó��
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
				Date date = (Date) fm.parse(rs.getString("date"));
				
				member.setDate(date);
				
				member.setPhonenum(rs.getString("phonenum"));
				member.setEmail(rs.getString("email"));
				member.setSchool(rs.getString("school"));
				member.setMajor(rs.getString("major"));
				member.setField(rs.getString("field"));
				member.setLanguage(rs.getString("language"));
				member.setExperience(rs.getString("experience"));
			}
			return member;
		}catch(Exception ex) {
			
		}
		finally {
			jdbcUtil.close();
		}

		return null;
	}
	
	public int update(Member member) throws SQLException {
		String sql = "UPDATE MEMBER "
					+ "SET mid=?, passwd=?, mname=?, date=?, phonenum=?, email=?, school=?, major=?, field=?, "
					+ "language=?, experience=?"
					+ "WHERE mnum=?";
		Object[] param = new Object[] { member.getMid(), member.getPasswd(), member.getMname(), member.getDate(),
				member.getPhonenum(), member.getEmail(), member.getSchool(), member.getMajor(), member.getField(), 
					member.getEmail(), member.getMnum()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
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

	public int remove(String mid) throws SQLException {
		String sql = "DELETE FROM MEMBER WHERE mid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {mid});	// JDBCUtil�� delete���� �Ű� ���� ����

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

}
