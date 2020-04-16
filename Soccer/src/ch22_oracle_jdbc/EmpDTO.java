package ch22_oracle_jdbc;

import java.sql.Date;

public class EmpDTO {

	String ename;
	int empno, sal;
	String hiredate;
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	@Override
	public String toString() {
		return "EmpDTO [ename=" + ename + ", empno=" + empno + ", sal=" + sal + ", hiredate=" + hiredate + "]";
	}
	public EmpDTO(String ename, int empno, int sal, String hiredate) {
		this.ename = ename;
		this.empno = empno;
		this.sal = sal;
		this.hiredate = hiredate;
	}
	public EmpDTO() {
	}
	
	
	
}
