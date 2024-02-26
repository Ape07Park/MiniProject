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

public class UserDao {
	public Connection getConnection() {
		Connection conn = null;
		try {
			// JNDI를 이용하기 위한 객체 생성: 이름으로 객체 찾기 o
			Context initContext = new InitialContext();

			// java:comp/env까지 initCtx의 lookup메서드-찾아서 넣음-
			// 를 이용해서 "java:comp/env" 에 해당하는 객체(톰캣)를 찾아서 evnCtx에 삽입
			// + 부터 envCtx의 lookup메서드를 이용해서 "jdbc/world"에 해당하는 객체(server.xml에 등록한 이름
			// world)를 찾아서 ds에 삽입

			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/bbs"); // "jdbc/world"는
																								// context.xml에서 가져온 거

			// getConnection메서드를 이용해서 커넥션 풀로 부터 커넥션 객체를 얻어내어 conn변수에 저장
			conn = ds.getConnection();

			/*
			 *  위의 코드를 아래와 같이 줄여서 작성 가능하다.  Context context = new InitialContext(); 
			 * DataSource dataSource = (DataSource) context.lookup(
			 * "java:comp/env/jdbc/oracle");             
			 * Connection con = dataSource.getConnection(); 
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	

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
						LocalDate.parse(rs.getString(5)), rs.getInt(6));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getUserList(int num, int offset) {
		Connection conn = getConnection();
		String sql = "select * from users where isDeleted=0" + " order by regDate desc, uid limit ? offset ?";
		// ArrayList는 잘못하면 모양 이상하게 나옴 
		List<User> list = new ArrayList<User>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, offset);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						LocalDate.parse(rs.getString(5)), rs.getInt(6));
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

	public void insertUser(User user) {
		Connection conn = getConnection();
		String sql = "insert users values (?, ?, ?, ?, default, default)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getUname());
			pstmt.setString(4, user.getEmail());

			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		Connection conn = getConnection();
		String sql = "update users set pwd=?, uname=?, email=? where uid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPwd());
			pstmt.setString(2, user.getUname());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUid());

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
	
	public int getUserCount() {
		// 리스트 불러오기 
		Connection conn = getConnection();
		String sql = "select count(uid) from users where isDeleted=0";
		int count = 0;
		
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				count = rs.getInt(1); // uid 가져오는 거 반복
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
