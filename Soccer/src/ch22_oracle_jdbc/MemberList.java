package ch22_oracle_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberList {
	public static void main(String[] args) {
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="java";
		String pwd="java1234";

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);//jdbc driver 로딩
			conn=DriverManager.getConnection(url, id, pwd);
			System.out.println("오라클 접속되었습니다.");

			String sql="select*from member";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			System.out.println("아이디\t비밀번호\t이름");

			while(rs.next()) {
				String userid=rs.getString("userid");
				String passwd=rs.getString("passwd");
				String name =rs.getString("name");
				System.out.println(userid+"\t"+passwd+"\t"+name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null)pstmt.close();
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
