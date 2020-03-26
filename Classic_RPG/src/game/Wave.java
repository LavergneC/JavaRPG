package game;
import java.util.ArrayList;

public class Wave {
	private int number;
	private ArrayList<Monster> monsters;
	
	public Wave(int number_, ArrayList<Monster> list_monster) {
		number = number_;
		monsters = list_monster;
	}

	public int getNumber() {
		return number;
	}

	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
}
