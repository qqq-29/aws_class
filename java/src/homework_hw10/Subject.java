package homework_hw10;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 123L;
	
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

	@Override
	public int hashCode() {
		return Objects.hash(grade, name, semeter);
	}

	//equals 오버라이빙. 학년, 학기, 과목명이 같으면 같은 객체로 판별
	@Override
	public boolean equals(Object obj) {
		//주소가 같은지 확인 => 같은 객체를 공유 => 무조건 참
		if (this == obj)
			return true;
		//비교 대상이 없음 => 비교 못함 => 무조건 거짓
		if (obj == null)
			return false;
		//비교 대상의 종류가 다름 => 비교를 대부분 못함 => 거짓
		if (getClass() != obj.getClass())
			return false;
		//과목이랑 과목 성적이랑 비교 가능하게 구현하고 싶다
		//아래 코드는 equals를 직접 호춯할 때만 동작
		//List에서 제공하는 indexOf나 contains에서는 의미가 없음 
		if( obj instanceof SubjectScore) {
			SubjectScore suScore = (SubjectScore) obj;
			return this.equals(suScore.getSubject());
		}
		// 두 객체의 클래스가 같으면 지정된 필드 각각을 비교하여 같은지를 판별
		Subject other = (Subject) obj;
		return grade == other.grade && Objects.equals(name, other.name) && semeter == other.semeter;
	}
	
}
