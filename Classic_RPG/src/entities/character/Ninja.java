package entities.character;

import java.util.ArrayList;

import entities.Entity;
import entities.Player;
import game_management.Interfaces.ActionModifier;
import game_management.Interfaces.GUI;
import game_management.Action_Enums.Attack;
import game_management.Action_Enums.Game_action;
import game_management.Action_Enums.Measure;

public class Ninja extends Player{
	final static int BASE_HP = 1000;
	final static int BASE_STAMINA = 3000;

	final static int BASE_STRENGTH = 10;
	final static int BASE_AGILITY = 20;
	final static int BASE_INTELLIGENCE = 10;

	public Ninja(String name) {
		super(BASE_HP, BASE_STAMINA, name, BASE_AGILITY, BASE_STRENGTH, BASE_INTELLIGENCE);
	}

	/* Should not be pared */
	public void specialHit(Entity target) {
		GUI.edit_message(name + " use surgical attack on " + target.getName());
		attack(target, this.getCharacteristics().getStrength() * 4 * this.getCharacteristics().getAgility() / 5);
		staminaChange(false, 300);
	}

	public void basicHit(Entity target) {
		int dmgs = getCharacteristics().getStrength() * 4 * this.getCharacteristics().getAgility() / 10;

		GUI.edit_message(name + " basic attack on " + target.getName());
		staminaChange(false, 130);
		this.attack(target, dmgs);
	}

	public void setDefensePosition(boolean defense_position_) {
		super.setDefensePosition(defense_position_);
		staminaChange(false, 150);
	}

	public void receiveAttack(int dmgIncoming) {
		if(defense_position) {
			GUI.edit_message(this.name + " did a beautiful dodge roll");
		}
		else {
			super.receiveAttack(dmgIncoming);
		}
	}

	public boolean actionPossible(Game_action action, ArrayList<ActionModifier> modifiers) {
		boolean r = false;
		switch(action) {
		case ATTACK:
			System.out.println("class ninja::ERROR can't get action possible for attaque");
			r = false;
			break;

		case DEFENSE:
			modifiers.add(new ActionModifier(Measure.STAMINA, -150));
			r = getStamina() >= 150;
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
			int dmgs = getCharacteristics().getStrength() * 4 * this.getCharacteristics().getAgility() / 10;
			modifiers.add(new ActionModifier(Measure.DAMAGES, dmgs));
			modifiers.add(new ActionModifier(Measure.STAMINA, -130));
			return getStamina() >= 130;
			
		case SPECIAL_ATTACK:
			int dmgsS = this.getCharacteristics().getStrength() * 4 * this.getCharacteristics().getAgility() / 5;
			modifiers.add(new ActionModifier(Measure.DAMAGES, dmgsS));
			modifiers.add(new ActionModifier(Measure.STAMINA, -300));
			return getStamina() >= 300;
			
		default:
			System.out.println("class ninja::ERROR this attack don't exist");
			return false;
		}
	}

	public void levelUp() {
		super.levelUp();
		this.characteristics.setAgility(this.characteristics.getAgility() + 1);
	}
}