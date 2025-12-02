package day02;

public class HW02_Star {

	public static void main(String[] args) {
		/* 샘플 코드를 참고하여 아래와 같이 출력 되도록 코드를 작성하세요. */
		
		int row = 5, col = 5;
		
		/* *****
		 * *****
		 * *****
		 * *****
		 * *****
		 * */
		for(int i = 1; i <= row; i++) {
			for(int j = 1; j <= col; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		/* *
		 * **
		 * ***
		 * ****
		 * *****
		 * */
		
		/* *	i=1, *=1개
		 * **	i=2, *=2개
		 * ***	i=3, *=3개
		 * ****	i=4, *=4개
		 * *****i=5, *=5개
		 * 		      *=?
		 * */
		System.out.println("----------------------------------------------------------------------");
		for(int i = 1; i <= row; i++) {
			for(int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		/*     *
		 *    **
		 *   ***
		 *  ****
		 * *****
		 * */
		/*     *	i=1, 공백=4, *=1
		 *    **	i=2, 공백=3, *=2
		 *   ***	i=3, 공백=2, *=3
		 *  ****	i=4, 공백=1, *=4
		 * *****	i=5, 공백=0, *=5
		 * */
		System.out.println("----------------------------------------------------------------------");
		int x = 0;
		for(int i = row+1; i > 1; i--) {
			x++;
			for(int j = i; j > 1; j--) {
				System.out.print(" ");
			}
				for(int t = 1; t <= x; t++) {
					System.out.print("*");
				}
			
			System.out.println();
		}
		
		/*     *
		 *    ***
		 *   *****
		 *  *******
		 * *********
		 * */
		System.out.println("----------------------------------------------------------------------");
		int y = 0;
		int z = 0;
		for(int i = row+1; i > 1; i--) {
			y++;
			z++;
			for(int j = i; j > 1; j--) {
				System.out.print(" ");
			}
				for(int t = 1; t <= y; t++) {
					System.out.print("*");
				}
				for(int p = 2; p <= z; p++) {
					System.out.print("*");
				}
			
			System.out.println();
		}

	}

}