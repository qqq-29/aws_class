package homework;

import java.util.Scanner;

public class HW08_BaseballGame {

	public static void main(String[] args) {
		/* 1~9사이의 중복되지 않은 3개의 수를생성해서 맞추는 게임
		 * 규칙
		 * S : 숫자가 있고 위치가 같음
		 * B : 숫자가 있고 위치가 다름
		 * O : 일치하는 숫자가 하나도 없음
		 * 
		 * 예시
		 * 랜덤수 : 1 5 4
		 * 입력 : 1 2 3
		 * 1S
		 * 입력 : 4 5 6
		 * 1S 1B
		 * 입력 : 7 8 9
		 * O
		 * 입력 : 1 4 5
		 * 1S 2B
		 * 입력 : 1 5 4
		 * 정답입니다.
		 * */

		Scanner scan = new Scanner(System.in);
		int [] base = new int[3];
		int [] input = new int[3];
		int placeCount = 0;//숫자가 있고 위치가 같음 
		int rightCount = 0;//숫자가 있고 위치가 다름
		int min = 1;
		int max = 9;
		for(int i = 0; i < 3; i++) {
			base[i] = (int)(Math.random()*(max-min+1)+min);
			for(int j = 0; j<i; j++) {
				if(base[j]==base[i]) {
					i--;
					break;
				}
			}
		}

		
		for(int i = 0; i<input.length; i++) {
			System.out.print("랜덤수 : ");
			for (int t = 0; t<base.length; t++) {
				System.out.print(base[t]+" ");
			}
			System.out.print("입력 : ");
			input[0] = scan.nextInt();
			input[1] = scan.nextInt();
			input[2] = scan.nextInt();
			for(int j = 0 ; j<i; j++) {
				if(input[j] == base[i] && i == j) {
					System.out.println("정답입니다.");
					break;
				}else if(input[j] == base[i]) {
					rightCount++;
					break;
				}else if(i == j) {
					placeCount++;
					break;
				}
			}
			System.out.println(rightCount + " " + placeCount);
		}
	}

}