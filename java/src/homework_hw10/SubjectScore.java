package homework_hw10;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectScore {

	//다음 필드를 선언하세요.
	//과목 정보, 성적
	private Subject subject; 
	private double score;
	
	//toString을 오버라이딩하세요
	//1학년 1학기 국어 90점 형태로
	@Override
	public String toString() {
		return subject.toString() + " " + score + "점";
	}

	public SubjectScore(int grade, int semeter, String name, double score) {
//		
//		subject = new Subject();
//		subject.setGrade(grade);
//		subject.setSemeter(semeter);
//		subject.setName(name);
//		
		subject = new Subject(grade, semeter, name);
		this.score = score;
	}
	
	//학년, 학기, 과목명, 성적을 이용한 생성자를 추가하세요
	
}
