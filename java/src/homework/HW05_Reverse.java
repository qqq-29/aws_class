package homework;

import java.util.Scanner;

public class HW05_Reverse {

	public static void main(String[] args) {
		/* 4자리 정수를 입력받아 거꾸로 출력하는 코드를 작성하세요
		 * 예시
		 * 입력 : 1234
		 * 결과 : 4321
		 * 
		 * 입력 : 2000
		 * 결과 : 0002
		 * */
		Scanner scan = new Scanner(System.in);
		int num1 = 0;
		int arr1 [] = new int [4];
		int arr2 [] = new int [4];
		System.out.println("4자리 정수를 입력:");
		num1 = scan.nextInt();
		arr1 [0] = num1 / 1000;
		arr1 [1] = (num1-((num1/1000)*1000))/100;
		arr1 [2] = (num1-((((num1-((num1/1000)*1000)))/100)*100)-((num1/1000)*1000))/10;
    	arr1 [3] = num1 - (((arr1 [0])*1000)+((arr1 [1])*100)+((arr1 [2])*10));
		for(int i = 1; i<=4; i++) {
			arr2[0] = arr1[3];
			arr2[1] = arr1[2];
			arr2[2] = arr1[1];
			arr2[3] = arr1[0];
		}
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(arr2[i]);
		}

	}

}
