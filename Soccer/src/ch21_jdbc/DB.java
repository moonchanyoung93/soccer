package ch21_jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {
	public static Connection dbConn() {
		Connection conn=null;
		try {
			FileInputStream fis=new FileInputStream("d:\\db.prop");
			Properties prop=new Properties();//key와 value를 세트로 저장
			prop.load(fis);
			
			String url=prop.getProperty("url");
			String id=prop.getProperty("id");
			String password=prop.getProperty("password");
			//mysql에 접속
			conn=DriverManager.getConnection(url,id,password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}//end dbConn()
	
	//오라클 접속용
	public static Connection hrConn() {
		Connection conn=null;
		try {
			FileInputStream fis=new FileInputStream("d:\\hr.prop");
			Properties prop=new Properties();
			prop.load(fis);
			
			String url=prop.getProperty("url");
			String id=prop.getProperty("id");
			String password=prop.getProperty("password");
			conn=DriverManager.getConnection(url,id,password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//오라클(java계정) 접속용
	public static Connection oraConn() {
		Connection conn=null;
		try {
			FileInputStream fis=new FileInputStream("d:\\oracle.prop");
			Properties prop=new Properties();
			prop.load(fis);
			
			String url=prop.getProperty("url");
			String id=prop.getProperty("id");
			String password=prop.getProperty("password");
			conn=DriverManager.getConnection(url,id,password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}

}
