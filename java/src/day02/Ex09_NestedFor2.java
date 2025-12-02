package day02;

public class Ex09_NestedFor2 {

	public static void main(String[] args) {
		/* 다음과 같이 출력되도록 코드를 작성하세요
		 * 1 2 3 4
		 * 5 6 7 8
		 * 9 10 11 12
		 * 13 14 15 16
		 * */
		int num = 0;
			for(int i=1; i<=4; i++) {
				num++;
				System.out.println(num+" "+(num+1)+" "+(num+2)+" "+(num+3));
				num=num+3;
			}
			System.out.println("=============================================================");
		int div = 4;
		for(int i = 1; i <= div*div; i++) {
			System.out.print(i+" ");
			if(i%div==0) {
				System.out.println();
			}
		}
		System.out.println("=============================================================");
		
		num = 1;
		//첫번째 줄
		for(int i = num; i < num + 4; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
		//두번째 줄
		num = 5;
		for(int i = num; i < num + 4; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
		//세번째 줄
		num = 9;
		for(int i = num; i < num + 4; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
		//네번째 줄
		num = 13;
		for(int i = num; i < num + 4; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
		System.out.println("=============================================================");
		//line : 1, num : 1
		//line : 2, num : 5
		//line : 3, num : 9
		//line : 4, num : 13
		//line : n, num : 4*n-3
		int line = 1;
		for(line=1; line<=4; line++) {
			num = 4*line-3;
			for(int i = num; i < num + 4; i++) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		
	}

}
