package ch22_oracle_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import ch21_jdbc.DB;

public class ScoreDAO {
	
	public Vector listScore() {
		//ArrayList는 JTable과 잘 호환이 안되므로 vector 사용
		Vector items =new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.oraConn();
			StringBuilder sb=new StringBuilder();//String 방식보다는 효율적
			sb.append("select student_no, name, kor, eng, mat");
			sb.append(", (kor+eng+mat) tot");
			sb.append(", round((kor+eng+mat)/3.0,2) avg ");
			sb.append(" from score ");
			sb.append(" order by name");
			pstmt = conn.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row = new Vector();
				String student_no= rs.getString("student_no");
				//String student_no= rs.getString(1);//가능하나 번호는 가독성이 떨어진다
				String name= rs.getString("name");
				int kor=rs.getInt("kor");
				int eng=rs.getInt("eng");
				int mat=rs.getInt("mat");
				int tot=rs.getInt("tot");
				double avg=rs.getDouble("avg");
				
				row.add(student_no);
				row.add(name);
				row.add(kor);
				row.add(eng);
				row.add(mat);
				row.add(tot);
				row.add(avg);
				items.add(row);//벡터에 1개의 행을 추가 (2차원 벡터)
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
		return items;
		
	}
		
	//추가
	public int insertScore(ScoreDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append("insert into score (student_no, name, kor, eng, mat) ");
			sb.append(" values(?,?,?,?,?)");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getStudent_no());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMat());
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
		return result;
	}
	/*[레코드를 전달하는 방법]
	 * 1. primary key만 전달 ->가장 적절
	 * 2. 선택한 행을 dto 전달 ->단, 여러사용자가 사용중에 중간에 변경이 있을경우 반영이 제대로 안될수도 있다.
	 * 3. static 변수에 저장 ->코드양이 줄지만, 객체지향방식에서는 안맞는다, 또한 무거운 데이터가 들어가면 메모리 낭비가 심하다.
	 */
	
	
	//수정메소드
	public int updateScore(ScoreDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append("update score ");
			sb.append(" set name =?, kor=?, eng=?, mat=? ");
			sb.append(" where student_no=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3, dto.getEng());
			pstmt.setInt(4, dto.getMat());
			pstmt.setString(5, dto.getStudent_no());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
		return result;
	}//end update
	
	public ScoreDTO viewScore(String student_no) {
		ScoreDTO dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append("select student_no, name, kor, eng, mat");
			sb.append(", (kor+eng+mat) tot");
			sb.append(", round((kor+eng+mat)/3.0,2) avg ");
			sb.append(" from score ");
			sb.append(" where student_no=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, student_no);
			rs=pstmt.executeQuery();
		if(rs.next()) {//레코드가 한개라서 if로 해도됨 while굳이 안써도됨
			String name=rs.getString("name");
			int kor=rs.getInt("kor");
			int eng=rs.getInt("eng");
			int mat=rs.getInt("mat");
			dto=new ScoreDTO(student_no, name, kor, eng, mat);
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
		return dto;
	}//end view
	
	//삭제
	public int deleteScore(String student_no) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append("delete from score");
			sb.append("where student_no =?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, student_no);
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
		return result;
	}
	

}
