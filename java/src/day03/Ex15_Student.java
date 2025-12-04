package day03;

import java.util.Scanner;

public class Ex15_Student {

	final static int MAX_COUNT = 3;
	public static void main(String[] args) {
		/* 프로그램 기능
		 * 1. 학생 추가
		 * 2. 학생 성적 수정
		 * 3. 학생 삭제
		 * 4. 종료 
		 * */
		Scanner scan = new Scanner(System.in);
		char menu;
		MyStudent stds[] = new MyStudent[MAX_COUNT];
		int count = 0;//현재 저장된 학생 수 => 배열에 추가할 때 사용
		final char EXIT = '5';
		//입력받은 학년, 반, 번호, 이름을 저장할 변수
		int grade, classNum, num;
		String name;
		do {
		//메뉴 출력
		printMenu();
		
		//메뉴 입력
		menu = scan.next().charAt(0);
		
		//선택한 메뉴에 맞는 기능 실행
		switch(menu) {
		case '1':
			//학생 배열과 학생수를 알려주면 학생정보를 추가하여
			//추가된 학생수를 새로 저장
			count = addStudent(stds, count, scan);
			break;
		case '2':
			updateStudents(stds, count, scan);
			break;
		case '3':
			System.out.println("학생 정보 삭제 기능 구현 예정");
			break;
		case '4':
			printStudents(stds, count);
			break;
		case EXIT:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
		}while(menu != EXIT);
	}
	
	public static void printMenu() {
		System.out.println("=====메뉴=====");
		System.out.println("1. 학생 추가");
		System.out.println("2. 학생 성적 수정");
		System.out.println("3. 학생 삭제");
		System.out.println("4. 학생 정보 확인");
		System.out.println("5. 종료");
		System.out.print("메뉴 입력 : ");
		
	}
	
//---------------------------------------------------------------------------------------------------------------
	/* 기능 : 학생 정보를 입력받아 학생 배열에 추가하는 메서드
	 * 매개변수 : 학생 배열, 현재 저장된 학생수, 스캐너
	 * 	=> MyStudent []stds, int count, Scanner scan
	 * 리턴타입 : 저장된 학생 수 => int
	 * 메서드명 : addStudent
	 * */
	public static int addStudent(MyStudent []stds, int count, Scanner scan) {
		//배열이 다 차있으면 못하겠다고 출력
		if(count == MAX_COUNT) {
			System.out.println("더이상 학생을 추가할 수 없습니다.");
			return count;					
		}
		//학생의 학년, 반, 번호, 이름을 입력
		System.out.print("학년 반 번호 이름순으로 입력하세요.:");
		int grade = scan.nextInt();
		int classNum = scan.nextInt();
		int num = scan.nextInt();
		String name = scan.next();
		//객체를 생성해서 배열에 저장
		MyStudent std = new MyStudent(grade, classNum, num, name);
		stds[count] = std;//배열에 저장
		count++;//추가된 학생 수 증가
		return count;
	}
//---------------------------------------------------------------------------------------------------------------
	//추가된 학생 정보를 출력하는 메서드
	public static void printStudents(MyStudent []stds, int count) {
		for(int i = 0; i < count; i++) {
			stds[i].print();
		}
		if(count == 0) {
			System.out.println("등록된 학생이 없습니다.");
		}
}
//---------------------------------------------------------------------------------------------------------------
	//학생 정보들에서 학년, 반, 번호를 이용하여 학생의 위치를 찾는 메서드
	public static int indexOf(MyStudent [] stds, int count,
							int grade, int classNum, int num) {
		int index = -1;//학생 정보가 있는 위치
		for(int i = 0; i < count; i++) {
			MyStudent std = stds[i];
			if(std.grade == grade &&
					std.classNum == classNum &&
					std.num == num) {
				index = i;
				break;
			}
		}
		return index;
	}
//---------------------------------------------------------------------------------------------------------------
	//학생 정보를 입력받아 학생의 성적을 수정하는 메서드
	public static void updateStudents(MyStudent [] stds, int count, Scanner scan) {
		//성적을 수정할 학생 정보를 입력(학년, 반, 번호)
		System.out.print("수정할 학생의 정보를 학년, 반, 번호입력 : ");
		int grade = scan.nextInt();
		int classNum = scan.nextInt();
		int num = scan.nextInt();
		
		//학생 정보들에서 학년반번호로 검색해서 있으면 위치를 저장
		int index = indexOf(stds, count, grade, classNum, num);
		//없으면 -1를 저장
		//학생이 없으면 없다고 알리고 종료
		if(index == -1) {
			System.out.println("등록된 학생이 아닙니다.");
			return;
		}
		
		//수정할 성적을 입력(국어,영어,수학)
		System.out.println("성적을 입력(국어,영어,수학 순) : ");
		int korScore = scan.nextInt();
		int engScore = scan.nextInt();
		int mathScore = scan.nextInt();
		//입력받은 성적을 수정학 학생 정보에 변경
		stds[index].update(korScore, engScore, mathScore);
	}
//---------------------------------------------------------------------------------------------------------------
}
class MyStudent{
	//필드
	int korScore, engScore, mathScore;
	int grade, classNum, num;
	String name;
	
	//학생 정보를 콘솔에 출력하는 기능
	void print() {
		System.out.println(grade + "학년 " + classNum + "반 "+ num + "번 "+ name);
		System.out.println("국어 : "+korScore);
		System.out.println("영어 : "+engScore);
		System.out.println("수학 : "+mathScore);
	}
	public void update(int korScore2, int engScore2, int mathScore2) {
		korScore = korScore2;
		engScore = engScore2;
		mathScore = mathScore2;
		
	}
	//기본 생성자
	MyStudent(){}
	//생성자 오버로딩
	MyStudent(int grade1, int classNum1, int num1, String name1){
		grade = grade1;
		classNum = classNum1;
		num = num1;
		name = name1;
	}
}
