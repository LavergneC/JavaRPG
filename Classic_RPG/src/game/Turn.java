package game;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Turn {
	private int number;
	private Wave actual_wave;
	private Player player;
	
	public Turn(int number_, Wave wave_, Player player_) {
		number = number_;
		actual_wave = wave_;
		player = player_;
	}
	
	public boolean run_turn() {
		Scanner input_player = new Scanner(System.in);
		boolean is_valid = true;
		Action action = null;
		
		do {
			System.out.println(player.toString());
			actual_wave.printMonsters();
			
			System.out.println("\nWhich action ?\n  1) Attack\n  2) Rest\n  3) Defense\n");
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
			} while(!attack_valid);
			String choix_monstre = "";
			int index_monstre = 0;
			do {
				Iterator<Monster> it = this.actual_wave.getMonsters().iterator();
				System.out.println("Which monster attack ?");
				int cpt = 1;
				while(it.hasNext()) {
					Monster monster = it.next();
					System.out.println("  " + cpt + ") " + monster.toString());
					cpt ++;
				}
				choix_monstre = input_player.nextLine();
				index_monstre = Integer.parseInt(choix_monstre) - 1;
			}while(index_monstre > actual_wave.getMonsters().size() - 1);
	
			// Check Stamina to attack
			
			System.out.println("Player " + player.getName() + " attack " + actual_wave.getMonsters().get(index_monstre).getName());
			
			delaySec(2);
			
			/* Attack */
			switch(attack) {
			/* Think to handle monster differently when there are severals monsters targeted because index could 
			 * could be decaled if monster died */
			case BASIC_ATTACK:
				player.basicHit(actual_wave.getMonsters().get(index_monstre));
				break;
			
			case SPECIAL_ATTACK:
				player.specialHit(actual_wave.getMonsters().get(index_monstre));
				break;
				
				//TODO add new attacks when created
			}
			/* Remove monster from the wave if we get hp != 0 */
			if (actual_wave.getMonsters().get(index_monstre).getHp() <= 0)
				actual_wave.removeMonster(index_monstre);
			break;
			
		case REST:
			player.rest();
			break;
			
		case DEFENSE:
			player.setDefensePosition(true);
			break;
		 //TODO add new actions
		}
		
		/* Monsters turn */
		Iterator<Monster> it = this.actual_wave.getMonsters().iterator();
		
		while(it.hasNext()) {
			delaySec(2);
			Monster monster = it.next();
			if(monster.getStamina() < 50) { /* not enough stamina to attack */
				monster.rest();
			}
			else {
				System.out.println(monster.getName() + " attacks " + player.getName());
				monster.basicHit(player);
				if (player.getHp() <= 0) {
					System.out.println("Loser, you are defeated by weak monsters !");
					return false;
				}
			}
		}
		player.setDefensePosition(false);
		return true;
	}
	
	void delaySec(int s) {
		try {
			TimeUnit.SECONDS.sleep(s);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
}
