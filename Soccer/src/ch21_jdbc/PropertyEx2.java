package ch21_jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import com.mysql.cj.protocol.Resultset;

/**db2.prop 파일에 아래와 같은 정보를 저장해보세요. 그리고 출력도 해보세요.

출력내용 :
driver:com.mysql.cj.jdbc.Driver
id:java
password:java1234

저장내용 :
dbName=mySQL
주석은 "propTest"
 */
public class PropertyEx2 {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		Resultset rs=null;

		try {
			FileInputStream fis = new FileInputStream("d:\\db2.prop");
			Properties prop=new Properties();
			prop.load(fis);
			System.out.println(prop);

			String password = prop.getProperty("password");
			System.out.println( "password : " +password);

			prop.setProperty("dbName", "mySQL");
			FileOutputStream fos = new FileOutputStream("d:\\db2.prop");

			prop.store(fos,"propTest");
			System.out.println("변경된 내용이 db2.prop에 저장되었어요~!");


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
