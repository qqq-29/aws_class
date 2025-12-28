package practice;

public class Student {

	private int grade, classNum, num;
	private String name;
	
	public String toString() {
		return grade + "학년 " + classNum + "반 " + num + "번 " + name;
	}
	public void printStudent() {
		System.out.println("====================");
		System.out.println(toString()+ "성적");
		System.out.println("====================");
	}
	
	public Student(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return classNum == other.classNum && grade == other.grade && num == other.num;
	}

}
