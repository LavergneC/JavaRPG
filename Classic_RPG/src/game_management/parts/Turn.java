package game_management.parts;

import game_management.*;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import entities.Monster;
import entities.Player;

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
		Game_action action = null;

		gui.updateMonsters(actual_wave.getMonsters());
		
		gui.setButtonsEnable(true, player);
		do {// Wait user input
			action = gui.getGame_action();
			try {
				TimeUnit.MILLISECONDS.sleep(250);
			} catch (InterruptedException e) {}
		}while(action == Game_action.PENDING);
		gui.setButtonsEnable(false);

		int index_monstre = 0;
		switch(action) {
		case ATTACK:
			index_monstre = monsterPick(this.actual_wave);

			switch(gui.getAttackType()) {
			case BASIC_ATTACK:
				player.basicHit(actual_wave.getMonsters().get(index_monstre));
				break;
			case SPECIAL_ATTACK:
				player.specialHit(actual_wave.getMonsters().get(index_monstre));
				break;
			}

			/* Remove monster from the wave if we get hp != 0 */
			if (actual_wave.getMonsters().get(index_monstre).getHp() <= 0)
				actual_wave.removeMonster(index_monstre);

			gui.updateMonsters(actual_wave.getMonsters());
			break;

		case REST:
			player.rest();
			break;

		case DEFENCE:
			player.setDefensePosition(true);
			break;
			//TODO add new actions
		case PENDING: default:
			System.out.println("Turn Run error");
		}
		gui.setGame_action(Game_action.PENDING);
		
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

	int monsterPick(Wave wave) {
		int index_monstre = -1;
		
		do {
			index_monstre = gui.getMonsterTarget();
			try {
				TimeUnit.MILLISECONDS.sleep(250);
			} catch (InterruptedException e) {}
		}while(index_monstre == -1);
		
		gui.resetMonsterTarget();

		return index_monstre;
	}
}
