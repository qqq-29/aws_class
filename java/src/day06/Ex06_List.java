package day06;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ex06_List {

	public static void main(String[] args) {
		/* List를 이용하여 1~5사이의 정수를 5번 입력받아 중복이 안되면 저장하고 중복이 되면 저장하지
		 * 않은 예제를 작성
		 * */
//		int [] numlist = new int[5];
//		Scanner scan = new Scanner(System.in);
//		for(int i = 0; i < 5; i++) {
//			System.out.print("1~5사이의 정수를 5번 입력해주세요:");
//			numlist[i] = scan.nextInt();
//			if(numlist[i]<=5 && numlist[i]>=1) {
//			for(int j = 0; j < i; j++) {
//				if(numlist[i]==numlist[j]) {
//					System.out.println("중복");
//					i--;
//					break;
//				}
//			}
//			}else {
//				System.out.println("1~5사이 아닙니다");
//			}
//		}
		
//------------------------강사------------------------------------------------------------
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		int num;
//		Scanner scan = new Scanner(System.in);
//		for(int i = 0; i < 5; i++) {
//			System.out.print("1~5사이의 정수를 5번 입력해주세요:");
//			num = scan.nextInt();
//			//추가하기 전에 중복 검사
//			//리스트에 num가 없으면
//			if(num > 5 || num < 1) {
//				System.out.println("범위를 벗어난 수를 입력했습니다");
//				continue;
//			}
//			if(!list.contains(num)) {
//				list.add(num);//추가
//				System.out.println(num + "가 추가 됐습니다.");
//			}else {
//				System.out.println(num + "는 중복된 수입니다.");
//			}
//		}
//		System.out.println(list);
//		
//	}

	/* List를 이용하여 1~5사이의 정수를 3번 입력받아 중복이 안되면 저장하고 중복이 되면 저장하지
	 * 리스트안에 저장한 정수가 3개
	 * 않은 예제를 작성
	 * */
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		int num;
//		Scanner scan = new Scanner(System.in);
//		for(int i = 0; list.size() < 3; i++) {
//			System.out.print("1~5사이 입력해주세요:");
//			num = scan.nextInt();
//			//추가하기 전에 중복 검사
//			//리스트에 num가 없으면
//			if(num > 5 || num < 1) {
//				System.out.println("범위를 벗어난 수를 입력했습니다");
//				continue;
//			}
//			if(!list.contains(num)) {
//				list.add(num);//추가
//				System.out.println(num + "가 추가 됐습니다.");
//			}else {
//				System.out.println(num + "는 중복된 수입니다.");
//			}
//		}
//		System.out.println(list);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		int min = 1, max = 5, size = 3;
		list = createRandomList(min,max,size);
		System.out.println(list);
		
	}
	
	/* 기능 : min~max사이의 중복되지 않은 size개의 램덤한 정수르 리스트에 담아 반환하는 메서드
	 * 매개변수 : min~max , size => int min, int max, int size
	 * 리턴타입 : 랜덤한 정수가 담긴 리스트 => ArrayList<Integer>
	 * 메서드명 : createRandomList
	 * */
	public static ArrayList<Integer> createRandomList(int min, int max, int size) {
		
		//최소가 최대보다 크면 바꿈
		if(min > max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		//랜덤으로 생성할 수 있는 개수보다 원하는 크기가 더 큰 경우 => 무한후프
		if(max - min + 1 < size) {
			return null;
			
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(list.size()<size) {
			Random random = new Random();
			int num = random.nextInt(min,max+1);
			//추가하기 전에 중복 검사
			//리스트에 num가 없으면
			if(num > max || num < min) {
				continue;
			}
			if(!list.contains(num)) {
				list.add(num);//추가
			}
		}
		return list;
	}

}
