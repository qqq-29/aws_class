package day01;

public class Ex07_Operator1 {

	public static void main(String[] args) {
		/*연잔자
		 * 목표
		 *  - 연산자 종류를 알고 결과를 예측할 수 있다.
		 *  
		 *  1. 대입 연산자(p70)
		 *  - =
		 *  - 오른쪽 값을 왼쪽에 저장
		 *  - 오른쪽에는 식(연산자가 포함된)이 올수 있지만 왼쪽은 안됨
		 *  
		 *  2. 산술 연산자(p72)
		 *  - +(더셈),-(뺄셈),/(나눗셈),*(곱셈),%(나머지)
		 *  - 정수 연산자 정수 => 정수
		 *  	- /을 조심. 1/2 => 0.5가 아닌 0
		 *  - 정수 연산자 실수 => 실수
		 *  - 실수 연산자 정수 => 실수
		 *  - 실수 연산자 실수 => 실수
		 * 
		 * */
		
		int num = 10;
		num = num+10;
		
		int num1=1, num2=2;
		System.out.println(num1 + "+" + num2 + "=" + (num1+num2));
		System.out.println(num1 + "-" + num2 + "=" + (num1-num2));
		System.out.println(num1 + "*" + num2 + "=" + (num1*num2));
		//1 / 2를 해서 결과가 0
		System.out.println(num1 + "/" + num2 + "=" + (num1/num2));
		//1.0 / 2를 해서 결과가 0.5(자료형변환)
		System.out.println(num1 + "/" + num2 + "=" + ((double)num1/num2));
		System.out.println(num1 + "%" + num2 + "=" + (num1%num2));

		/* 증감연산자 p75
		 * - 최종적으로 1증가/1감소
		 * - ++ : 1증가, --: 1감소
		 * - 전위형
		 * 		- ++변수, --변수
		 * 		- 변수를 증가시키고 일은 함
		 * - 후위형 
		 * 		- 변수++, 변수--
		 * 		- 일을 하고 변수를 증가 시킴
		 * */
		
		int num3 = 3, num4 = 3;
		//num3이 증가를 한후, 증가한  num3의 값읗 num5에 저장
		int num5 = ++num3;
		//num4의 값읗 num6에 저장한 후.num4의 값을 증가
		int num6 = num4++;
		System.out.println("num5:" + num5);
		System.out.println("num6:" + num6);
		
		//num5,6처럼 결과가 되도록 작업
		num3 = 3;
		num4 = 3;
		int num7 = num3, num8 = num4;//일
		
		++num3;//증가 시키고
		num7 = num3;//일
		
		System.out.println("num7 : "+ num7);
		System.out.println("num8 : "+ num8);
		++num4;//증가
	}

}
