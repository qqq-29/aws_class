package day01;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class 연습day01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Gamefunction1 gf = new Gamefunction2();
		GameScore gamescore = new GameScore(null,0);
		for(int i=0; i<5; i++) {
			gamescore.setName(scan.nextLine());
			gamescore.setScore(scan.nextInt());
			scan.nextLine();
			gf.add(gamescore);
		}
	}

}

interface Gamefunction1{
	boolean add(GameScore gamescore);
	GameScore remove(String name);
	GameScore find(String name);
	void sortset();
	void printall();
}

class Gamefunction2 implements Gamefunction1{
	private ArrayList <GameScore> list = new ArrayList<GameScore>();

	@Override
	public boolean add(GameScore gamescore) {
		if(gamescore==null) {
			return false;
		}
		return false;
	}

	@Override
	public GameScore remove(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameScore find(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sortset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printall() {
		// TODO Auto-generated method stub
		
	}
}

class GameScore implements Comparable<GameScore>{

	private String name;
	private int score;
	
	public GameScore(String name, int score) {
		this.name=name;
		this.score=score;
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameScore other = (GameScore) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(GameScore o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}