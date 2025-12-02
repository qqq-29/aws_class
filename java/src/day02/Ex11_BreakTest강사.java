package day02;

import java.util.Scanner;

public class Ex11_BreakTest강사 {

public static void main(String[] args) {
	/* 문자를 입력받아 입력받은 문자가 q이면 종료.아니면 계속 입력을 받는 코드를 작성하세요*/
	Scanner scan = new Scanner(System.in);
	char ch;
	for(ch = 'a'; ch != 'q';) {
		System.out.print("문자입력 : ");
		ch = scan.next().charAt(0);
		System.out.println("입력 문자 : "+ch);
		}
	System.out.println("프로그램 종료.");
	
	for(;;) {
		System.out.print("문자입력 : ");
		ch = scan.next().charAt(0);
		System.out.println("입력 문자 : "+ch);
		if(ch == 'q') {
			break;
		}
	}
	System.out.println("프로그램 종료.");
	
	do {
		System.out.print("문자입력 : ");
		ch = scan.next().charAt(0);
		System.out.println("입력 문자 : "+ch);
	}while(ch != 'q');
	System.out.println("프로그램 종료.");
}

}
