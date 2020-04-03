package game;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Turn {
	private int number;
	private Wave actual_wave;
	private Player player;
	private GUI gui;
	
	public Turn(int number_, Wave wave_, Player player_, GUI gui_) {
		number = number_;
		actual_wave = wave_;
		player = player_;
		gui = gui_;
	}
	
	public boolean run_turn() {
		@SuppressWarnings("resource")
		Scanner input_player = new Scanner(System.in);
		boolean is_valid = true;
		Action action = null;
		
		gui.updateMonsters(actual_wave.getMonsters());
		
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
	
			// TODO Check Stamina to attack
			
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
			
			gui.updateMonsters(actual_wave.getMonsters());
			break;
			
		case REST:
			player.rest();
			break;
			
		case DEFENSE:
			player.setDefensePosition(true);
			break;
		 //TODO add new actions
		}
		gui.updatePlayerBars(player);
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
				gui.updatePlayerBars(player);
				if (player.getHp() <= 0) {
					System.out.println("Loser, you are defeated by weak monsters !");
					return false;
				}
			}
		}
		
		gui.updateMonsters(actual_wave.getMonsters());
		player.setDefensePosition(false);
		return true;
	}
	
	public int getNumber() {
		return number;
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
