package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import project.entity.Board;

public class BoardDao {
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

	public Board getBoard(int bid) {
		Connection conn = getConnection();
		Board board = null;
		String sql = "SELECT b.*, u.uname FROM board b" + "	JOIN users u ON b.uid=u.uid" + " WHERE b.bid=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, bid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						LocalDateTime.parse(rs.getString(5).replace(" ", "T")), rs.getInt(6), rs.getInt(7),
						rs.getInt(8), rs.getString(9));
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	// field 값은 title, content, uid 등 attribute name
	// query 값은 검색어- attribute name

	//
	public List<Board> getBoardsList(String field, String query, int num, int offset) {
		Connection conn = getConnection();
		String sql = "SELECT b.*, u.uname FROM board b" + " JOIN users u ON b.uid=u.uid" + " WHERE b.isDeleted=0 AND "
				+ field + " LIKE ?" + " ORDER BY bid desc " + " LIMIT ? OFFSET ?";

		List<Board> list = new ArrayList<Board>();

		try {
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);

			// field는 sql문에 직접 넣어서 여기서 작업 x
			pstmt.setString(1, query);
			pstmt.setInt(2, num);
			pstmt.setInt(3, offset);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4),LocalDateTime.parse(rs.getString(5).replace(" ", "T")), 
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
				list.add(board);
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void insertBoard(Board board) {
		Connection conn = getConnection();
		String sql = "insert board values (default, ?, ?, ?, default, default, default, default)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getUid());

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBoard(Board board) {
		Connection conn = getConnection();
		// user ID를 건드리는 것이 아니기에 uid 필요 x 
		String sql = "update board set title=?, content=?, modTime=now() where bid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			// modeTime은 DB에서 자동으로 처리
			pstmt.setInt(3, board.getBid());

			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBoard(int bid) {
		Connection conn = getConnection();
		String sql = "update board set isDeleted=1 where bid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);

			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// field 값은 view 또는 reply(view 또는 reply값 증가 시키기 1씩)
	// 잘 모름
	public void increaseCount(String field, int bid) {
		Connection conn = getConnection();
		String sql = "UPDATE board SET " + field + "Count=" + field + "Count+1 WHERE bid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, bid);
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int getBoardCount(String field, String query) {
		Connection conn = getConnection();
		// uname으로 할 시 검색이 안돼서 수정함: 파라미터 field, query 추가 및 sql 수정 
		query = "%" + query + "%";
		String sql = "SELECT COUNT(bid) FROM board"
				+ "	JOIN users ON board.uid=users.uid"
				+ "	WHERE board.isDeleted=0 and " +  field + " like ?";
		int count = 0;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
