package exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Student {

	public static void main(String[] args) {
		int findgrade = 0;
		int findstuclass = 0;
		int findnum = 0;
		int delgrade = 0;
		int delstuclass = 0;
		int delnum = 0;
		Scanner scan = new Scanner(System.in);
		Studentfunction1 stu = new Studentfunction2();
		stu.add(new StudentImfor("최민수",2,1,12,88));
		stu.add(new StudentImfor("이영희",1,3,2,77));
		stu.add(new StudentImfor("김수현",2,1,5,95));

		stu.printAll();
		try {
		System.out.println("");
		System.out.println("검색할 학생 정보 입력");
		System.out.print("학년 입력: ");
		findgrade = scan.nextInt();
		System.out.print("반 입력: ");
		findstuclass = scan.nextInt();
		System.out.print("번호 입력: ");
		findnum = scan.nextInt();
		System.out.println(stu.find(findgrade, findstuclass, findnum));
		
		System.out.println("");
		System.out.println("삭제할 학생 정보 입력");
		System.out.print("학년 입력: ");
		delgrade = scan.nextInt();
		System.out.print("반 입력: ");
		delstuclass = scan.nextInt();
		System.out.print("번호 입력: ");
		delnum = scan.nextInt();
		stu.remove(delgrade, delstuclass, delnum);
		
		System.out.println("");

		System.out.println("=== 정렬 ===");
		stu.sortByNum();
		stu.printAll();
		}catch (InputMismatchException e) {
			System.out.println("정수를 입력하세요.");
		}
	}

}

interface Studentfunction1{
	boolean add(StudentImfor studentimfor);
	StudentImfor find(int grade,int stuclass,int num);
	StudentImfor remove(int grade,int stuclass,int num);
	void printAll();
	void sortByNum();
}

class Studentfunction2 implements Studentfunction1{
	private ArrayList <StudentImfor> list= new ArrayList<StudentImfor>();

	@Override
	public boolean add(StudentImfor studentimfor) {
		if(studentimfor == null) {
			return false;
		}
		if(!list.contains(studentimfor)) {
			return list.add(studentimfor);
		}
		if(list.contains(studentimfor)) {
			int index = list.indexOf(studentimfor);
			list.set(index, studentimfor);
			return false;
		}
		return false;
	}

	@Override
	public StudentImfor find(int grade, int stuclass, int num) {
		int index = list.indexOf(new StudentImfor(null,grade, stuclass, num,0));
		if(index < 0 ) {
			System.out.println("못찾았습니다");
			return null;
		}
		return list.get(index);
	}

	@Override
	public void printAll() {
		for(StudentImfor stu : list) {
			System.out.println(stu);
		}
		
	}

	@Override
	public void sortByNum() {
		Collections.sort(list);
	}

	@Override
	public StudentImfor remove(int grade, int stuclass, int num) {
		int index = list.indexOf(new StudentImfor(null,grade, stuclass, num,0));
		if(index < 0 ) {
			System.out.println("못찾았습니다");
			return null;
		}
		System.out.println("삭제완료");
		return list.remove(index);
	}
	
}

