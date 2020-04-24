package entities.character;

import java.util.ArrayList;

import entities.Entity;
import entities.Player;
import game_management.Interfaces.ActionModifier;
import game_management.Interfaces.GUI;
import game_management.Action_Enums.Attack;
import game_management.Action_Enums.Game_action;
import game_management.Action_Enums.Measure;

public class Warrior extends Player{
	final static int BASE_HP = 1300;
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
	
	public int receiveAttack(int dmgIncoming) {
		int dmgTaken = 0;
		
		if(defense_position) {
			if(dmgIncoming > getCharacteristics().getStrength() * 5) {
				hpChange(false, dmgIncoming / 2);
				dmgTaken = dmgIncoming /2;
			}
			else
				GUI.edit_message(this.name + " held behind his shield");
		}
		else {
			super.receiveAttack(dmgIncoming);
			dmgTaken = dmgIncoming;
		}
		
		return dmgTaken;
	}
	
	public boolean actionPossible(Game_action action, ArrayList<ActionModifier> modifiers) {
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
			modifiers.add(new ActionModifier(Measure.HP, getCharacteristics().getMax_hp() / 14));
			modifiers.add(new ActionModifier(Measure.STAMINA, getCharacteristics().getMax_stamina() / 5));
			r = true;
			break;
			
		case PENDING:
		}
		return r;
	}
	
	public boolean actionPossible(Attack attackType, ArrayList<ActionModifier> modifiers) {
		switch(attackType) {
		case BASIC_ATTACK:
			modifiers.add(new ActionModifier(Measure.DAMAGES, getCharacteristics().getStrength() * 4));
			modifiers.add(new ActionModifier(Measure.STAMINA, -200));
			return getStamina() >= 200;
			
		case SPECIAL_ATTACK:
			modifiers.add(new ActionModifier(Measure.DAMAGES, getCharacteristics().getStrength() * 4 * 2));
			modifiers.add(new ActionModifier(Measure.STAMINA, -600));
			return getStamina() >= 600;
			
		default:
			System.out.println("class warrior::ERROR this attack don't exist");
			return false;
		}
	}
	
	public void levelUp() {
		super.levelUp();
		this.characteristics.setStrength(this.characteristics.getStrength() + 1);
	}
}
