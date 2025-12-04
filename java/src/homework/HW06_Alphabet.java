package homework;

public class HW06_Alphabet {

	public static void main(String[] args) {
		/* 다음 결과가 출력 되도록 코드를 작성하세요.
		 * 참고 예제 : day02.Ex02_ForTest에서 a부터 z까지 한줄로 출력 예제
		 * a
		 * ab
		 * abc
		 * abcd
		 * abcde
		 * ...
		 * abcde....xyz
		 * */
		Print();

	}
	
	public static void Print() {
		int num = 0;
		char arr1 [] = new char [26];
		for(int i = 0; i<=25; i++) {
			arr1[i]=((char)(i+97));
			for(int j = 0; j<i; j++) {
				System.out.print(arr1[j]);
			}
			System.out.println(arr1[i]);
			

		}
	
	}
}
