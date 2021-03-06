package game_management;

import entities.Monster;
import entities.Player;
import entities.monsters.Baby_Dragon;
import entities.monsters.Chien;
import entities.monsters.Harpy;
import entities.monsters.Kiugg;
import entities.monsters.Troll;
import entities.monsters.Vampire;
import game_management.Interfaces.GUI;
import game_management.Interfaces.PlayerCreation;
import game_management.Sections.Level;
import game_management.Sections.Turn;
import game_management.Sections.Wave;

import java.util.ArrayList;

public class Classic_RPG {
	private ArrayList<Level> levels;
	private Player player;
	private Turn currentTurn;
	private GUI gui;

	public Classic_RPG() {
		levels = new ArrayList<Level>();
		levels.add(generateLevel1());
		levels.add(generateLevel2());
		levels.add(generateLevel3());
		levels.add(generateLevel4());
	}
	public void launchGame() {
		gui = new GUI(this.player);
		gui.setVisible(true);
		gui.setButtonsEnable(false);
		
		for(Level currentLevel : levels) {
			System.out.println("New level !\nLevel n°" + currentLevel.getNumber() + " : " + currentLevel.getName());
			gui.infobarNewLevel(currentLevel.getNumber(), currentLevel.getName());
			for (Wave currentWave : currentLevel.getWaves()) {
				System.out.println("New wave ! Wave n°" + currentWave.getNumber());
				gui.infobarSetWave(currentWave.getNumber());
				int turnIndex = 1;

				while(!currentWave.getMonsters().isEmpty()) {
					System.out.println("\nTurn n°" + turnIndex);
					currentTurn = new Turn(turnIndex, currentWave, player, gui);
					gui.infobarSetTurn(currentTurn.getNumber());
					currentTurn.run_turn();
					turnIndex++;
					
					if( player.getHp() <= 0)
						return;
				}
				player.rest(false);
				
			}
			player.rest(false);
		}
	}
	public static void main(String[] args) {
		System.out.println("This is Classic RPG game; enjoy !");

		Classic_RPG Game = new Classic_RPG();
		Game.generatePlayer(Game);
		Game.launchGame();
	}

	public Level generateLevel1() {
		ArrayList<Monster> wave1Monsters = new ArrayList<Monster>();
		wave1Monsters.add(new Chien());
		wave1Monsters.add(new Chien());
		Wave wave1 = new Wave(1, wave1Monsters);

		ArrayList<Monster> wave2Monsters = new ArrayList<Monster>();
		wave2Monsters.add(new Baby_Dragon());
		Wave wave2 = new Wave(2, wave2Monsters);

		ArrayList<Wave> wavesLevel = new ArrayList<Wave>();
		wavesLevel.add(wave1);
		wavesLevel.add(wave2);

		return new Level("Bjouty's village exit", 1, wavesLevel);
	}

	public Level generateLevel2() {
		ArrayList<Monster> wave1Monsters = new ArrayList<Monster>();
		
		for(int i = 0; i < 4; i++) 
			wave1Monsters.add(new Chien());
		
		Wave wave1 = new Wave(1, wave1Monsters);
		
		ArrayList<Monster> wave2Monsters = new ArrayList<Monster>();
		wave2Monsters.add(new Troll());
		Wave wave2 = new Wave(2, wave2Monsters);

		ArrayList<Wave> wavesLevel = new ArrayList<Wave>();
		wavesLevel.add(wave1);
		wavesLevel.add(wave2);

		return new Level("Bjouty's donjon clearing", 2, wavesLevel);
	}
	
	public Level generateLevel3() {
		ArrayList<Monster> wave1Monsters = new ArrayList<Monster>();
		wave1Monsters.add(new Vampire());
		wave1Monsters.add(new Chien());
		wave1Monsters.add(new Chien());
		
		ArrayList<Monster> wave2Monsters = new ArrayList<Monster>();
		wave2Monsters.add(new Troll());
		wave2Monsters.add(new Troll());
		wave2Monsters.add(new Chien());
		
		ArrayList<Monster> wave3Monsters = new ArrayList<Monster>();
		wave3Monsters.add(new Harpy());
		wave3Monsters.add(new Harpy());
		wave3Monsters.add(new Harpy());
		
		Wave wave1 = new Wave(1, wave1Monsters);
		Wave wave2 = new Wave(2, wave1Monsters);
		Wave wave3 = new Wave(3, wave1Monsters);
		
		ArrayList<Wave> wavesLevel = new ArrayList<Wave>();
		wavesLevel.add(wave1);
		wavesLevel.add(wave2);
		wavesLevel.add(wave3);
		
		return new Level("Bjouty's donjon hall", 3, wavesLevel);
	}

	public Level generateLevel4() {
		ArrayList<Monster> wave1Monsters = new ArrayList<Monster>();
		wave1Monsters.add(new Kiugg());
		Wave wave1 = new Wave(1, wave1Monsters);
		ArrayList<Wave> wavesLevel = new ArrayList<Wave>();
		wavesLevel.add(wave1);
		
		return new Level("Bjouty's tresure room", 4, wavesLevel);
	}
	public void generatePlayer(Classic_RPG game) {
		PlayerCreation pc = new PlayerCreation(game);
		pc.setVisible(true);
		pc.waitPlayerCreated();
	}
	public void setPlayer(Player p) {
		this.player = p;
	}
}
