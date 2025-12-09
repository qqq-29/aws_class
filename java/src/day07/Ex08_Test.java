package day07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Ex08_Test {

	public static void main(String[] args) {
		/* 게임 성적을 관리하는 프로그램을 만들려고 한다.
		 * - 이름, 점수를 관리
		 * - 이름은 중복이 X
		 * - 기능
		 *   - 추가
		 *   - 삭제
		 *   - 조회
		 *   - 정렬
		 * 
		 */
		GameRecordManager gm = new GameRecordManagerImp();
		gm.add(new GameRecord("JIK", 1000));
		gm.add(new GameRecord("JIK", 2000));
		gm.add(new GameRecord("XYZ", 100));
		gm.add(new GameRecord("ABC", 3000));
		
		gm.printAll();
		
		gm.remove("JIK");
		
		System.out.println("===========");
		gm.printAll();
		
		System.out.println("===========");
		System.out.println(gm.find("ABC"));
		
		gm.sortByScore();
		System.out.println("===========");
		gm.printAll();
		
	}
	
}

interface GameRecordManager{
	//있는 기록이면 업데이트 후 false, 없는 기록이면 추가 후 true
	boolean add(GameRecord gameRecord);
	GameRecord remove(String name);
	GameRecord find(String name);
	void printAll();
	void sortByScore();
}

class GameRecordManagerImp implements GameRecordManager{
	
	private ArrayList<GameRecord> list = new ArrayList<GameRecord>();

	@Override
	public boolean add(GameRecord gameRecord) {
		if(gameRecord == null) {
			return false;
		}
		//등록되지 않은 이름이면 추가후 true 반환
		if(!list.contains(gameRecord)) {
			return list.add(gameRecord);
		}
		
		//등록된 이름이면 점수 수정후 false 반환
		int index = list.indexOf(gameRecord);//수정할 위치를 찾음
		list.set(index, gameRecord);
		
		return false;
	}

	@Override
	public GameRecord remove(String name) {
		if(name == null) {
			return null;
		}
		//이름이 있는 위치를 찾음
		int index = list.indexOf(new GameRecord(name, 0));
		return list.remove(index);
	}

	@Override
	public GameRecord find(String name) {
		int index = list.indexOf(new GameRecord(name, 0));
		if(index < 0 ) {
			return null;
		}
		return list.get(index);
	}

	@Override
	public void printAll() {
		for(GameRecord gr : list) {
			System.out.println(gr);
		}
	}

	@Override
	public void sortByScore() {
		Collections.sort(list);
		
	}
	
}












class GameRecord implements Comparable<GameRecord>{
	
	private String name;
	private int score;
	
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	//생성자
	public GameRecord(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	@Override
	public String toString() {
		return name + " : " + score + "점";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameRecord other = (GameRecord) obj;
		return Objects.equals(name, other.name);
	}
	@Override
	public int compareTo(GameRecord o) {
		return o.getScore() - score;
	}
	
}