package ch21_jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class SQLInsertTest {
	public static void main(String[] args) throws Exception{
		FileInputStream fis = new FileInputStream("d:\\db.prop");
		//Map구조처럼 key와 value를 세트로 관리하는 객체

		Properties prop = new Properties();
		//db.prop 파일을 읽어서 key, value구조로 변환
		prop.load(fis);

		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String id = prop.getProperty("id");
		String password = prop.getProperty("password");

		System.out.println(driver);
		System.out.println(url);
		System.out.println(id);
		System.out.println(password);

		Connection conn=null;
		PreparedStatement stmt=null; //Statement보다 향상된 객체

		try {
			Class.forName(driver);//jdbc driver 로딩,jdk1.6부터 생략가능
			//DB접속
			conn=DriverManager.getConnection(url,id,password);
			String title = "명품 Java";
			String publisher="영진출판사";
			String year="2019";
			int price=30000;
			//sql =>(1)parsing(분석),(2)실행계획수립, (3)sql실행
			String sql="insert into books (title,publisher, year,price) values(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);//첫번째 물음표에 입력될 값, DB는 index가 1번부터이다.
			stmt.setString(2, publisher);
			stmt.setString(3, year);
			stmt.setInt(4, price);
			stmt.executeUpdate();//sql 실행(반드시 있어야 실행됩니다.)
			System.out.println("추가되었습니다.");
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
