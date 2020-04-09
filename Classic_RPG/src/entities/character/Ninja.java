package entities.character;

import entities.Entity;
import entities.Player;
import game_management.Action;
import game_management.Attack;

public class Ninja extends Player{
	final static int BASE_HP = 1300;
	final static int BASE_STAMINA = 3000;
	
	final static int BASE_STRENGTH = 10;
	final static int BASE_AGILITY = 20;
	final static int BASE_INTELLIGENCE = 10;
	
	public Ninja(String name) {
		super(BASE_HP, BASE_STAMINA, name, BASE_AGILITY, BASE_STRENGTH, BASE_INTELLIGENCE);
	}
	
	/* Should not be pared */
	public void specialHit(Entity target) {
		System.out.println(name + " use surgical attack on " + target.getName());
		attack(target, this.getCharacteristics().getStrength() * 1 + 3 * this.getCharacteristics().getAgility());
		staminaChange(false, 300);
	}
	
	public void receiveAttack(int dmgIncoming) {
		// Add esquive based on agility and random
		if(defense_position) {
			if(dmgIncoming > getCharacteristics().getStrength()) {
				hpChange(false, dmgIncoming / 2);
			}
			else {
				System.out.println(this.name + " did a beautiful dodge roll");
			}
		}
		else {
			super.receiveAttack(dmgIncoming);
		}
	}
	
	public boolean actionPossible(Action action) {
		boolean r = false;
		switch(action) {
		case ATTACK:
			System.out.println("class ninja::ERROR can't get action possible for attaque");
			r = false;
			break;
			
		case DEFENSE:
			r = true;
			break;

		case REST:
			r = true;
			break;
		}
		return r;
	}
	
	public boolean  actionPossible(Attack attackType) {
		switch(attackType) {
		case BASIC_ATTACK:
			return getStamina() >= 200;
		case SPECIAL_ATTACK:
			return getStamina() >= 300;
		default:
			System.out.println("class ninja::ERROR this attack don't exist");
			return false;
		}
	}
}