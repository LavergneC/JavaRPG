package entities.monsters;

import entities.Entity;
import entities.Monster;
import game_management.Interfaces.GUI;

public class Vampire extends Monster{
	private static final int HP = 360;
	private static final int STAMINA = 1000;
	private static final String NAME = "Vampire";
	private static final int AGILITY = 1;
	private static final int STRENGTH = 15;
	private static final int INTELLIGENCE = 20;
	private static final int XP_GIVEN = 53;
	
	public Vampire() {
		super(HP, STAMINA, NAME, AGILITY, STRENGTH, INTELLIGENCE, XP_GIVEN);
	}
	
	public void specialHit(Entity target) {
		int dmgDealt = 0;
		
		GUI.edit_message(name + " try to drink " + target.getName() + " blood");
		dmgDealt = attack(target, getCharacteristics().getIntelligence() * 7);
		staminaChange(false, 600);
		hpChange(true, dmgDealt);
	}
}
