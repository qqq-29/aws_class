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
		
		System.out.println("메뉴");
		System.out.println("1. 플레이");
		System.out.println("2. 기록 확인");
		System.out.println("3. 종료");
		System.out.print("메뉴 선택 : ");
		int select = scan.nextInt();
        String[] player = new String[5];   // 최대 5명 기록
        int[] playerPoint = new int[5];    // 시도 횟수
        int playerNum = 0;                 // 현재 기록된 플레이어 수
		switch(select) {
		case 1:
            int[] base = generateBase();
            int[] input = new int[3];
            int attempts = 0;
            boolean correct = false;

            System.out.println("게임 시작!");

            while(!correct) {
                System.out.print("입력: ");
                input[0] = scan.nextInt();
                input[1] = scan.nextInt();
                input[2] = scan.nextInt();
                attempts++;

                int s = 0; // 위치 맞음
                int b = 0; // 숫자는 맞지만 위치 틀림

                for(int i=0; i<3; i++) {
                    if(input[i] == base[i]) s++;
                    else if(input[i] == base[(i+1)%3] || input[i] == base[(i+2)%3]) b++;
                }

                if(s == 3) {
                    System.out.println("정답입니다!");
                    System.out.println("시도횟수는 " + attempts + "회");
                    correct = true;

                    if(playerNum < 5) {
                        System.out.print("이니셜을 남겨주세요 : ");
                        player[playerNum] = scan.next();
                        playerPoint[playerNum] = attempts;
                        playerNum++;

                        // 등수 계산
                        int rank = 1;
                        for(int i=0; i<playerNum-1; i++) {
                            if(playerPoint[playerNum-1] > playerPoint[i]) {
                                rank++;
                            }
                        }
                        System.out.println(rank + "등 안에 들었습니다.");
                    }
                } else if(s == 0 && b == 0) {
                    System.out.println("O");
                } else {
                    System.out.println(s + "S " + b + "B");
                }
            }
            break;

		case 2:		
			for(int i = 0; i<player.length; i++) {
				System.out.println(player[i]+"-"+playerPoint [i]+"회");
			}
			break;
		case 3:
			break;
		default:
			System.out.println("잘못된 입력");
			break;
		}
		
		
	}

    private static int[] generateBase() {
        int[] base = new int[3];
        int min = 1;
        int max = 9;

        for(int i=0; i<3; i++) {
            base[i] = (int)(Math.random()*(max-min+1)+min);
            for(int j=0; j<i; j++) {
                if(base[i] == base[j]) {
                    i--;
                    break;
                }
            }
        }
        return base;
    }
}