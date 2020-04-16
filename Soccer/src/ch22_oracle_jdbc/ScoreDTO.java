package ch22_oracle_jdbc;

public class ScoreDTO {
	
	private String student_no, name;
	private int kor,eng,mat ,tot;
	private double avg;
	public String getStudent_no() {
		return student_no;
	}
	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getTot() {
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	@Override
	public String toString() {
		return "ScoreDTO [student_no=" + student_no + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat="
				+ mat + ", tot=" + tot + ", avg=" + avg + "]";
	}
	
	public ScoreDTO(String student_no, String name, int kor, int eng, int mat) {
		this.student_no = student_no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		tot=kor+eng+mat;
		avg=tot/3.0;
				
	}
	
	public ScoreDTO() {
	}
	
	
	
	
	

}
