package ch21_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MotorDAO2 {
	public MotorDTO2 search(String company) {
		MotorDTO2 dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.dbConn();
			String sql= "select li_number, company, year, eff from motor where company=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, company);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String li_number=rs.getString("li_number");
				int year=rs.getInt("year");
				int eff=rs.getInt("eff");
				dto=new MotorDTO2(li_number, company, year, eff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt !=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null ) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			}//end finally
		return dto;
	}
	
	

}
