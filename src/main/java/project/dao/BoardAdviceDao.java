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

import project.entity.BoardAdvice;


public class BoardAdviceDao {

	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/mini");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public BoardAdvice getBoardAdvice(int bid) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM boardadvice"
					+ "	WHERE bid=?";
		BoardAdvice boardAd = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				boardAd = new BoardAdvice(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), LocalDate.parse(rs.getString(5).split(" ")[0]),
						rs.getInt(6), rs.getInt(7), rs.getInt(8));
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardAd;
	}

	// field 값은 title, content, uid 등 attribute name
	// query 값은 검색어
	public List<BoardAdvice> getBoardAdviceList(String field, String query, int num, int offset) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM boardadvice"
					+ " where " + field + " like ?"
					+ "	ORDER BY bid DESC "
					+ "	LIMIT ? OFFSET ?";
		List<BoardAdvice> list = new ArrayList<BoardAdvice>();
		BoardAdvice boardAd = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, query);
			pstmt.setInt(2, num);
			pstmt.setInt(3, offset);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				boardAd = new BoardAdvice(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), LocalDate.parse(rs.getString(5).split(" ")[0]),
						rs.getInt(6), rs.getInt(7), rs.getInt(8));
				System.out.println(boardAd);
				list.add(boardAd);
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void insertBoardAdvice(BoardAdvice boardAd) {
		Connection conn = getConnection();
		String sql = "insert into boardadvice values (default, ?, ?, ?, default, default, default, default)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardAd.getTitle());
			pstmt.setString(2, boardAd.getContent());
			pstmt.setString(3, boardAd.getUid());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateBoardAdvice(BoardAdvice boardAd) {
		Connection conn = getConnection();
		String sql = "update boardadvice set title=?, content=?, modTime=now() where bid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardAd.getTitle());
			pstmt.setString(2, boardAd.getContent());
			pstmt.setInt(3, boardAd.getBid());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBoardAdvice(int bid) {
		Connection conn = getConnection();
		String sql = "delete boardadvice where bid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// field 값은 view 또는 reply
	public void increaseCount(String field, int bid) {
		Connection conn = getConnection();
		String sql = "UPDATE boardadvice SET " + field + "Count=" + field + "Count+1 WHERE bid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getBoardAdviceCount(String field, String query) {
		Connection conn = getConnection();
		query = "%" + query + "%";
		String sql = "SELECT COUNT(bid) FROM boardadvice"
				+ "  JOIN users ON boardadvice.uid=users.uid"
				+ "  WHERE " + field + " LIKE ?";
		int count = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
}