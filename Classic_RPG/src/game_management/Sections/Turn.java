package game_management.Sections;

import game_management.Action_Enums.Attack;
import game_management.Action_Enums.Game_action;
import game_management.Interfaces.GUI;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import entities.Monster;
import entities.Player;
import entities.character.Wizard;

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
		int cur_player_life = player.getHp();
		int cur_player_mana_stamina = player.getStamina();
		if(player instanceof Wizard)
			cur_player_mana_stamina = player.getMana();
		switch(action) {
		case ATTACK:
			index_monstre = monsterPick(this.actual_wave);
			int cur_life = actual_wave.getMonsters().get(index_monstre).getHp();
			int cur_stamina = actual_wave.getMonsters().get(index_monstre).getStamina();
			
			switch(gui.getAttackType()) {
			case BASIC_ATTACK:
				gui.clear_console();
				player.basicHit(actual_wave.getMonsters().get(index_monstre));
				gui.generate_commands(gui.getAttackType(), player, actual_wave.getMonsters().get(index_monstre), cur_life, cur_stamina);
				break;
			case SPECIAL_ATTACK:
				gui.clear_console();
				player.specialHit(actual_wave.getMonsters().get(index_monstre));
				gui.generate_commands(gui.getAttackType(), player, actual_wave.getMonsters().get(index_monstre), cur_life, cur_stamina);
				break;
			}
			
			/* Remove monster from the wave if we get hp != 0 */
			if (actual_wave.getMonsters().get(index_monstre).getHp() <= 0) {
				boolean earn_lvl = player.earn_xp(actual_wave.getMonsters().get(index_monstre).getXpGiven());
				System.out.println("Xp : " + player.getXp());
				actual_wave.removeMonster(index_monstre);
				if (earn_lvl) {
					gui.update_playerLevel(player.getLevel());
					gui.updatePlayerBars(player);
				}
			}

			gui.updateMonsters(actual_wave.getMonsters());
			break;

		case REST:
			gui.clear_console();
			player.rest(true);
			gui.generate_commands(action, player, cur_player_life, cur_player_mana_stamina, player instanceof Wizard);
			break;

		case DEFENSE:
			gui.clear_console();
			player.setDefensePosition(true);
			gui.generate_commands(action, player, cur_player_life, cur_player_mana_stamina, player instanceof Wizard);
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
			cur_player_life = player.getHp();
			cur_player_mana_stamina = player.getStamina();
			
			if (player instanceof Wizard) {
				cur_player_mana_stamina = player.getMana();
			}
				
			Monster monster = it.next();
			int cur_monster_life = monster.getHp();
			int cur_monster_stamina = monster.getStamina();
			String action_monster = monster.action(player);
			if (action_monster == "Special")
				gui.generate_commands(Attack.SPECIAL_ATTACK, monster, player, cur_player_life, cur_player_mana_stamina);
			else if(action_monster == "Basic")
				gui.generate_commands(Attack.BASIC_ATTACK, monster, player, cur_player_life, cur_player_mana_stamina);
			else if (action_monster == "Rest") {
				gui.generate_commands(Game_action.REST, monster,cur_monster_life, cur_monster_stamina, false);
			}
			gui.updatePlayerBars(player);
			if (player.getHp() <= 0) {
				GUI.edit_message("Loser, you are defeated by weak monsters !");
				return false;
			}
		}
		player.setDefensePosition(false);
		return true;
	}

	public int getNumber() {
		return number;
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
