package homework_hw10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

	//다음 필드를 추가하세요
	//학생의 학년, 반, 번호, 이름, 과목 성적들
	private int grade, classNum, num;
	private String name;
	private List<SubjectScore> list;
	
	//toString 오버라이딩
	//1학년 1반 3번 홍길동
	public String toString() {
		return grade + "학년 " + classNum + "반 " + num + "번 " + name;
	}
	
	// 학생의 성적을 출력하는 메서드
	public void printScore() {
		/*
		====================
		1학년 1반 1번 홍길동 성적
		====================
		1학년 1학기 국어 90점
		1학년 1학기 수학 30점
		 */
		System.out.println("====================");
		System.out.println(toString()+ "성적");
		System.out.println("====================");
		
		if(list.size() == 0) {
			System.out.println("등록된 성적이 없습니다.");
			System.out.println("====================");
			return;
		}
		
		//[1학년 1학기 국어 90점, 1학년 1학기 수학 30점, ...]
		//System.out.println(list);
		/*
		for(int i = 0 ; i<list.size();i++) {
			SubjectScore tmp = list.get(i);
			System.out.println(tmp);
		}
		*/
		for(SubjectScore tmp : list) {
			System.out.println(tmp);
		}
		System.out.println("====================");
	}
	
	// 학년, 반, 번호, 이름을 이용한 생성자를 작성하세요.
	public Student(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
		//과목 성적을 저장할 빈 리스트 생성
		list = new ArrayList<SubjectScore>();
	}
	
	// 학생의 학년,반,번호가 같으면 같다고 판별하는 equals를 오버라이딩하세요
	
	@Override
	public int hashCode() {
		return Objects.hash(classNum, grade, num);
	}

	@Override
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
