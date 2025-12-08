package day06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ex05_CollectionFramework {

	public static void main(String[] args) {
		/* 컬렉션 프레임워크 p422
		 * - 자료를 편하게 관리하기 위해 만들어지 라이브러리
		 * 	 - 배열은 기능이 없어 관리가 불편 => 기능이 있는 배열이라고 생각하면 이해가 쉽다.
		 * - 컬렉션 인터페이스와 맵 인터페이스로 구성
		 * - 컬렉션 프레임워크는 제넥릭 인터페이스로 구성되여 있음
		 * - 컬렉션 인터페이스
		 * 	 - 한 종류의 자료들을 관리
		 * 	 - List 인터페이스와 Set 인터페이스
		 * - MAP 인터페이스
		 * 	 - 두 종류의 자료들을 관리
		 * 	 - Map 인터페이스
		 * 
		 * Collection 인터페이스
		 * - 기능
		 * 	 - add(값) : 값을 뒤에 추가 - collection
		 * 	 - remove(값) : 값과 일치하는 객체를 제거
		 * 	 - contains(값) : 값이 있으면 true, 없으면 false를 반환
		 * 	 - size() : 현재 저장된 크기
		 * 
		 * List p427
		 * - 순서를 보장 , 중복을 허용
		 * - 구현 클래스
		 * 	 - List 인터페이스에 있는 기능을 공통으로 가지고 있음
		 * 	 - ArrayList, Vector(쓰레드 지원), LinkedList
		 * 	 - ArrayList : 배열로 구성, 전체 탐색 빠름, 중간 추가 삭제 느림
		 *   - LinkedList : 링크로 연결, 전체 탐색 느림, 중간 추가 삭제 빠름
		 * - 기능
		 * 	 - get(번지) : 번지에 있는 객체를 반환
		 * 	 - remove(번지) : 번지에 있는 객체를 삭제하고 삭제한 객체를 반환
		 * 	 - set(번지, 값) : 번지에 있는 객체를 값으로 수정(덮어쓰기)
		 * 	 - indexOf(값) : 값이 있으면 위치를 없으면 -1를 반환
		 * 
		 * Set p441
		 * - 순서를 보장하지 않음 , 중복을 허용하지 않음
		 * - 구현 클래스
		 * 	 - HashSet, TreeSet
		 *   - HashSet
		 *     - 해쉬 값을 이용하여 중복 체크를 함
		 *     	 => 같은 해시 값을 가지는 객체들을 모아서 관리
		 *     	 => hashCode()를 이용하여 비교후 => equals()를 이용하여 비교
		 *   - TreeSet
		 *     - Tree 구조로 데이터를 관리 
		 * 
		 * Map 
		 * - 두 자료형을 관리
		 * - K(key), V(value)
		 * 	 - K는 중복 안됨
		 * 	 - V는 중복 허용
		 * - 기능
		 * 	 - get(k) : 키들 중에서 k와 일치하는 객체를 반환
		 * 	 - put(k,v) : 키들 중에서 k와 일치하는 객체가 없으면 k와 v를 추가. 있으면 v만 수정
		 * 	 - remove(k) : 키들 중에서 k와 일치하는 객체가 있으면 삭제후 v를 반환
		 * 	 - keySet() : 키값들을 Set으로 만들어서 반환
		 * 	 	- Map에서 반복문을 이용할 때 활용
		 * */
		/* - 컬렉션 프레임워크에서 객체를 추가, 삭제할 때
		 * 	해당 객체가 있는지를 Objects.equals(o1,o2)를 이용하여 확인
		 * - Objects.equals(o1,o2)
		 * 	 - o1과 o2가 다른 클래스이면 false를 반환
		 * 	 - o1과 o2중 하나라도 null이면 false를 반환
		 * 	 - o1.equals(o2)를 호출
		 * 	 => equals를 오버라이딩해야 함
		 * 
		 * */
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(1);
		list.add(1);
		System.out.println(list);
		//2와 일치하는 객체 제거
		//꼭 Integer 형변환을 해야 하나요?
		//remove는 메서드 오버로딩에 의해 2개있음. 하나는 객체, 하나는 정수
		//아래에서 형번환없이 2를 넣으면 2번지에 있는 객체를 삭제
		list.remove((Integer)2);
		System.out.println(list);
		System.out.println("리스트에 1이있습니까? "+ list.contains(1));
		System.out.println("리스트에 1이 어디에 있습니까? "+ list.indexOf(1));
		
		ArrayList <Student> stds = new ArrayList<Student>();
		stds.add(new Student(1,1,1,"홍길동"));
		stds.add(new Student(1,1,2,"임꺽정"));
		stds.add(new Student(1,1,3,"고길동"));
		
		System.out.println(stds);
		
		Student std = new Student(1,1,1,"홍길동");
		stds.remove(std);
		System.out.println(std + " 학생 삭제후");
		System.out.println(stds);
		
		System.out.println("-------------------------------------------------");
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(1);
		set.add(2);
		set.add(2);
		System.out.println(set);
		//값이 2인 객체 삭제. Integer 안붙여도 됨. set은 번지를 이용한 메서드가 없음
		set.remove(2);
		System.out.println(set);
		System.out.println("set에 2가 있습니까?" + set.contains(2));
		System.out.println("set에 크기는?" + set.size());
		
		ArrayList<String> list3 = new ArrayList<String>();
		list3.add("사과");
		list3.add("바나나");
		list3.add("딸기");
		
		//List, Set 향상된 for문 이용하기
		for(String fruit: list3) {
			System.out.println(fruit);
		}
		
		//List, Set iterator를 이용하기
		Iterator<String> it = list3.iterator();
		
		//다음 요소가 있는지 확인
		while(it.hasNext()) {
			String tmp = it.next();//다음 요소를 꺼내옴
			System.out.println(tmp);
		}
		System.out.println("-------------------------------------------------");
		
		//map은 k와 v가 필요, 타입이 2개 필요
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("abc123", "abc123123");
		map.put("abc123", "123123");//중복된 key값이면 value만 업데이트
		map.put("qwerqwer", "123123");
		
		System.out.println(map);
		/* map은 직접 반복문을 이용할 수 없음
		 * keySet()이나 entrySet()을 이용하여 반복문 활용
		 * */
		Set<String> keySet = map.keySet();
		for(String id : keySet) {
			System.out.println("아이디: "+ id);
			System.out.println("비번: "+ map.get(id));
		}
	}

}
