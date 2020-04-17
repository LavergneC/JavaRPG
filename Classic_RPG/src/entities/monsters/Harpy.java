package entities.monsters;

import entities.Monster;
import game_management.Interfaces.GUI;

public class Harpy extends Monster{
	private static final int HP = 230;
	private static final int STAMINA = 840;
	private static final String NAME = "Flying harpy";
	private static final int AGILITY = 20;
	private static final int STRENGTH = 13;
	private static final int INTELLIGENCE = 1;
	private static final int XP_GIVEN = 25;

	public Harpy() 
	{
		super(HP, STAMINA, NAME, AGILITY, STRENGTH, INTELLIGENCE, XP_GIVEN);
	}
	
	protected int receiveAttack(int dmgIncoming) {
		int dmg = dmgIncoming;
		
		if(getStamina() >= 250) {
			staminaChange(false, 250);
			dmg = 0;
			GUI.edit_message(name + " flew over the attack");
		}
		else if(getStamina() >= 100) {
			staminaChange(false, 100);
			dmg /= 2;
			GUI.edit_message(name + " flew but still got hit");
		}
		hpChange(false, dmg);
		
		return dmg;
	}
}
