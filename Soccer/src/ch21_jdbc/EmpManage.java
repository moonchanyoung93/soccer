package ch21_jdbc;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class EmpManage {
	EmpDAO dao=new EmpDAO();
	
	void delete() {
		Scanner scan=new Scanner(System.in);
		System.out.print("삭제할 사번을 입력하세요 : ");
		int empno=scan.nextInt();
		int result=dao.deleteEmp(empno);
		if(result==1) {
			System.out.println("삭제되었습니다.");
		}else {
			System.out.println("사번을 확인하세요.");
		}
	}
	
	void insert() {
		Scanner scan=new Scanner(System.in);
		System.out.println("사번 : ");
		int empno=scan.nextInt();
		System.out.println("이름 :");
		String ename = scan.next();//next 엔터문자 무시, nextline 엔터문자 포함
		System.out.println("입사일자 : ");
		String hiredate= scan.next();
		System.out.println("급여 : ");
		int sal=scan.nextInt();
		EmpDTO dto=new EmpDTO(empno, ename, Date.valueOf(hiredate), sal);
		dao.insertEmp(dto);
		System.out.println("저~장!");
	}//end insert()
	
	//사원목록 출력
	 void list() {
		 //사원 목록을 받아옴
		 List<EmpDTO> items = dao.listEmp();		 
		 System.out.println("사번\t이름\t입사일자\t\t급여");
		 for(EmpDTO dto : items) {//for(개별변수 : 집합변수)
			 System.out.print(dto.getEmpno()+"\t");
			 System.out.print(dto.getEname()+"\t");
			 System.out.print(dto.getHiredate()+"\t");
			 System.out.print(dto.getSal()+"\n");
		 }
	 }//end list

	 public static void main(String[] args) {
		EmpManage emp=new EmpManage();
		Scanner scan=new Scanner(System.in);
		while(true) {
			System.out.println("메뉴를 선택하세요(1.목록 2.추가 3.삭제 0.종료)");
			int code=scan.nextInt();
			switch(code) {
			case 0:
				System.out.println("종료");
				System.exit(0);
				break;
			case 1 : emp.list(); break;
			case 2 : emp.insert(); break;
			case 3 : emp.delete();break;
			}
		}
	}//end main
}
