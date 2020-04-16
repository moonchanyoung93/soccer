package ch21_jdbc;

public class MotorDTO2 {

	private String li_number;
	private String company;
	private int year;
	private int eff;


	public String getLi_number() {
		return li_number;
	}
	public void setLi_number(String li_number) {
		this.li_number = li_number;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getEff() {
		return eff;
	}
	public void setEff(int eff) {
		this.eff = eff;
	}
	public MotorDTO2(String li_number, String company, int year, int eff) {
		this.li_number = li_number;
		this.company = company;
		this.year = year;
		this.eff = eff;
	}
	public MotorDTO2() {
	}

	@Override
	public String toString() {
		return "MotorDTO2 [li_number=" + li_number + ", company=" + company + ", year=" + year + ", eff=" + eff + "]";
	}






}
