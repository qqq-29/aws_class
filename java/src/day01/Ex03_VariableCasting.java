package day01;

public class Ex03_VariableCasting {

	public static void main(String[] args) {
		
		/*자료형(기본타입) 변환
		 * - 변수 또는 값의 자료형을 일시적으로 변환
		 * - 묵시적 자료형 변환(자동 자료형 변환)
		 * 		- 정수 => 실수(int => float)
		 * 		- 작은 바이트 => 큰 바이트로 변환(byte => int)
		 * - 명시적 자료형 변환(겡자 자료형 변환)
		 * 		- 데이터 손실이 일어날 수 있는 경우(안하면 에러 발생)
		 * 		- 실수 => 정수( float => int)
		 * 		- 큰바이트 => 작은 바이트로(int => char)
		 * - 필요에 의한 변환
		 * 		- 나누기
		 * 			- js : 1/2 => 0.5
		 * 			-자바 : 1/2 => 0. 정수/정수 => 정수
		 * 				 : 1/2.0 => 0.5 정수/실수 => 실수
		 * - 적용 방법
		 * 		- 값 또는 변수 앞에 (바꿀자료형)을 추가 
		 * 
		 * */
		long num1 = 10;//int 10이 long으로 자동 자료형 변환
		float num2 = num1;//정수 => 실수로. 자동 자료형변환
		System.out.println(num2);
		
		int num3 = (int)1.23;//double 1.23을 int로 강제 자료형 변환
		System.out.println(num3);
		
		//자료형변환을 하지 않아도 에러는 발생하지 않지만 원하는 결과를 얻을 수 없음
		//=> 형변환읗 통해 원하는 결과를 얻음
		int num4 = 1, num5 = 2;
		System.out.println("1 / 2(형 변환 안함) : " + num4 / num5);
		System.out.println("1 / 2(형 변환 함) : " + (double)num4 / num5);
	}

}
