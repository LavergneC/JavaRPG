package game_management;

import entities.Monster;
import entities.Player;
import game_management.parts.Level;
import game_management.parts.Turn;
import game_management.parts.Wave;

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

				while(!currentWave.getMonsters().isEmpty() && player.getHp() > 0) {
					System.out.println("\nTurn n°" + turnIndex);
					currentTurn = new Turn(turnIndex, currentWave, player, gui);
					gui.infobarSetTurn(currentTurn.getNumber());
					currentTurn.run_turn();
					turnIndex++;
				}
				player.rest();
			}
			player.rest();
		}
	}
	public static void main(String[] args) {
		System.out.println("This is Classic RPG game; enjoy !");

		Classic_RPG Game = new Classic_RPG();
		Game.generatePlayer(Game);
		Game.launchGame();
	}

	public Level generateLevel1() {
		Monster bebeDragon = new Monster(200,600,"Bébé dragon", 1, 34, 1);
		Monster fantomeDeChienSauvage1 = new Monster(100, 500, "Fantôme de chien sauvage", 1, 13, 1);
		Monster fantomeDeChienSauvage2 = new Monster(100, 500, "Fantôme de chien sauvage", 1, 13, 1);

		ArrayList<Monster> wave1Monsters = new ArrayList<Monster>();
		wave1Monsters.add(fantomeDeChienSauvage1);
		wave1Monsters.add(fantomeDeChienSauvage2);
		Wave wave1 = new Wave(1, wave1Monsters);

		ArrayList<Monster> wave2Monsters = new ArrayList<Monster>();
		wave2Monsters.add(bebeDragon);
		Wave wave2 = new Wave(2, wave2Monsters);

		ArrayList<Wave> wavesLevel = new ArrayList<Wave>();
		wavesLevel.add(wave1);
		wavesLevel.add(wave2);

		return new Level("Sortie du village du Bjouty", 1, wavesLevel);
	}

	public Level generateLevel2() {
		Monster fantomeDeChienSauvage1 = new Monster(100, 500, "Fantôme de chien sauvage", 1, 13, 1);
		Monster fantomeDeChienSauvage2 = new Monster(100, 500, "Fantôme de chien sauvage", 1, 13, 1);
		Monster fantomeDeChienSauvage3 = new Monster(100, 500, "Fantôme de chien sauvage", 1, 13, 1);
		Monster fantomeDeChienSauvage4 = new Monster(100, 500, "Fantôme de chien sauvage", 1, 13, 1);

		ArrayList<Monster> wave1Monsters = new ArrayList<Monster>();
		wave1Monsters.add(fantomeDeChienSauvage1);
		wave1Monsters.add(fantomeDeChienSauvage2);
		wave1Monsters.add(fantomeDeChienSauvage3);
		wave1Monsters.add(fantomeDeChienSauvage4);
		Wave wave1 = new Wave(1, wave1Monsters);

		Monster trollGeant1 = new Monster(600, 1000, "Toll Géant Grrr", 1, 20, 1);
		Monster trollGeant2 = new Monster(600, 1000, "Toll Géant Grrr", 1, 20, 1);
		
		ArrayList<Monster> wave2Monsters = new ArrayList<Monster>();
		wave2Monsters.add(trollGeant1);
		wave2Monsters.add(trollGeant2);
		Wave wave2 = new Wave(2, wave2Monsters);

		ArrayList<Wave> wavesLevel = new ArrayList<Wave>();
		wavesLevel.add(wave1);
		wavesLevel.add(wave2);

		return new Level("Clairière du donjon de Bjouty", 2, wavesLevel);
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
