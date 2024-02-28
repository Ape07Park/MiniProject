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
import project.entity.BoardBuy;

public class BoardAuctionDao {

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

	public BoardAuction getBoard(int bid, String pack) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM " + pack + " WHERE bid=?";
		BoardAuction boardAuc = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				boardAuc = new BoardAuction(rs.getInt(1), rs.getString(2),
						LocalDate.parse(rs.getString(3).split(" ")[0]), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
				;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardAuc;
	}

//	// field 값은 title, content, uid 등 attribute name
//	// query 값은 검색어
	public List<BoardAuction> getBoardList(String field, String query, int num, int offset, String pack) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM " + pack + " where " + field + " like ? order by bid desc limit ? offset ?";
		List<BoardAuction> list = new ArrayList<BoardAuction>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, query);
			pstmt.setInt(2, num);
			pstmt.setInt(3, offset);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardAuction boardAuc = new BoardAuction(rs.getInt(1), rs.getString(2),
						LocalDate.parse(rs.getString(3).split(" ")[0]), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
				list.add(boardAuc);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public BoardBuy getBuy(int bid, String pack) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM " + pack + " WHERE bid=?";
		BoardBuy boardbuy = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				boardbuy = new BoardBuy(rs.getInt(1), rs.getString(2),
						LocalDate.parse(rs.getString(3).split(" ")[0]), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
				;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardbuy;
	}

//	// field 값은 title, content, uid 등 attribute name
//	// query 값은 검색어
	public List<BoardBuy> getBuyList(String field, String query, int num, int offset, String pack) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM " + pack + " where " + field + " like ? order by bid desc limit ? offset ?";
		List<BoardBuy> list = new ArrayList<BoardBuy>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, query);
			pstmt.setInt(2, num);
			pstmt.setInt(3, offset);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardBuy boardAuc = new BoardBuy(rs.getInt(1), rs.getString(2),
						LocalDate.parse(rs.getString(3).split(" ")[0]), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
				list.add(boardAuc);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	
	public void insertBoard(BoardAuction boardAuc, String pack) {
		Connection conn = getConnection();
		String sql = "insert into " + pack
				+ " values (default, ?, default, ?, ?, ?, default, default, default, default)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardAuc.getUid());
			pstmt.setString(2, boardAuc.getNickName());
			pstmt.setString(3, boardAuc.getProcessTitle());
			pstmt.setString(4, boardAuc.getProcessContent());

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateBoard(BoardAuction boardAuc, String pack) {
		Connection conn = getConnection();
		String sql = "update " + pack + " set processTitle=?, processContent=? where bid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardAuc.getProcessTitle());
			pstmt.setString(2, boardAuc.getProcessContent());
			pstmt.setInt(3, boardAuc.getBid());

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBoard(int bid, String pack) {
		Connection conn = getConnection();
		String sql = "delete from " + pack + " where bid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
	public int getBoardCount(String field, String query, String pack) {
		Connection conn = getConnection();
		query = "%" + query + "%";
		String sql = "SELECT COUNT(bid) FROM " + pack + "  WHERE " + field + " LIKE ?";
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
//	
}