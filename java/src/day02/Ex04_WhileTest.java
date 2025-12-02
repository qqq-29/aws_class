package day02;

public class Ex04_WhileTest {

	public static void main(String[] args) {
		//Hello World 5번 출력 예제
		int i = 1;
		while(i<=5) {
			System.out.println("Hello World");
			i++;
		}
		
		System.out.println();
		
		//Hello World 5번 출력 예제
		int i2 = 5;
		while(i2-- > 0) {
			System.out.println("Hello World");
		}
		
		System.out.println();
		
		//2단 구구단 출력 예제
		int j = 1;
		while(j<=9) {
			System.out.println("2 X "+j+" = "+(2*j));
			j++;
		}

	}

}
