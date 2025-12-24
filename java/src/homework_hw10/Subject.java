package homework_hw10;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subject {

	// 학년(grade), 학기(semeter), 과목명(name)을 필드로 선언하세요
	private int grade, semeter;
	private String name;

	// grade, semester, name의 getter와 setter를 추가
	
	//toString 오버라이딩해서 1학년 1학기 국어 형태의 문자열이
	//반환되도록 작성하세요.
	@Override
	public String toString(){
		return grade + "학년" + semeter + "학기" + name + "과목명" ;
	}
	
	//학년, 반, 번호를 이용한 생성자를 추가하세요.
	public Subject(int grade, int semeter, String name) {
		this.grade = grade;
		this.semeter = semeter;
		this.name = name;
	}
	
	
	
	
}
