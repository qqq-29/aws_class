package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import homework.HW10_StudentProgram.Studentfunction2.StudentImfor;

public class HW10_StudentProgram {

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
		
		switch(scan.nextInt()) {
		case 1:
			System.out.println("학생 등록");
			System.out.print("이름 입력: ");
			String name = scan.next();
			System.out.print("학년 입력: ");
			int grade = scan.nextInt();
			System.out.print("반 입력: ");
			int stuclass = scan.nextInt();
			System.out.print("번호 입력: ");
			int num = scan.nextInt();
			System.out.print("점수 입력: ");
			int score = scan.nextInt();
			stu.add(new StudentImfor(name, grade, stuclass, num, score));
			System.out.println("학생 등록 성공");
			break;
		case 2:
			System.out.println("학생 삭제");
			System.out.print("학년 입력: ");
			delgrade = scan.nextInt();
			System.out.print("반 입력: ");
			delstuclass = scan.nextInt();
			System.out.print("번호 입력: ");
			delnum = scan.nextInt();
			stu.remove(delgrade, delstuclass, delnum);
			System.out.println("학생 삭제 성공");
			break;
		case 3:
			System.out.println("학생 조회");
			System.out.print("학년 입력: ");
			findgrade = scan.nextInt();
			System.out.print("반 입력: ");
			findstuclass = scan.nextInt();
			System.out.print("번호 입력: ");
			findnum = scan.nextInt();
			System.out.println(stu.find(findgrade, findstuclass, findnum));
			break;
		case 4:	
			System.out.println("과목 등록");

		    System.out.print("학생 학년: ");
		    int sg = scan.nextInt();
		    System.out.print("학생 반: ");
		    int sc = scan.nextInt();
		    System.out.print("학생 번호: ");
		    int sn = scan.nextInt();

		    StudentImfor s1 = stu.find(sg, sc, sn);
		    if (s1 == null) break;

		    System.out.print("과목 학년: ");
		    int subGrade = scan.nextInt();
		    System.out.print("학기: ");
		    int semester = scan.nextInt();
		    System.out.print("과목명: ");
		    String subName = scan.next();
		    System.out.print("점수: ");
		    int subScore = scan.nextInt();

		    if (s1.addSubject(subGrade, semester, subName, subScore)) {
		        System.out.println("과목 등록 성공");
		    } else {
		        System.out.println("이미 존재하는 과목");
		    }
		    break;

		case 5:
		    System.out.println("과목 삭제");

		    System.out.print("학생 학년: ");
		    sg = scan.nextInt();
		    System.out.print("학생 반: ");
		    sc = scan.nextInt();
		    System.out.print("학생 번호: ");
		    sn = scan.nextInt();

		    s1 = stu.find(sg, sc, sn);
		    if (s1 == null) break;

		    System.out.print("과목 학년: ");
		    subGrade = scan.nextInt();
		    System.out.print("학기: ");
		    semester = scan.nextInt();
		    System.out.print("과목명: ");
		    subName = scan.next();

		    if (s1.removeSubject(subGrade, semester, subName)) {
		        System.out.println("과목 삭제 성공");
		    } else {
		        System.out.println("과목을 찾을 수 없음");
		    }
		    break;

		case 6:
		    System.out.println("과목 전체 조회");

		    System.out.print("학생 학년: ");
		    sg = scan.nextInt();
		    System.out.print("학생 반: ");
		    sc = scan.nextInt();
		    System.out.print("학생 번호: ");
		    sn = scan.nextInt();

		    s1 = stu.find(sg, sc, sn);
		    if (s1 != null) {
		        s1.printSubjects();
		    }
		    break;
		}
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
	
	interface Studentfunction1{
		boolean add(StudentImfor studentimfor);
		StudentImfor find(int grade,int stuclass,int num);
		StudentImfor remove(int grade,int stuclass,int num);
		void printAll();
		void sortByNum();
	}

	static class Studentfunction2 implements Studentfunction1{
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
		
		static class Subject {
			int grade;
			int semester;
			String name;
			int score;
			
			public Subject(int grade, int semester, String name, int score) {
				this.grade = grade;
				this.semester = semester;
				this.name = name;
				this.score = score;
			}
			
			@Override
			public boolean equals(Object obj) {
				if (this == obj) return true;
				if (!(obj instanceof Subject)) return false;
				Subject other = (Subject) obj;
				return grade == other.grade &&
						semester == other.semester &&
						name.equals(other.name);
			}
			
			@Override
			public String toString() {
				return grade + "학년 " + semester + "학기 " + name + " : " + score;
			}
		}
		static class StudentImfor implements Comparable<StudentImfor>{
			
			private ArrayList<Subject> subjects = new ArrayList<>();
			
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

			public boolean addSubject(int grade, int semester, String name, int score) {
			    Subject sub = new Subject(grade, semester, name, score);
			    if (subjects.contains(sub)) return false;
			    subjects.add(sub);
			    return true;
			}

			public boolean removeSubject(int grade, int semester, String name) {
			    return subjects.remove(new Subject(grade, semester, name, 0));
			}

			public void printSubjects() {
			    if (subjects.isEmpty()) {
			        System.out.println("등록된 과목 없음");
			        return;
			    }
			    for (Subject s : subjects) {
			        System.out.println(s);
			    }
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
	}
}