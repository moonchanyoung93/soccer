package ch21_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDatabase {
	public static void main(String[] args) {
		//DB연결
		String url = 
			"jdbc:mysql://localhost/java?serverTimezone=UTC";
		String id="java"; //mysql 접속 아이디
		String password="java1234"; //mysql 접속 비밀번호
		Connection conn=null; //DB접속처리
		Statement stmt=null; //sql명령어 실행
		ResultSet rs=null; //select 명령어의 결과셋을 조회(레코드 탐색)
		
		try {
			//jdbc driver를 로딩함
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버를 로딩했습니다.");
			//getConnection(연결문자열, 아이디,비밀번호)
			conn=DriverManager.getConnection(url,id,password);
			System.out.println("mysql에 접속 성공...");
			
			String sql="select*from books";//sql작성
			stmt = conn.createStatement();//sql 실행객체
			
			//select 쿼리를 실행한 결과를 결과셋으로 수행
			rs= stmt.executeQuery(sql);
			while(rs.next()) {//다음 레코드가 존재하면
				//결과셋.get자료형("필드명")
				int book_id=rs.getInt("book_id");
				String title = rs.getString("title");
				String publisher = rs.getString("publisher");
				String year = rs.getString("year");
				int price = rs.getInt("price");
				System.out.println(book_id+"\t"+title+"\t"+publisher+"\t"+year+"\t"+price);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {//DB연동 관련 리소스 정리
			//open(Connection->Statement->ResultSet)
			//close(ResultSet->Statement->Connection)
			try {
				if(rs !=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				
			}
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
