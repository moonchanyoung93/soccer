package project11_11;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Vector;

import ch21_jdbc.DB;

public class SoccerDAO {
	//oracle 연결
	public static Connection oraConn() {
		Connection conn= null;
		try {
			FileInputStream fis=new FileInputStream("d:\\oracle.prop");
			Properties prop=new Properties();
			prop.load(fis);

			String url=prop.getProperty("url");
			String id= prop.getProperty("id");
			String password=prop.getProperty("password");
			conn=DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	//리스트
	public Vector listSoccer() {
		Vector items = new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append("select no, name,position,team,age, goal ");
			sb.append(" from soccer " );
			sb.append(" order by position, no");
			pstmt=conn.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				int no=rs.getInt("no");
				String name=rs.getString("name");
				String position =rs.getString("position");
				String team=rs.getString("team");
				int age=rs.getInt("age");
				int goal=rs.getInt("goal");

				row.add(no);
				row.add(name);
				row.add(position);
				row.add(team);
				row.add(age);
				row.add(goal);

				items.add(row);
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
	public int insertSoccer(SoccerDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=oraConn();
			StringBuilder sb= new StringBuilder();
			sb.append("insert into soccer ");
			sb.append(" values(?,?,?,?,?,?)");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPosition());
			pstmt.setString(4, dto.getTeam());
			pstmt.setInt(5, dto.getAge());
			pstmt.setInt(6, dto.getGoal());
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
	}//end insert

	
	//수정
	public int updateSoccer(SoccerDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append("update soccer ");
			sb.append(" set team=?, age=?, goal=?, position=?, no=? ");
			sb.append(" where name=? ");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getTeam());
			pstmt.setInt(2, dto.getAge());
			pstmt.setInt(3, dto.getGoal());
			pstmt.setString(4, dto.getPosition());
			pstmt.setInt(5, dto.getNo());
			pstmt.setString(6, dto.getName());
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
	
	//삭제
	public int deleteSoccer(String name) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append("delete from soccer ");
			sb.append(" where name=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
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
	
	//검색
	public Vector searchSoccer(String name) {
		Vector items =new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append("select no, name, position, team, age,goal ");
			sb.append(" from soccer ");
			sb.append(" where name=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getInt("no"));
				row.add(rs.getString("name"));
				row.add(rs.getString("position"));
				row.add(rs.getString("team"));
				row.add(rs.getInt("age"));
				row.add(rs.getInt("goal"));
				items.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return items;
	}//end search
	
	public SoccerDTO viewSoccer(String name) {
		SoccerDTO dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append(" select no, name, position, team, age, goal ");
			sb.append(" from soccer ");
			sb.append(" where name=? ");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int no=rs.getInt("no");
				String position=rs.getString("position");
				String team=rs.getString("team");
				int age=rs.getInt("age");
				int goal=rs.getInt("goal");
				dto=new SoccerDTO(no, age, goal, name, position, team);
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
	}

	
}


