package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.entity.Equipment;


public class EquipmentDao {
	public Connection getConnection() {
		Connection conn = null;
		try {
			// JNDI를 이용하기 위한 객체 생성: 이름으로 객체 찾기 o
			Context initContext = new InitialContext();

			// DB 위치값 찾아 ds에 저장 ** bbs 수정할 것
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/mini");

			// DB와 연결
			conn = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Equipment getEquipmentByInum(int inum) {
		Connection conn = getConnection();
		Equipment equip = null;
		String sql = "select * from equipment as l where inum=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inum);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				equip = new Equipment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return equip;
	}
	
	public List<Equipment> getEquipmentList(int num, int offset) { // num: 1 페이지에 몇명을 보여줄 건지, offset: 몇 페이지 건너뛸 것인지 
		Connection conn = getConnection();
		String sql = "select * from equipment" + " order by inum limit ?  offset ?";
		List<Equipment> list = new ArrayList<Equipment>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, offset);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Equipment equip = new Equipment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(equip);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getEquipmentCount() {
		 
		Connection conn = getConnection();
		String sql = "select count(inum) from equipment";
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
