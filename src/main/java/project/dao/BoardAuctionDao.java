package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.entity.BoardAuction;


public class BoardAuctionDao {

	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/1.mini");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public BoardAuction getBoard(int bid) {
		Connection conn = getConnection();
		String sql = "SELECT b.*, u.uname FROM boardAuction"
					+ "	JOIN users u ON b.uid=u.uid"
					+ "	WHERE b.bid=?";
		BoardAuction boardAuc = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				boardAuc = new BoardAuction();
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardAuc;
	}

//	// field 값은 title, content, uid 등 attribute name
//	// query 값은 검색어
	public List<BoardAuction> getBoardList(String field, String query, int num) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM boardauction";
		List<BoardAuction> list = new ArrayList<BoardAuction>();
		try {
			Statement pstmt = conn.createStatement();

			
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				BoardAuction boardAuc = new BoardAuction(LocalDate.parse(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
				list.add(boardAuc);
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}
//	
	public void insertBoard(BoardAuction boardAuc) {
		Connection conn = getConnection();
		String sql = "insert into boardauction values (default, ?, ?, ?, default, default, default)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardAuc.getNickName());
			pstmt.setString(2, boardAuc.getProcessTitle());
			pstmt.setString(3, boardAuc.getProcessContent());

			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	
//	public void updateBoard(BoardAuction boardAuc) {
//		Connection conn = getConnection();
//		String sql = "update board set title=?, content=?, modTime=now() where bid=?";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, boardAuc.getTitle());
//			pstmt.setString(2, boardAuc.getContent());
//			pstmt.setInt(3, boardAuc.getBid());
//			
//			pstmt.executeUpdate();
//			pstmt.close(); conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void deleteBoard(int bid) {
//		Connection conn = getConnection();
//		String sql = "update board set isDeleted=1 where bid=?";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bid);
//			
//			pstmt.executeUpdate();
//			pstmt.close(); conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	// field 값은 view 또는 reply
//	public void increaseCount(String field, int bid) {
//		Connection conn = getConnection();
//		String sql = "UPDATE board SET " + field + "Count=" + field + "Count+1 WHERE bid=?";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bid);
//			
//			pstmt.executeUpdate();
//			pstmt.close(); conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public int getBoardCount(String field, String query) {
//		Connection conn = getConnection();
//		query = "%" + query + "%";
//		String sql = "SELECT COUNT(bid) FROM board"
//				+ "  JOIN users ON board.uid=users.uid"
//				+ "  WHERE board.isDeleted=0 and " + field + " LIKE ?";
//		int count = 0;
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, query);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				count = rs.getInt(1);
//			}
//			rs.close(); pstmt.close(); conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return count;
//	}
//	
}