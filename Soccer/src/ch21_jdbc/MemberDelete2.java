package ch21_jdbc;

import java.util.Scanner;

public class MemberDelete2 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("무엇을 삭제하시겠습니까~?");
		String userid=scan.next();
		scan.close();
		int rows=MemberDAO2.memberDelete(userid);
		
		if(rows>0) {
			System.out.println("삭.제.완.료.");
		}
		
	}

}
