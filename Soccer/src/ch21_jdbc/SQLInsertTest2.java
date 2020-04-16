package ch21_jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class SQLInsertTest2 {

	public static void main(String[] args) throws Exception{
		FileInputStream fis = new FileInputStream("d:\\db2.prop");
		
		Properties prop=new Properties();
		prop.load(fis);
		
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String id = prop.getProperty("id");
		String pass = prop.getProperty("password");
		
		System.out.println(driver);
		System.out.println(url);
		System.out.println(id);
		System.out.println(pass);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,id,pass);
			String title ="mySQL 기초";
			String year = "2016";
			int price=20000;
			
			String sql="insert into books (title, year, price) values(?,?,?)";
			
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, year);
			stmt.setInt(3, price);
			stmt.executeUpdate();
			System.out.println("추가완료.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null)stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		
	}
}
