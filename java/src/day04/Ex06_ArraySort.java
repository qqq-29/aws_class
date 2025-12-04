package day04;

public class Ex06_ArraySort {

	public static void main(String[] args) {
		/* 배열 정렬
		 * - 정렬 방법은 매우 많음 => 알고리즘에서 배움
		 * - 설명이 쉬운 버블 정렬
		 *   - 옆에 수와 크기를 비교하여 위치를 바꿈
		 *   - 효율이 가장 안 좋음
		 */
		
		//두 정수의 값을 바꾸는 코드를 작성하세요.
		int num1 = 10, num2 = 20;
		
		//num1에 num2의 값 20이 저장되고, num2에 nu1m의 값 10이 저장되도록 작성
		System.out.println("num1 = " + num1);
		System.out.println("num2 = " + num2);
		
		//잘못된 교환
		//num1 = num2;//num2의 값이 num1의 값을 덮어쓰기 해서 num1의 값이 사라짐
		//num2 = num1;//이후에 20을 다시 num2에 해도 10은 돌아오지 않음
		int tmp = num1;
		num1 = num2;
		num2 = tmp;
		
		System.out.println("num1 = " + num1);
		System.out.println("num2 = " + num2);

		/* 1 3 5 7 9 2 4 6 8 10(원본) 내림차순으로 정렬
		 * 3 5 7 9 2 4 6 8 10 1 : 한 사이클 후 결과. 1이 정렬
		 * 5 7 9 3 4 6 8 10 2 1 : 두 사이클 후 결과. 1, 2가 정렬
		 * */
		int arr[] = {1,3,5,7,9,2,4,6,8,10};
		
		for(int i = 0; i < arr.length - 1 ; i++) {
			for(int j = 0 ; j < arr.length - 1 ; j++) {
				//내림차순이므로 앞에 수가 뒤 수보다 작으면 교환
				if(arr[j] < arr[j+1]) {
					int tmp1 = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp1; 
				}
			}
			//정렬된 배열 확인
			for(int num : arr) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
		
	}

}