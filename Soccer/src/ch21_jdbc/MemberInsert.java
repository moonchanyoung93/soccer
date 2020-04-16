package ch21_jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

public class MemberInsert {
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("d:\\oracle.prop");

		//key와 value를 세트로 관리하는 객체
		Properties prop=new Properties();
		prop.load(fis);
		String driver=prop.getProperty("driver");
		String url=prop.getProperty("url");
		String id=prop.getProperty("id");
		String password=prop.getProperty("password");

	
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName(driver);//(생략가능)

			//오라클 서버에 접속
			conn=DriverManager.getConnection(url, id, password);
			Scanner scan = new Scanner(System.in);
			System.out.print("아이디 : ");
			String userid = scan.nextLine();//한 라인을 입력
			System.out.println("비밀번호 :");
			String passwd = scan.nextLine();
			System.out.println("이름 : ");
			String name = scan.nextLine();

			String sql="insert into member(userid,passwd,name)" +
					"values(?,?,?)";

			stmt =conn.prepareStatement(sql);
			stmt.setString(1,userid);
			stmt.setString(2, passwd);
			stmt.setString(3, name);
			stmt.executeUpdate();//실행
			System.out.println("저장되었습니다.");


		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt !=null)stmt.close();
				
			} catch (Exception e2) {
e2.printStackTrace();
			}
			try {
				if(conn !=null)conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			
		}


	}

}
