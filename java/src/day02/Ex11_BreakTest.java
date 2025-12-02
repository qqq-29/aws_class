package day02;

import java.util.Scanner;

public class Ex11_BreakTest {

	public static void main(String[] args) {
		/* 문자를 입력받아 입력받은 문자가 q이면 종료.아니면 계속 입력을 받는 코드를 작성하세요*/
		Scanner scan = new Scanner(System.in);
		char ch;
		for(;;) {
			System.out.print("문자입력 : ");
			ch = scan.next().charAt(0);
			if(ch=='q') {
				break;
			}else {
				System.out.println("입력 문자 : "+ch);
			}
		}
		

	}

}