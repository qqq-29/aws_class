package day01;

import java.util.Scanner;

public class Ex10_OperatorTest {

	public static void main(String[] args) {
		// 두 정수를 입력받아 합을 구하는 코드
		//안내 문구
		Scanner sc1 = new Scanner(System.in);
		System.out.print("첫번째 숫자 입력");
		int num1 = sc1.nextInt();
		Scanner sc2 = new Scanner(System.in);
		System.out.print("두번째 숫자 입력");
		int num2 = sc2.nextInt();
		System.out.println("합은 : " + (num1+num2));
		
	}

}
