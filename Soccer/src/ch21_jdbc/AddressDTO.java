package ch21_jdbc;

public class AddressDTO {
	
	String email;
	String name;
	String addr;
	String hp;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	
	@Override
	public String toString() {
		return "AddressDTO [email=" + email + ", name=" + name + ", addr=" + addr + ", hp=" + hp + "]";
	}
	
	public AddressDTO(String email, String name, String addr, String hp) {
		this.email = email;
		this.name = name;
		this.addr = addr;
		this.hp = hp;
	}
	
	public AddressDTO() {
	}
	
	
	
	

}
