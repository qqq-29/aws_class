package day01;

import java.util.Scanner;

public class Ex15_HomeWork {

	public static void main(String[] args) {
		/*성적을 입력받아 성적에 맞는 학점을 출력하는 코드를 작성하세요 switch
		 * - 추가 작업 없이 switch문을 간단히 활용하기 위해 점수 범위 설정
		 * A: 90~109
		 * B: 80~89
		 * C: 70~79
		 * D: 60~69
		 * F: -9~59
		 * 잘못된 성적 : -9미만, 110이상
		 * */
		Scanner sc = new Scanner(System.in);
		System.out.print("성적입력 : ");
		int score = sc.nextInt();
		char s;
		if(score >= 90 && score <= 109) {
			s = 'A';
		}else if(score >= 80 && score <= 89) {
			s = 'B';
		}else if(score >= 70 && score <= 79) {
			s = 'B';
		}

}
