package ch21_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//DAO(Data Access Object)
public class MemberDAO {
	public static Connection getConn() {
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="java";
		String pwd="java1234";
		Connection conn=null;
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, id, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}//end getConn()
	
	//회원정보 삭제
	public static int memberDelete(String userid) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			conn=getConn();
			String sql="delete from member where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rows=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return rows;
	}

}
