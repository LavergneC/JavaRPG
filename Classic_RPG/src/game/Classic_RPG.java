package game;

import java.util.Scanner;

import java.util.ArrayList;

public class Classic_RPG {
	private ArrayList<Level> levels;
	private Player player;
	private Turn currentTurn;

	public Classic_RPG() {
		levels = new ArrayList<Level>();
		levels.add(generateLevel1());
		levels.add(generateLevel2());
	}
	public void launchGame() {
		for(Level currentLevel : levels) {
			System.out.println("New level !\nLevel n°" + currentLevel.getNumber() + " : " + currentLevel.getName());
			for (Wave currentWave : currentLevel.getWaves()) {
				System.out.println("New wave ! Wave n°" + currentWave.getNumber());
				int turnIndex = 1;

				while(!currentWave.getMonsters().isEmpty() && player.getHp() > 0) {
					System.out.println("\nTurn n°" + turnIndex);
					currentTurn = new Turn(turnIndex, currentWave, player);
					currentTurn.run_turn();
					turnIndex++;
					waitEnter();
				}
			}
		}
	}
	public static void main(String[] args) {
		System.out.println("This is Classic RPG game; enjoy !");

		Classic_RPG Game = new Classic_RPG();
		Game.generatePlayer();
		Game.launchGame();
	}

	public void waitEnter() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("press Enter to continue...");

		@SuppressWarnings("unused")
		String s = scan.nextLine();
	}
	public Level generateLevel1() {
		Monster bebeDragon = new Monster(200,200,"Bébé dragon", 1, 20, 1);
		Monster fantomeDeChienSauvage1 = new Monster(100, 200, "Fantôme de chien sauvage", 1, 10, 1);
		Monster fantomeDeChienSauvage2 = new Monster(100, 200, "Fantôme de chien sauvage", 1, 10, 1);

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
		Monster trollGeant1 = new Monster(600, 200, "Toll Géant Grrr", 1, 30, 1);
		Monster trollGeant2 = new Monster(600, 200, "Toll Géant Grrr", 1, 30, 1);

		Monster fantomeDeChienSauvage1 = new Monster(100, 200, "Fantôme de chien sauvage", 1, 10, 1);
		Monster fantomeDeChienSauvage2 = new Monster(100, 200, "Fantôme de chien sauvage", 1, 10, 1);
		Monster fantomeDeChienSauvage3 = new Monster(100, 200, "Fantôme de chien sauvage", 1, 10, 1);
		Monster fantomeDeChienSauvage4 = new Monster(100, 200, "Fantôme de chien sauvage", 1, 10, 1);

		ArrayList<Monster> wave1Monsters = new ArrayList<Monster>();
		wave1Monsters.add(fantomeDeChienSauvage1);
		wave1Monsters.add(fantomeDeChienSauvage2);
		wave1Monsters.add(fantomeDeChienSauvage3);
		wave1Monsters.add(fantomeDeChienSauvage4);
		Wave wave1 = new Wave(1, wave1Monsters);

		ArrayList<Monster> wave2Monsters = new ArrayList<Monster>();
		wave2Monsters.add(trollGeant1);
		wave2Monsters.add(trollGeant2);
		Wave wave2 = new Wave(2, wave2Monsters);

		ArrayList<Wave> wavesLevel = new ArrayList<Wave>();
		wavesLevel.add(wave1);
		wavesLevel.add(wave2);

		return new Level("Clairière du donjon de Bjouty", 2, wavesLevel);
	}

	public void generatePlayer() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		boolean inputOK = false;

		System.out.println("How will this courageous aventurer named ?");
		String name = scan.nextLine();
		System.out.println("Oh ! " + name + " what a fabulous name !");

		do {
			System.out.println("\nPlease choose a class :\n  1)Warrior\n  2)Wizard\n  3)Ninja");
			String line = scan.nextLine();

			switch(line) {
			case "1":
				System.out.println("Great ! You will be a strong Warrior !");
				player = new Warrior(name);
				inputOK = true;
				break;
			case "2":
				System.out.println("Great ! You will be a powerfull Wizard !");
				player = new Wizard(name);
				inputOK = true;
				break;
			case "3":
				System.out.println("Great ! You will be a agile Ninja !");
				player = new Ninja(name);
				inputOK = true;
				break;
			default:
				inputOK = false;

			}
		}while (!inputOK);
		waitEnter();
	}
}
