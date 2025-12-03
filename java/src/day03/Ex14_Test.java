package day03;

public class Ex14_Test {

	public static void main(String[] args) {
		/* 학생 3명의 국어 영어 수학 성적을 관리하는 프로그램을 만들려고 한다.
		 * 이때 필요한 변수나 배열을 선언하세요
		 * 단, 관리할 정보는 학년, 반, 번호, 이름, 국어, 영어, 수학 점수
		 * */
		final int STUDENT_COUNT = 3;
		int grade [] = new int [STUDENT_COUNT];//학년
		int classNum [] = new int [STUDENT_COUNT];//반
		int num [] = new int [STUDENT_COUNT];//번호
		String name [] = new String [STUDENT_COUNT];//이름
		int korScore [] = new int [STUDENT_COUNT];//국어점수
		int engScore [] = new int [STUDENT_COUNT];//영어점수
		int Score [] = new int [STUDENT_COUNT];//수학점수
		
		Student students[] = new Student [STUDENT_COUNT];
		//첫번째 학생 정보를 추가(1학년, 1반, 1번, 홍길동)
//		students[0] = new Student();
//		students[0].grade = 1;
//		students[0].classNum = 1;
//		students[0].num = 1;
//		students[0].name = "홍길동";
		//우에있는 5줄 아래 한줄로 쓸수있습
		students[0] = new Student(1, 1, 1, "홍길동");
		students[1] = new Student(1, 1, 2, "임꺽정");
		students[2] = new Student(1, 1, 3, "고길동");
		
		students[0].print();
		students[1].print();
		students[2].print();
	}

}

class Student{
	//필드
	int korScore, engScore, mathScore;
	int grade, classNum, num;
	String name;
	
	//학생 정보를 콘솔에 출력하는 기능
	void print() {
		System.out.println(grade + "학년 " + classNum + "반 "+ num + "번 "+ name);
	}
	//기본 생성자
	Student(){}
	//생성자 오버로딩
	Student(int grade1, int classNum1, int num1, String name1){
		grade = grade1;
		classNum = classNum1;
		num = num1;
		name = name1;
	}
}