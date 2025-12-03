package day03;

public class Ex05_EnhancedFor {

	public static void main(String[] args) {
		/* 항상된 for문
		 * - 배열이나 컬렉션 프에임워크(List,Set)에서 for문을 편리하게 사용하는 방법
		 * - 특징
		 * 	 - 전체 탐색만 가능(수정 불가능,수정하려면 일반 for문 이용)
		 * 문법
		 *  for(자료형 변수명 : 배열명){
		 *  	실행문;
		 *  }
		 * */
		int [] arr1 = {1,2,3,4};
		
		for(int i =0; i < arr1.length; i++) {
			int num = arr1[i];
			System.out.print(num + " ");
		}
		System.out.println();
		//항상된 for문
		for( int num : arr1 ) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

}
