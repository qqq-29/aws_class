package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;



public class practice {

	public static void main(String[] args) {
		int count = 0;
		gamefunction gm = new gamefunction2();
		Scanner scan = new Scanner(System.in);
		while(count<6) {
			GamePro gamePro = new GamePro(null, 0);
			System.out.println("name:");
			gamePro.setName(scan.nextLine());
			if(gamePro.getName().isEmpty()) {
				System.out.println("공백이예요");
				continue;
			}
			if(gamePro.getName().equals(gamePro)) {
				System.out.println("중복이예요");
				continue;
			}
			System.out.println("score:");
			gamePro.setScore(scan.nextInt());
			scan.nextLine();
			gm.add(gamePro);
			count++;
		}
		
		gm.printAll();
		
		gm.remove("JIK");
		
		System.out.println("===========");
		gm.printAll();
		
		System.out.println("===========");
		System.out.println(gm.find("ABC"));
		
		gm.set();
		System.out.println("===========");
		gm.printAll();

	}

	
}

interface gamefunction{
	boolean add(GamePro gamepro);
	GamePro remove(String name);
	GamePro find(String name);
	void set();
	void printAll();
}

class gamefunction2 implements gamefunction{
	private ArrayList <GamePro> list= new ArrayList<GamePro>();

	@Override
	public boolean add(GamePro gamepro) {
		if(gamepro == null) {
			return false;
		}
		if(!list.contains(gamepro)) {
			return list.add(gamepro);
		}
		//중복 이름 점수 덮어
		if(list.contains(gamepro)) {
			int index = list.indexOf(gamepro);
			list.set(index, gamepro);
			return false;
		}
		//중복 이름
//		if (list.contains(gamepro)) {
//	        System.out.println("중복 이름입니다: " + gamepro.getName());
//	        return false;
//	    }
		
		//중복 이름이 들어오면, 기존 점수와 비교해서 더 큰 점수만 남기고 싶으면
//		// ① 중복이름 존재 → 점수 비교
//		int index = list.indexOf(gamepro);  // equals(name) 기준
//	    if (index >= 0) {
//	        GamePro existing = list.get(index);
//
//	        if (gamepro.getScore() > existing.getScore()) {
//	            // 새 점수가 더 크면 갱신
//	            list.set(index, gamepro);
//	            System.out.println("점수 갱신: " + existing.getName() + " -> " + gamepro.getScore());
//	        } else {
//	            System.out.println("기존 점수가 더 높아요: 유지 (" + existing.getScore() + ")");
//	        }
//	        return false;
//	    }
		return false;
	}

	@Override
	public GamePro remove(String name) {
		int index = list.indexOf(new GamePro(name,0));
		if(index>=0) {
		return list.remove(index);
		}
		System.out.println("removenonono");
		return null;
	}

	@Override
	public GamePro find(String name) {
		int index = list.indexOf(new GamePro(name,0));
		if(index>=0) {			
			return list.get(index);
		}
		System.out.println("findnonono");
		return null;
		
	}

	@Override
	public void set() {
		Collections.sort(list);
	}

	@Override
	public void printAll() {
		for(GamePro gr:list) {
			System.out.println(gr);
		}
		
	}
}

class GamePro implements Comparable<GamePro>{

	private String name;
	private int score;
	
	public GamePro(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "GamePro [name=" + name + ", score=" + score + "]";
	}

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


	@Override
	public int hashCode() {
		return Objects.hash(name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GamePro other = (GamePro) obj;
		return Objects.equals(name, other.name);
	}


	@Override
	public int compareTo(GamePro o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

