package ch21_jdbc;

import java.util.List;
import java.util.Scanner;

public class AddressManage {
	
	AddressDAO dao=new AddressDAO();
	
	void del() {
		Scanner scan=new Scanner(System.in);
		System.out.println("삭제할 이름을 입력하세요.");
		String name=scan.next();
		int result=dao.delete(name);
		if(result==1) {
			System.out.println("삭제 완료!");
		}else {
			System.out.println("이름을 확인해주세요!");
		}
	}//end del()
	
	void ins() {
		Scanner scan=new Scanner(System.in);
		System.out.println("이메일 : ");
		String email=scan.next();
		System.out.println("이름 : ");
		String name=scan.next(); 
		System.out.println("주소 :");
		String addr=scan.next();
		System.out.println("연락처 :");
		String hp =scan.next();
		AddressDTO dto=new AddressDTO(email, name, addr, hp);
		dao.insert(dto);
		System.out.println("저장되었습니다.");
	}
	
	void li() {
		List<AddressDTO> items=dao.list();
		System.out.println("==============================");
		System.out.println("이메일\t\t이름\t주소\t연락처");
		System.out.println("==============================");
		for(AddressDTO dto : items) {
			System.out.print(dto.getEmail()+"\t");
			System.out.print(dto.getName()+"\t");
			System.out.print(dto.getAddr()+"\t");
			System.out.print(dto.getHp()+"\n");
		}
	}
	
	
	public static void main(String[] args) {
		AddressManage ad=new AddressManage();
		Scanner scan=new Scanner(System.in);
		while(true) {
			System.out.print("메뉴를 선택하세요(1:주소목록 2:주소록등록 3:주소록삭제 0:종료):");
			int c=scan.nextInt();
			switch(c) {
			case 0:
				System.out.println("종료됩니다!");
				System.exit(0);
				break;
				
			case 1: ad.li(); break;
			case 2: ad.ins();break;
			case 3: ad.del();break;
			
			}
			
		}
		
	}
	

}
