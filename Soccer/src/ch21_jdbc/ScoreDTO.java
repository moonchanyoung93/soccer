package ch21_jdbc;
/**MVC pattern :
 * Model(모델, 데이터 저장/처리) -DTO
 * View(뷰, 화면 처리 클래스) -main
 * Controller(컨트롤러, 프로그램의 흐름 제어)-DAO */
public class ScoreDTO {
	String student_no;
	String name;
	int kor,eng, mat,tot;
	double avg;
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
		//총점, 평균은 계산
		tot=kor+eng+mat;
		avg=tot/3.0;
	}
	public ScoreDTO() {
	}
	
	
}
