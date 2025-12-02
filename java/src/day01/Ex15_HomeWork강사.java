package day01;

import java.util.Scanner;

public class Ex15_HomeWork강사 {

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
		
		switch(score / 10) {
		case 9,10:
			System.out.println(score+"점은 A입니다.");
			break;
		case 8:
			System.out.println(score+"점은 B입니다.");
			break;
		case 7:
			System.out.println(score+"점은 C입니다.");
			break;
		case 6:
			System.out.println(score+"점은 D입니다.");
			break;
		case 0,1,2,3,4,5:
			System.out.println(score+"점은 F입니다.");
			break;
		default:
			System.out.println(score+"점은 잘못된 점수입니다.");
			break;
		}
	}
}
