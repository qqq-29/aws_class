package homework;

public class HW03_Star강사 {

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
		 * 		      *=i개
		 * */
		for(int i = 1; i<= row; i++) {
			//*의 개수만큼 출력
			for(int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			//줄 바꿈
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
		 * 			공백의 개수 + *의 개수 = 5
		 * 				 공백=5-i, * = i
		 * */
		for(int i = 1; i<= row; i++) {
			//공백 개수만큼 출력
			for(int j = 1; j <= 5 - i; j++) {
				System.out.print(" ");
			}
			//* 개수만큼 출력
			for(int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			//줄 바꿈
			System.out.println();
		}
		
		/*     *
		 *    ***
		 *   *****
		 *  *******
		 * *********
		 * */
		/*     *		i=1, 공백=4, *=1
		 *    ***		i=2, 공백=3, *=3
		 *   *****		i=3, 공백=2, *=5
		 *  *******		i=4, 공백=1, *=7
		 * *********	i=5, 공백=0, *=9
		 * 					공백=5-i, *=2*i-1
		 * */
		for(int i = 1; i<= row; i++) {
			//공백 개수만큼 출력
			for(int j = 1; j <= 5 - i; j++) {
				System.out.print(" ");
			}
			//* 개수만큼 출력
			for(int j = 1; j <= 2*i - 1; j++) {
				System.out.print("*");
			}
			//줄 바꿈
			System.out.println();
		}
		
		/*     *		i=1, 공백=4, *=1, 추가된 *=0
		 *    ** *		i=2, 공백=3, *=2, 추가된 *=1
		 *   *** **		i=3, 공백=2, *=3, 추가된 *=2
		 *  **** ***	i=4, 공백=1, *=4, 추가된 *=3
		 * ***** ****	i=5, 공백=0, *=5, 추가된 *=4
		 * 					공백=5-i, *=i,추가된 * = i-1
		 * */
		for(int i = 1; i<= row; i++) {
			//공백 개수만큼 출력
			for(int j = 1; j <= 5 - i; j++) {
				System.out.print(" ");
			}
			//* 개수만큼 출력
			for(int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			//추가된 * 개수만큼 출력
			for(int j = 1; j <= i-1; j++) {
				System.out.print("*");
			}
			//줄 바꿈
			System.out.println();
		}
	}
}