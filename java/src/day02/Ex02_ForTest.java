package day02;

public class Ex02_ForTest {

	public static void main(String[] args) {
		// 구구단 2단을 출력하는 코드
//		for(int i = 1;i <= 9; i++) {
//			System.out.println("2 X "+i+" = "+(2*i));
//		}
		
		//a에서 z까지 출력하는 코드를 작성하세요 97~122
		for(int i = 97; i <= 122; i++) {
			System.out.print((char)i);
		}
		System.out.println();
		for(int num = 0; num < 26; num++) {
			System.out.print((char)('a'+num));
		}
		
		/* 1부터 10까지 합을 구하는 코드
		 * 
		 * */
		int sum = 0;
		for(int i=1; i<=10; i++) {
			sum += i;//sum = sum + i;
		}
		System.out.println("1부터 10까지 합은" + sum);
		
	}

}
