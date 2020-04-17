package entities.monsters;

import entities.Monster;

public class Troll extends Monster{
	private static final int HP = 1300;
	private static final int STAMINA = 1000;
	private static final String NAME = "Giant mountain troll";
	private static final int AGILITY = 1;
	private static final int STRENGTH = 30;
	private static final int INTELLIGENCE = 1;
	private static final int XP_GIVEN = 100;
	
	public Troll() {
		super(HP, STAMINA, NAME, AGILITY, STRENGTH, INTELLIGENCE, XP_GIVEN);
	}
}
