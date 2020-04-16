package ch21_jdbc;

import java.util.List;
import java.util.Scanner;

public class AccountManage {
	
	AccountDAO dao=new AccountDAO();
	
	void del() {
		Scanner scan=new Scanner(System.in);
		System.out.print("삭제할 은행을 입력하세요 :");
		String bank=scan.next();
		int result=dao.delete(bank);
		if(result==1) {
			System.out.println("삭제 완료!");
		}else {
			System.out.println("은행을 확인해주세요!");
		}
	}//end delete

	
	void ins() {
		Scanner scan=new Scanner(System.in);
		System.out.println("은행 : ");
		String bank=scan.next();
		System.out.println("계좌 :");
		String num=scan.next();
		System.out.println("이름 : ");
		String name =scan.next();
		System.out.println("잔고 :");
		int money=scan.nextInt();
		AccountDTO dto=new AccountDTO(bank, num, name, money);
		dao.insert(dto);
		System.out.println("저장완료!");
	}//end ins()
	
	void li() {
		List<AccountDTO> items=dao.list();
		System.out.println("==============================");
		System.out.println("은행\t계좌\t\t이름\t잔고");
		System.out.println("==============================");
		for(AccountDTO dto : items) {
			System.out.print(dto.getBank()+"\t");
			System.out.print(dto.getNum()+"\t");
			System.out.print(dto.getName()+"\t");
			System.out.print(dto.getMoney()+"\n");
		}
	}//end li
	
	public static void main(String[] args) {
		AccountManage acm=new AccountManage();
		Scanner scan=new Scanner(System.in);
		while(true) {
			System.out.println("메뉴를 선택하세요(1:계좌목록 2:계좌등록 3:계좌삭제 0:종료):");
			int c=scan.nextInt();
			switch(c) {
			case 0: 
				System.out.println("종료됩니다!");
				System.exit(0);
				break;
			case 1 : acm.li();break;
			case 2 : acm.ins();break;
			case 3 : acm.del();break;
			}
		}
	}
}
