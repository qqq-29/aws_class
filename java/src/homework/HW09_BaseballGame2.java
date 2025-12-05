package homework;

import java.util.Scanner;

public class HW09_BaseballGame2 {

	public static void main(String[] args) {
		/* 기본 게임 방식은 HW08과 동일한데 기록 관리를 추가
		 * 관리할 기록은 횟수와 입력한 이니셜
		 *  메뉴
		 *  1. 플레이
		 *  2. 기록 확인
		 *  3. 종료
		 *  메뉴 선택 : 1
		 *  //HW08에 했던 야구 게임이 진행
		 *  //정답을 맞추면 맞춘 횟수를 출력
		 *  정답입니다.
		 *  시도횟수는 4회
		 *  5등안에 들었습니다. 
		 *  이니셜을 남겨주세요 : JIK
		 *  
		 *  메뉴
		 *  1. 플레이
		 *  2. 기록 확인
		 *  3. 종료
		 *  메뉴 선택 : 2
		 *  1. JIK - 4회
		 *  메뉴
		 *  1. 플레이
		 *  2. 기록 확인
		 *  3. 종료
		 *  메뉴 선택 : 3
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