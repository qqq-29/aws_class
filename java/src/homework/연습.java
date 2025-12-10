package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;



public class 연습 {

	public static void main(String[] args) {
		gamefunction gm = new gamefunction2();
		Scanner scan = new Scanner(System.in);
		for(int i=0; i<5; i++) {
			GamePro gamePro = new GamePro(null, 0);
			System.out.println("name:");
			gamePro.setName(scan.nextLine());
			System.out.println("score:");
			gamePro.setScore(scan.nextInt());
			scan.nextLine();
			gm.add(gamePro);
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
		if(list.contains(gamepro)) {
			int index = list.indexOf(gamepro);
			list.set(index, gamepro);
			return false;
		}
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

