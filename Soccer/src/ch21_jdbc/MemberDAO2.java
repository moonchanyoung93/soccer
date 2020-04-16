package ch21_jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MemberDAO2 {
	public static Connection getConn() throws Exception {
		FileInputStream f=new FileInputStream("d:\\oracle.prop");
		Properties prop = new Properties();
		prop.load(f);
		String driver=prop.getProperty("driver");
		String url=prop.getProperty("url");
		String id=prop.getProperty("id");
		String password=prop.getProperty("password");
		
		Connection conn=null;
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,id,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static int memberDelete(String userid) {
		Connection conn=null;
		PreparedStatement pstmt =null;
		int rows = 0;
		try {
			conn=getConn();
			String sql= "delete from member where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rows=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return rows;
	}
		
	}
	
	

