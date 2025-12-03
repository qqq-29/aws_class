package day03;

import java.util.Scanner;

public class Ex02_ArrayTest {

	public static void main(String[] args) {
		// 정수 3개를 저장할 수 있는 scores라는 배열을 선언하세요.
		int scores [] = new int[3];
		
		// scores에 0번지에 1, 1번지에 2, 2번지에 3을 저장하세요.
		scores [0] = 1;
		scores [1] = 2;
		scores [2] = 3;
		for(int i = 0; i<scores.length; i++) {
			scores[i] = i+1;
			System.out.println(scores[i]);
		}
		//Scanner를 이용하 0~100사이의 정수 3개를 입력받아 배열에 저장하세요.
		Scanner scan = new Scanner(System.in);
		System.out.print("0~100사이의 정수 3개를 입력 : ");
//		scores [0] = scan.nextInt();
//		scores [1] = scan.nextInt();
//		scores [2] = scan.nextInt();
		for(int i = 0; i<scores.length; i++) {
			scores[i] = scan.nextInt();
		}
		
		for(int i = 0; i<scores.length; i++) {
			System.out.println(scores[i]);
		}
	}

}
