package day03;

import java.util.Scanner;

public class Ex10_MethodTest2 {

	public static void main(String[] args) {
		/* 두 정수의 최대 공약수를 구하는 메서드를 만들고 테스트해보세요.
		 * */
		

		int num1 = 12;
		int num2 = 64;
		int num3 = a(num1, num2);
		System.out.println("최대 공약수 : " + num3);
		
		/* 최대 공약수를 이용하여 최소 공배수를 구하세요.
		 * */
		
		int num4 = b(num1,num2,num3);
	}
	
	public static int a(int num1, int num2) {
		int x = 0;//최대 공약수
		for(int i = 1; i<=num2; i++) {
			if(num1%i==0 && num2%i==0) {
				x = i;
			}
		}
		return x;
	}
	
	public static int b(int num1, int num2, int num3) {
		int x = 0;//최소 공배수
		
		return 0;
	}
	
	

}
