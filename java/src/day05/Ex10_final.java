package day05;

public class Ex10_final {

	public static void main(String[] args) {
		/* final p314
		 * - 변수, 메서드, 클래스에 final을 붙일 수 있음
		 * - 변수 앞에 붙이면 상수
		 * - 메서드 앞에 붙이면 오버라이딩이 불가능
		 * - 클래스 앞에 붙이면 상속이 불가능 => 부모 클래스가 될수 없음
		 * 	 - 대표적으로 String 클래스
		 * */

	}

}

final class Parent1{}
//class Child extends Parent1{} // 에러 발생 : final 클래스에게 상속 받을 수 없음
class Parent2{
	public final void print() {}
	public void print2() {}
}
class Child extends Parent2{
	
	//public void print() {}// 에러 발생 : final 메서드를 재정의 할수 없음
	public void print2() {}// final 메서드가 아닌 메서드 재정의 가능
}
