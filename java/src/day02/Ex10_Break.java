package day02;

public class Ex10_Break {

	public static void main(String[] args) {
		/* break
		 * - 반복문에서 break를 만나면 반복문을 빠져 나옴
		 * - 반복문에서 break는 if문과 함께 사용
		 * 	- if문 없이 사용하면 반복문을 쓴 의미가 없어짐
		 * */
		//1부터 5까지 출력하는 코드를 break문을 이용
		for(int i = 1; i <= 5; i++) {
			System.out.println(i);
			if(i == 5) {
				break;				
			}
		}
		//두 정수의 최대 공약수를 구하는 코드 => 큰 공약수먼저 찾아서 찾으면 반복문 종료
		int num1 = 12, num2 = 8;
		for(int i = num1; i>=1; i--) {
			if(num1 % i == 0 && num2 % i == 0) {
				System.out.println(num1+"과 " + num2 + "의 최대 공약수 : "+i);
				break;
			}
		}
	}

}
