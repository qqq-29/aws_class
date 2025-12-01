package day01;

import java.util.Scanner;

public class Ex14_IfSwitchTest {

	public static void main(String[] args) {
		/* 두 정수와 산술연산자를 입력받아 산술 연산 결과를 출력하는 코드
		 * 예시
		 * 두 정수와 연산자 입력(예: 1 + 2) : 1 / 2
		 * 1 / 2 = 0.5
		 * 예시
		 * 두 정수와 연산자 입력(예: 1 + 2) : 1 ? 2
		 * ?는 산술 연산자가 아닙니다.
		 * 
		 * */

		Scanner scan = new Scanner(System.in);
		System.out.print("두 정수와 연산자 입력(예: 1 + 2) : ");
		int num1 = scan.nextInt();
		String a = scan.next();
		int num2 = scan.nextInt();
		switch(a) {
		case("+"):
			System.out.println(num1 + " + " + num2 + " = " + (num1+num2));
			break;
		case("-"):
			System.out.println(num1 + " - " + num2 + " = " + (num1-num2));
			break;
		case("*"):
			System.out.println(num1 + " * " + num2 + " = " + (num1*num2));
			break;
		case("/"):
			System.out.println(num1 + " / " + num2 + " = " + ((double)num1/num2));
			break;
		default:
			System.out.println(a + "는 산술 연산자가 아닙니다.");
		}
	}

}
