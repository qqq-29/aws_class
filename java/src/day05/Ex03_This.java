package day05;

public class Ex03_This {

	public static void main(String[] args) {
		/* this p176
		 * - 나 자신을 가리키는 객체
		 * - 클래스내에서 메서드나 생성자에서 사용
		 * - 왜 사용?
		 *   - 메서드, 생성자의 매개변수와 필드의 이름이 같은 경우 구분을 해야하기 때문에
		 *   	필드 앞에 this를 붙임
		 * this() 생성자 p182
		 * - 생성자에서 다른 생성자를 이용하여 초기화 할 때 사용
		 * - 생성자에서 첫번째 줄에 this()를 이용 => 안그러면 에러가 발생할수 있음
		 * */

	}

}
class Point2{
	private int x, y;
	
	public void print() {
		System.out.println(x+ "," +y);
	}
	
	/*
	 * 생성자 만드는 방법
	 * - 클래스를 클릭하여 커서 활상화
	 * - 우클릭 > Source > Generate Constructor using fields를 클릭 후
	 *   생성자의 매개변수로 들어갈 필드를 선택하여 생성
	 * */
	public Point2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point2() {
		//x = 1;//이 코드가 여기에 있으면 this()에 에러 발생
		this(1, 1);//point2(int x, int y)를 호출하여 초기화

	}
	
	/*
	 * Getter/Setter 만드는 방법
	 * - 클래스를 클릭하여 커서 활상화
	 * - 우클릭 > Source > Generate Getter/Setter를 클릭 후
	 *   생성자의 매개변수로 들어갈 필드를 선택하여 생성
	 * */

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}


	
}
