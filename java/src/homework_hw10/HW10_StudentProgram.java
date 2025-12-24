package homework_hw10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HW10_StudentProgram {

	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		/* 학생의 성적을 관리하는 프로그램을 구현하세요.
		 * - 파일에 저장. 파일에서 불러오기 
		 * 메뉴
		 * 1. 학생 등록
		 *  - 학년, 반, 번호, 이름을 입력받아 등록
		 *  - 학년, 반, 번호가 같은 학생은 등록 못함
		 * 2. 학생 삭제
		 *  - 학년, 반, 번호를 입력받아 삭제
		 * 3. 학생 조회
		 *  - 학년, 반, 번호를 입력받아 조회
		 * 4. 과목 등록
		 *  - 학년, 학기, 과목명을 입력받아 등록
		 *  - 같은 학년, 학기, 과목명을 가진 과목은 등록 못함
		 * 5. 과목 삭제
		 *  - 학년, 학기, 과목명을 입력받아 삭제
		 * 6. 과목 전체 조회
		 * 7. 학생 성적 추가
		 *  - 학생의 학년, 반, 번호를 입력받아 있으면 과목 학년, 학기, 과목명, 성적을 입력받아 추가
		 * 8. 학생 성적 삭제
		 *  - 학생의 학년, 반, 번호를 입력받아 있으면 과목 학년, 학기, 과목명을 입력받아 삭제 
		 * 9. 프로그램 종료 
		 * */
		int menu = 0;
		final int EXIT = 9;
		//학생 정보를 관리하는 리스트
		List<Student> students = new ArrayList<Student>();
		
		do {
			//메뉴 출력
			printMenu();
			menu = scan.nextInt();
			switch(menu) {
			case 1:
//				addStudent(students);
				addStudent2(students);
				break;
			case 2: 
				removeStudent(students);
				break;
			case 3: 
//				searchStudent(students);
				searchStudent2(students);
				break;
			case 4: break;
			case 5: break;
			case 6: break;
			case 7: break;
			case 8: break;
			case EXIT: 
				exit();
				break;
			default:
				
			}
		}while(menu != EXIT);
	}
	//Student클래스의 equals를 활용
	private static void removeStudent(List<Student> list) {
		//학생 정보를 입력받아 학생 객체를 생성.
		Student student = inputStudent(false);
		//list에 학생 객테가 몇번지에 있는지 확인
		int index = list.indexOf(student);
		//학생 정보가 없으면 일치하는 학생이 없습니다. 안내문구 출력후 종료
		if(index < 0) {
			System.out.println("=====================");
			System.out.println("일치하는 학생이 없습니다.");
			System.out.println("=====================");
			return;
		}
		//있으면 list에서 학생 정보가 있는 번지의 객체를 제거후
			list.remove(index);
			//학생을 삭제했습니다를 출력
			System.out.println("=====================");
			System.out.println("학생을 삭제했습니다.");
			System.out.println("=====================");
		
	}
	private static void searchStudent(List<Student> list) {
		//학년, 반, 번호를 입력받아 학생 객체를 생성. inputStudent 활용
		Student student = inputStudent(false);
		
		boolean isFind = false;
		//반복문을 통해서
			//학년, 반, 번호가 같은 학생이 있으면
				//학생 정보를 출력
		for(Student tmp:list) {
			if(tmp.getGrade() == student.getGrade()
				&& tmp.getClassNum() == student.getClassNum()
				&& tmp.getNum() == student.getNum()) {
				tmp.printScore();
				isFind = true;
				
			}
		}
		//일치하는 학생이 없는 경우
		if(!isFind) {
			System.out.println("=====================");
			System.out.println("일치하는 학생이 없습니다.");
			System.out.println("=====================");
		}
	}
	
	//searchStudent는 equals를 이용하지 않음.
	//searchStudent2는 Student 클래스의 equals를 오버라이딩하여 이용.
	private static void searchStudent2(List<Student> list) {
		//학년, 반, 번호를 입력받아 학생 객체를 생성. inputStudent 활용
		Student student = inputStudent(false);
		
		int index = list.indexOf(student);
	
		//일치하는 학생이 없는 경우
		if(index < 0) {
			System.out.println("=====================");
			System.out.println("일치하는 학생이 없습니다.");
			System.out.println("=====================");
			return;
		}
		//있으면 학생 정보(성적을 포함한)를 출력
		//list.get(index).printScore();
		Student tmp = list.get(index);
		tmp.printScore();
	}
	
	private static Student inputStudent(boolean isName) {
		//학년, 반, 번호, 이름을 scan를 이용하여 입력 받음
		System.out.print("학년입력 : ");
		int grade = scan.nextInt();
		System.out.print("반입력 : ");
		int classNum = scan.nextInt();
		System.out.print("번호입력 : ");
		int num = scan.nextInt();
		
		String name = null;
		//이름까지 입력을 받아야 하면
		if (isName) {
			System.out.print("이름입력 : ");
			name = scan.next();			
		}
		return new Student(grade, classNum, num, name);
	}
	
	// 학생 정보 입력받은 후 학생 추가
	private static void addStudent(List<Student> list) {
//		//학년, 반, 번호, 이름을 scan를 이용하여 입력 받음
//		System.out.print("학년입력 : ");
//		int grade = scan.nextInt();
//		System.out.print("반입력 : ");
//		int classNum = scan.nextInt();
//		System.out.print("번호입력 : ");
//		int num = scan.nextInt();
//		System.out.print("이름입력 : ");
//		String name = scan.next();
		
		//학년, 반, 번호, 이름을 이용하여 학생 객체를 생성
		// new를 통해 인스턴스를 생성한 후 객체에 저장
		Student student = inputStudent(true);
		
		//리스트에 학생 객체를 추가
		list.add(student);
		
		//학생을 추가했습니다라고 콘솔에 출력
		System.out.println("=====================");
		System.out.println("학생을 추가했습니다");
		System.out.println("=====================");
		
		System.out.println(list);
	}
	
	private static void addStudent2(List<Student> list) {
		// 학생,반,번호,이름을 scan를 이용하여 입력 받음
		Student student = inputStudent(true);
	
		// 등록된 학생인지 확인
		// 학생 정보가 학생 리스트에서 몇번지에 있는지 확인
		int index = list.indexOf(student);
		//있는 학생이면 이미 등록된 학생입니다라고 출력 후 종료
		if(index >= 0) {
			System.out.println("=====================");
			System.out.println("이미 등록된 학생입니다");
			System.out.println("=====================");
			return;
		}
		//리스트에 학생 객체를 추가
		list.add(student);
		
		//학생을 추가했습니다라고 콘솔에 출력
		System.out.println("=====================");
		System.out.println("학생을 추가했습니다");
		System.out.println("=====================");
		
		System.out.println(list);
	}
	
	// 종료
	private static void exit() {
		// 프로그램 종료
		System.out.println("=====================");
		System.out.println("프로그램 종료");
		System.out.println("=====================");
	}

	private static void printMenu() {
		// TODO Auto-generated method stub
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