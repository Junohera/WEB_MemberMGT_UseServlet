package com.juno.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.juno.dto.MemberDTO;

public class MemberDAO {
	private MemberDAO() {} // 생성자 외부호출 차단
	// 클래스내부에서만 호출가능한 생성자를 통해 인스턴스화가 아닌 프로그램 최초 구동시부터 유효한 static변수에 인스턴스를 할당
	private static MemberDAO ist = new MemberDAO();
	// 외부에서 인스턴스화는 불가능하지만 프로그램 최초에 만들어둔 객체를 반환.(어디서든 동일한 주소를 참조 => ㅁㅇㄷ)
	public static MemberDAO getIst() { return ist; }

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String uid = "juno";
	private static final String pass = "juno";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	

	public MemberDTO selectMember(String userid) {
		MemberDTO member = null;
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, pass);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = new MemberDTO();
				member.setName(rs.getString("NAME"));
				member.setUserid(rs.getString("USERID"));
				member.setPwd(rs.getString("PWD"));
				member.setEmail(rs.getString("EMAIL"));
				member.setPhone(rs.getString("PHONE"));
				member.setAdmin(rs.getInt("ADMIN"));
			}

		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {e.printStackTrace();}
		}
		
		return member;
	}


	public int confirmId(String userid) {
		int result = 0;
		
		String sql = "SELECT USERID FROM MEMBER WHERE USERID = ?";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, pass);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1; // 아이디 중복되어 사용불가
			} else {
				result = 0;
			}

		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {e.printStackTrace();}
		}
		
		return result;
	}


	public int insertMember(MemberDTO member) {
		int result = 0;
		String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, pass);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getUserid());
			pstmt.setString(3, member.getPwd());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setInt(6, member.getAdmin());

			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {e.printStackTrace();}
		}
		
		return result;
	}

}
