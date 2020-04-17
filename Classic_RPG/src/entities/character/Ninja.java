package entities.character;

import entities.Entity;
import entities.Player;
import game_management.Interfaces.GUI;
import game_management.random.Random;
import game_management.random.TestOutput;
import game_management.Action_Enums.Attack;
import game_management.Action_Enums.Game_action;

public class Ninja extends Player {
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
		int dmgs =  this.getCharacteristics().getStrength() * 4 * this.getCharacteristics().getAgility() / 5;
		int success = getCharacteristics().getAgility() + 75;
		int specialDamage = Random.EffectiveDamage(success, dmgs);
		GUI.edit_message(name + " use surgical attack on " + target.getName());
		attack(target, specialDamage);
		staminaChange(false, 300);
	}

	public void basicHit(Entity target) {
		int dmgs = getCharacteristics().getStrength() * 4 * this.getCharacteristics().getAgility() / 10;
		int success = getCharacteristics().getAgility() + 75;
		Random.EffectiveDamage(success, dmgs);
		GUI.edit_message(name + " basic attack on " + target.getName());
		staminaChange(false, 130);
		this.attack(target, dmgs);
	}

	public void receiveAttack(int dmgIncoming) {
		int success = getCharacteristics().getAgility() + 75;
		TestOutput result1D100 = Random.test1D100(success);
		if (defense_position && getStamina() >= 150 && result1D100 != TestOutput.CRITICAL_FAILURE) {
			staminaChange(false, 150);
			GUI.edit_message(this.name + " did a beautiful dodge roll");
		} else {
			super.receiveAttack(dmgIncoming);
		}
	}

	public boolean actionPossible(Game_action action) {
		boolean r = false;
		switch (action) {
		case ATTACK:
			System.out.println("class ninja::ERROR can't get action possible for attaque");
			r = false;
			break;

		case DEFENSE:
			r = getStamina() >= 150;
			break;

		case REST:
			r = true;
			break;
		case PENDING:
		}
		return r;
	}

	public boolean actionPossible(Attack attackType) {
		switch (attackType) {
		case BASIC_ATTACK:
			return getStamina() >= 130;
		case SPECIAL_ATTACK:
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