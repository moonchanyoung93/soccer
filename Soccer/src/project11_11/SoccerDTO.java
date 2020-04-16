package project11_11;

public class SoccerDTO {
	int no, age, goal;
	String name, position, team;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	@Override
	public String toString() {
		return "SoccerDTO [no=" + no + ", age=" + age + ", goal=" + goal + ", name=" + name + ", position=" + position
				+ ", team=" + team + "]";
	}
	public SoccerDTO(int no, int age, int goal, String name, String position, String team) {
		this.no = no;
		this.age = age;
		this.goal = goal;
		this.name = name;
		this.position = position;
		this.team = team;
	}
	public SoccerDTO() {
	}
	
	
}
