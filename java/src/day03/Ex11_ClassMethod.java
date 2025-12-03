package day03;

public class Ex11_ClassMethod {

	public static void main(String[] args) {
		/* 필드와 메서드
		 * - 필드는 같은 클래스에 있는 메서드에서 사용될 수 있음
		 * - 메서드에서 필드 값을 수정하면 수정이 된다
		 * - 메서드가 필드값만 수정하는 경우 리턴타임이 필요없다
		 * */
		
		MyCar car = new MyCar();
		car.turnOn();
		car.turnOff();
		car.turnOn();
		car.changeGear('D');
		//엑셀을 밟음
		for(int i = 1; i<=5; i++) {
			car.speedUp();
		}
		System.out.println("현재 속력은 "+ car.speed + "km/h 입니다");
		car.turnOff();
		for(int i = 1; i<=15; i++) {
			car.speedDown();
	}
		System.out.println("현재 속력은 "+ car.speed + "km/h 입니다");
		car.turnOff();
	}
	
}



class MyCar{
	String company, name, color;
	int speed;
	char gear;
	boolean power;
	
	//시동을 켜는 기능
	void turnOn() {
		power = true;
		System.out.println("부르릉~");
	}
	//시동을 끄는 기능
	void turnOff() {
		//주행 중에는 시동이 꺼지지 않게(실제는 어떨지 모름)
		if(speed == 0) {
		power = false;
		System.out.println("조용~");
		}
	}
	//기어 변경
	void changeGear(char nextGear) {
		switch(nextGear) {
		case 'P':
			gear = nextGear;
			System.out.println("기어가 주차로 변경");
			break;
		case 'D':
			gear = nextGear;
			System.out.println("기어가 주행로 변경");
			break;
		case 'R':
			gear = nextGear;
			System.out.println("기어가 후진로 변경");
			break;
		case 'N':
			gear = nextGear;
			System.out.println("기어가 증립로 변경");
			break;
		default:
			System.out.println("기어 고장");
			
		}
		gear = nextGear;
	}
	
	//속력 UP(엑셀레이터 밟음) : 속력이 1씩증가
	void speedUp() {
		speed++;
	}
	//속력 Down(브레이크 밟음) : 속력이 1씩감소
	void speedDown() {
		if(speed>0) {
			speed--;
		}
	}
}
