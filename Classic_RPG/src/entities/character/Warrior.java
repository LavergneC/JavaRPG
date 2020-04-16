package entities.character;

import entities.Entity;
import entities.Player;
import game_management.Interfaces.GUI;
import game_management.Action_Enums.Attack;
import game_management.Action_Enums.Game_action;

public class Warrior extends Player{
	final static int BASE_HP = 1500;
	final static int BASE_STAMINA = 2000;
	
	final static int BASE_STRENGTH = 30;
	final static int BASE_AGILITY = 5;
	final static int BASE_INTELLIGENCE = 5;
	
	public Warrior(String name) {
		super(BASE_HP, BASE_STAMINA, name, BASE_AGILITY, BASE_STRENGTH, BASE_INTELLIGENCE);
	}
	
	public void specialHit(Entity target) {
		GUI.edit_message(name + " use double smash on " + target.getName());
		basicHit(target);
		basicHit(target);
		staminaChange(false, 200);
	}
	
	public void receiveAttack(int dmgIncoming) {
		if(defense_position) {
			if(dmgIncoming > getCharacteristics().getStrength() * 5) {
				hpChange(false, dmgIncoming / 2);
			}
			else
				GUI.edit_message(this.name + " held behind his shield");
		}
		else {
			super.receiveAttack(dmgIncoming);
		}
	}
	
	public boolean actionPossible(Game_action action) {
		boolean r = false;
		switch(action) {
		case ATTACK:
			System.out.println("class warrior::ERROR can't get action possible for attaque");
			r = false;
			break;
			
		case DEFENSE:
			r = true;
			break;

		case REST:
			r = true;
			break;
		case PENDING:
		}
		return r;
	}
	
	public boolean  actionPossible(Attack attackType) {
		switch(attackType) {
		case BASIC_ATTACK:
			return getStamina() >= 200;
		case SPECIAL_ATTACK:
			return getStamina() >= 600;
		default:
			System.out.println("class warrior::ERROR this attack don't exist");
			return false;
		}
	}
}
