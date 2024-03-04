package project.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.entity.User;

/*
 * 
 */
public class UserDao {
	public Connection getConnection() {
		Connection conn = null;
		try {
			// JNDI를 이용하기 위한 객체 생성: 이름으로 객체 찾기 o
			Context initContext = new InitialContext();

			
			// DB 위치값 찾아 ds에 저장  ** bbs 수정할 것 
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/mini"); 
			
			// DB와 연결 
			conn = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/*
	 *  User uid를 넣으면
	 *  uid, pwd, uname, email, regDate, isDeleted 가져옴
	 */
	public User getUserByUid(String uid) {
		Connection conn = getConnection();
		String sql = "select * from users where uid=?";
		User user = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5),LocalDate.parse(rs.getString(6)), rs.getInt(7));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/* 몇 명을 보여줄 지와 몇 페이지부터 보여줄지 넣으면
	 * User 목록 보여줌. 먼저 가입한 사람 순서대로
	 */
	public List<User> getUserList(int num, int offset) { // num: 1 페이지에 몇명을 보여줄 건지, offset: 몇 페이지 건너뛸 것인지 
		Connection conn = getConnection();
		String sql = "select * from users where isDeleted!=1" + " order by regDate desc, uid limit ? offset ?";
		 
		List<User> list = new ArrayList<User>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, offset);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5),LocalDate.parse(rs.getString(6)), rs.getInt(7));
				list.add(user);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * 회원가입한 User 추가 
	 */
	public void insertUser(User user) {
		Connection conn = getConnection();
		String sql = "insert users values (?, ?, ?, ?, ?,  default, default)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getUname());
			pstmt.setString(4, user.getNickName());
			pstmt.setString(5, user.getEmail());

			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * User의 개인정보 수정(pwd, 닉네임, email)
	 */
	public void updateUser(User user) {
		Connection conn = getConnection();
		String sql = "update users set pwd=?, uname=?, nickName=? ,email=? where uid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPwd());
			pstmt.setString(2, user.getUname());
			pstmt.setString(3, user.getNickName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getUid());

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * uid를 넣으면 
	 * isDeleted 속성을 1로 봐꿔서 삭제로 취급 
	 */
	public void deleteUser(String uid) {
		Connection conn = getConnection();
		String sql = "update users set isDeleted=1 where uid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * User 목록에 있는 User의 숫자 보여줌
	 */
	public int getUserCount() {
		 
		Connection conn = getConnection();
		String sql = "select count(uid) from users where isDeleted=0";
		int count = 0;
		
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				count = rs.getInt(1); 
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
