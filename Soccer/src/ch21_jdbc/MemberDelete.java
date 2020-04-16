package ch21_jdbc;

import java.util.Scanner;

public class MemberDelete {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("삭제 무엇?");
		String userid = scan.next();
		scan.close();
		int rows = MemberDAO.memberDelete(userid);

		if(rows >0) {
			System.out.println("삭제.");
		}
	}
}
