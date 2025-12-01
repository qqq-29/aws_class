package day01;

import java.util.Scanner;

public class Ex14_IfSwitchTest강사 {

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
		// 입력 안내 문구
		System.out.print("두 정수와 연산자 입력(예: 1 + 2) : ");
		//정수1를 입력 받음
		int num1 = scan.nextInt();
		//연산자를 입력 받음(문자)
		char op = scan.next().charAt(0);
		//정수2를 입력 받음
		int num2 = scan.nextInt();
		
		//입력 받은 연산자가 +이면 덧셈을 출력
		if(op == '+') {
			System.out.println(num1 + " " + op+ " " + num2 + " = " + (num1+num2));
		}
		//입력 받은 연산자가 -이면 뺄셈을 출력
		else if(op == '-') {
			System.out.println(num1 + " " + op+ " " + num2 + " = " + (num1-num2));
		}
		//...
		else if(op == '/') {
			System.out.println(num1 + " " + op+ " " + num2 + " = " + ((double)num1/num2));
		}
		else if(op == '*') {
			System.out.println(num1 + " " + op+ " " + num2 + " = " + (num1*num2));
		}
		else if(op == '%') {
			System.out.println(num1 + " " + op+ " " + num2 + " = " + (num1%num2));
		}
		//입력 받은 연산자가 +, -, *, /, %가 아니면 잘못된 연산자라고 출력
		else {
			System.out.println(op + "는 산술 연산자가 아닙니다.");
		}
	
//---------------------------------------------------------------------------------------------------------------------
		
		switch(op) {
		case '+':
			System.out.println(num1 + " " + op+ " " + num2 + " = " + (num1+num2));
			break;
		case '-':
			System.out.println(num1 + " " + op+ " " + num2 + " = " + (num1-num2));
			break;
		case '*':
			System.out.println(num1 + " " + op+ " " + num2 + " = " + (num1*num2));
			break;
		case '/':
			System.out.println(num1 + " " + op+ " " + num2 + " = " + ((double)num1/num2));
			break;
		case '%':
			System.out.println(num1 + " " + op+ " " + num2 + " = " + (num1%num2));
			break;
		default:
			System.out.println(op + "는 산술 연산자가 아닙니다.");
		}
		
	}

}
