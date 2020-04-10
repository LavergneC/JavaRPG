package game_management.Sections;
import java.util.ArrayList;

import entities.Monster;

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
	
	public void removeMonster(int index) {
		monsters.remove(index);
	}
	
	public void printMonsters() {
		for (Monster monster  : getMonsters()) {
			System.out.println(monster.toString());
		}
	}
}
