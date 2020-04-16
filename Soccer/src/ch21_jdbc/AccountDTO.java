package ch21_jdbc;

public class AccountDTO {
	private String bank;
	private String num;
	private String name;
	private int money;
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "AccountDTO [bank=" + bank + ", num=" + num + ", name=" + name + ", money=" + money + "]";
	}
	public AccountDTO(String bank, String num, String name, int money) {
		this.bank = bank;
		this.num = num;
		this.name = name;
		this.money = money;
	}
	public AccountDTO() {
	}
	
	

}
