package day05;

public class 연습 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		c c = new c(5,2,3);
		c.draw();
	}

}

class c extends Shape{
	int cx, cy;
	int cr;
	
	public c(int cx, int cy, int cr) {
		super("원");
		this.cx = cx;
		this.cy = cy;
		this.cr = cr;
	}
	
	@Override
	public void draw() {
		super.draw();
		System.out.println(cx+""+cy+""+cr);
	}
}
