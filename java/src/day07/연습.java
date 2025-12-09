package day07;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class 연습 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);

	}

}

interface GameRecordMan{
	boolean add(GameRecord gameRecord);
	GameRecord remove(String name);
	GameRecord find(String name);
	void printAll();
	void sortByScore();
}

class GameRecordManImp implements GameRecordMan{

	private ArrayList<GameRecord> list = new ArrayList<GameRecord>();
	@Override
	public boolean add(GameRecord gameRecord) {
		if(gameRecord == null) {
			return false;			
		}
		if(!list.contains(gameRecord)) {
			return list.add(gameRecord);
		}
		
		int index = list.indexOf(gameRecord);
		list.set(index,gameRecord);
		
		return false;	
	}

	@Override
	public GameRecord remove(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameRecord find(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortByScore() {
		// TODO Auto-generated method stub
		
	}
	
}

class GameRecord2 implements Comparable<GameRecord2>{

	private String name;
	private int score;
	
	@Override
	public int compareTo(GameRecord2 o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
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
	public GameRecord2(String name, int score) {
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
		GameRecord2 other = (GameRecord2) obj;
		return Objects.equals(name, other.name);
	}
	
}