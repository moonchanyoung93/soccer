package ch22_oracle_jdbc;

import java.util.Scanner;

public class MemberDelete {
public static void main(String[] args) {
	Scanner scan=new Scanner(System.in);
	System.out.print("삭제할 아이디를 입력하세요 : ");
	String userid=scan.next();
	scan.close();
	int rows=MemberDAO.memberDelete(userid);
	if(rows>0) {
		System.out.println("삭제!");
	}
}
}
