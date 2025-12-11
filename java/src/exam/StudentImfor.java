package exam;

class StudentImfor implements Comparable<StudentImfor>{

	private int grade;
	private  int stuclass;
	private int num;
	private int score;
	private String name;
	
	@Override
	public int compareTo(StudentImfor o) {

		if (this.grade != o.grade) {
			return this.grade - o.grade;  
		}
		
		if (this.stuclass != o.stuclass) {
			return this.stuclass - o.stuclass;
		}
		return this.num - o.num;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getStuclass() {
		return stuclass;
	}

	public void setStuclass(int stuclass) {
		this.stuclass = stuclass;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StudentImfor(String name, int grade, int stuclass, int num, int score) {
		this.name = name;
		this.grade = grade;
		this.stuclass = stuclass;
		this.num = num;
		this.score = score;
	}
	
	@Override
	public String toString() {
		return grade + "학년 " + stuclass + "반 " + num + "번 " + name + " - 점수: " + score;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentImfor other = (StudentImfor) obj;
		return grade == other.grade && num == other.num && stuclass == other.stuclass;
	}



	
	
}