package entities.character;

import entities.Entity;
import entities.Player;

public class Warrior extends Player{
	final static int BASE_HP = 2000;
	final static int BASE_STAMINA = 2000;
	
	final static int BASE_STRENGTH = 30;
	final static int BASE_AGILITY = 5;
	final static int BASE_INTELLIGENCE = 5;
	
	public Warrior(String name) {
		super(BASE_HP, BASE_STAMINA, name, BASE_AGILITY, BASE_STRENGTH, BASE_INTELLIGENCE);
	}
	
	public void specialHit(Entity target) {
		System.out.println(name + " use double smash on " + target.getName());
		basicHit(target);
		basicHit(target);
		staminaChange(false, 100);
	}
	
	public void receiveAttack(int dmgIncoming) {
		if(defense_position) {
			if(dmgIncoming > characteristics.getStrength()) {
				hpChange(false, dmgIncoming / 2);
			}
			else
				System.out.println(this.name + " held behind his shield");
		}
		else {
			super.receiveAttack(dmgIncoming);
		}
	}
}
