package ch21_jdbc;

import java.util.Scanner;

public class MotorSearch {

	void print(MotorDTO2 dto) {
		System.out.println("차량번호 : "+dto.getLi_number());
		System.out.println("제조사 : " +dto.getCompany());
		System.out.println("제조 년도 : "+dto.getYear());
		System.out.println("연비 : "+dto.getEff());
	}
	
	public static void main(String[] args) {
		MotorSearch s=new MotorSearch();
		Scanner scan=new Scanner(System.in);
		System.out.print("회사를 입력하세요 :");
		String company=scan.nextLine();
		MotorDAO2 dao=new MotorDAO2();
		MotorDTO2 dto= dao.search(company);
		s.print(dto);
		scan.close();
	}
}
