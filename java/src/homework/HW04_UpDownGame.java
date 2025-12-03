package homework;

import java.util.Scanner;

public class HW04_UpDownGame {

	public static void main(String[] args) {
		/* 1~100사이의 랜덤한 수를 생성해서 맞추는 게임
		 * 랜덤한 수 : 33//안보여야 함
		 * 정수입력 : 50
		 * DOWN
		 * 정수입력 : 25
		 * UP
		 * 정수입력 : 30
		 * UP
		 * 정수입력 : 33
		 * 정답입니다.
		 * */
		int min = 1;
		int max = 100;
		int x = (int)(Math.random()*(max - min +1)+min);
		Scanner scan = new Scanner(System.in);
		System.out.println("UP DOWN GAME 숫자 맞추보세요");
		for(;;) {
			int num = scan.nextInt();
			if(num < x) {
				System.out.println("UP");
			}else if(num > x) {
				System.out.println("DOWN");
			}else if(num == x) {
				System.out.println("정답입니다.");
				break;
			}
		}
	}

}
