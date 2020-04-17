package entities;

import game_management.Interfaces.GUI;

public class Monster extends Entity{
	
	private int cpt_basic = 0;
	private int xp_given;
	
	public Monster(int hp_, int stamina_, String name_, int agility_, int strength_, int intelligence_, int xp_given_) {
		super(hp_, stamina_, name_, agility_, strength_, intelligence_);
		xp_given = xp_given_;
	}
	
	public int getXpGiven() {
		return xp_given;
	}
	
	public void specialHit(Entity target) {
		GUI.edit_message(name + " target " + target.getName() + " with his special hit");
		attack(target, getCharacteristics().getStrength() * 7);
		staminaChange(false, 300);
	}
	
	public void action(Entity target) {
		if((cpt_basic == 2 && getStamina() < 400) || getStamina() < 50) { /* not enough stamina to attack */
			rest(true);
		}
		else {
			if(cpt_basic == 2) {
				specialHit(target);
				cpt_basic = 0;
			}
			else {
				basicHit(target);
				cpt_basic++;
			}
		}
	}
	
	@Override
	public String toString() {
		return name + " | " + "HP: " + getHp() + "/" + getCharacteristics().getMax_hp();
	}
}
