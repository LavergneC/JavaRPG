package game;

import java.util.Iterator;
import java.util.Scanner;

public class Turn {
	private int number;
	private Wave actual_wave;
	private Player player;
	
	public Turn(int number_, Wave wave_, Player player_) {
		number = number_;
		actual_wave = wave_;
		player = player_;
	}
	
	public void turn() {
		Scanner input_player = new Scanner(System.in);
		boolean is_valid = true;
		Action action = null;
		
		do {
			System.out.println("Which action ?\n  1) Attack\n  2) Rest\n  3) Defense\n");
			String choix = input_player.nextLine();
			switch(choix) {
			case "1":
				action = Action.ATTACK;
				is_valid = true;
				break;
			case "2":
				action = Action.REST;
				is_valid = true;
				break;
			case "3":
				action = Action.DEFENSE;
				is_valid = true;
				break;
			default:
				is_valid = false;
			}
		}while(!is_valid);
		
		boolean attack_valid;
		Attack attack = null;
		switch(action) {
		case ATTACK:
			do {
				System.out.println("Which attack ? \n 1)  Basic hit \n 2)  Special hit \n");
				String choix_attaque = input_player.nextLine();
				switch(choix_attaque) {
				case "1":
					attack = Attack.BASIC_ATTACK;
					attack_valid = true;
					break;
				case "2":
					attack = Attack.SPECIAL_ATTACK;
					attack_valid = true;
					break;
				default:
					attack_valid = false;
				}
			} while(attack_valid);
			String choix_monstre = "";
			int index_monstre = 0;
			do {
				Iterator<Monster> it = this.actual_wave.getMonsters().iterator();
				System.out.println("Which monster attack ? \n");
				int cpt = 1;
				while(it.hasNext()) {
					Monster monster = it.next();
					System.out.println("  " + cpt + ") " + monster.getName() + "\n");
					cpt ++;
				}
				choix_monstre = input_player.nextLine();
				index_monstre = Integer.parseInt(choix_monstre) - 1;
			}while(index_monstre > actual_wave.getMonsters().size() - 1);
	
			// Check Stamina to attack
			
			System.out.println("Player " + player.getName() + " attack " + actual_wave.getMonsters().get(index_monstre).getName());
			
		}
	}
}
