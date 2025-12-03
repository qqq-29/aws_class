package day03;

public class Ex12_MethodOverloading {
	
	public static void main(String[] args) {
		/* 메서드 오버로딩
		 * - 동일한 이름의 메서드가 여러개 있는 경우
		 * - 조건 : 매개변수가 다름
		 * 	 1. 매개변수의 종류가 다름
		 *   2. 매개변수의 개수가 다름
		 * */
		
		int result = sum(1,2);
		System.out.println(result);
		double result2 = sum(1.2, 2.2);
		System.out.println(result2);
		//println은 대표적인 메서드 오버로딩
		System.out.println(1);
		System.out.println("1");
		System.out.println('1');
		System.out.println(1.1);
	}
	
	public static int sum(int num1, int num2) {
		return num1 + num2;
	}
	public static double sum(double num1, double num2) {
		return num1 + num2;
	}
//에러 발셍: 리턴타입만 다르면 메서드 오버로딩 적용 할수 없다.	
//	public static double sum(int num1, int num2) {
//		return num1 + num2;
//	}
	

}
