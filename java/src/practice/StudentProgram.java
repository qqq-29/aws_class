package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentProgram {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		int menu = 0;
		final int EXIT = 9;
		List<Student> students = new ArrayList<Student>();
		do {
			printmenu();
			menu = scan.nextInt();
			switch(menu) {
			case 1 :
				addstudent(students);
				break;
			}
		}while(menu != EXIT);
		

	}

	private static void addstudent(List<Student> students) {
		//학년, 반, 번호, 이름을 scan를 이용하여 입력 받음
		System.out.print("학년입력 : ");
		int grade = scan.nextInt();
		System.out.print("반입력 : ");
		int classNum = scan.nextInt();
		System.out.print("번호입력 : ");
		int num = scan.nextInt();
		System.out.print("이름입력 : ");
		String name = scan.next();
		
		Student student = new Student(grade, classNum, num, name);
		
		students.add(student);
		
	}

	private static void printmenu() {
		System.out.println("");
		System.out.println("메뉴");
		System.out.println("1. 학생 등록");
		System.out.println("2. 학생 삭제");
		System.out.println("3. 학생 조회");
		System.out.println("4. 과목 등록");
		System.out.println("5. 과목 삭제");
		System.out.println("6. 과목 전체 조회");
		System.out.println("7. 학생 성적 추가");
		System.out.println("8. 학생 성적 삭제");
		System.out.println("9. 프로그램 종료");
		System.out.print("매뉴 선택 : ");
	}
	
}
