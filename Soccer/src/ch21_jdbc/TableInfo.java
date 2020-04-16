package ch21_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class TableInfo {

	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//테이블에 대한 부가적인 설명 데이터
		ResultSetMetaData meta=null;
		try {
			conn=DB.dbConn();
			String sql="select*from score";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			meta= rs.getMetaData();//ResultSet 으로부터 메타데이터(테이블의 정보)를 가져옴
			int cols=meta.getColumnCount();//컬럼의 갯수
			System.out.println("필드명\t\t자료형\t\tNull여부");
			for(int i=1; i<=cols;i++) {
				String colName =meta.getColumnName(i);//i번째 컬럼의 이름
				String colType=meta.getColumnTypeName(i);//i번째 컬럼의 타입
				int isNull=meta.isNullable(i);//i번째 컬럼의  null허용 여부(0: not null, 1: null 허용)
				System.out.println(colName+"\t\t"+colType+"\t\t"+isNull);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null) rs.close();
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
