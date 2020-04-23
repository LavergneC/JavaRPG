package entities.monsters;

import entities.Monster;

public class Chien extends Monster{
	private static final int HP = 230;
	private static final int STAMINA = 500;
	private static final String NAME = "Wild ghost dog";
	private static final int AGILITY = 1;
	private static final int STRENGTH = 13;
	private static final int INTELLIGENCE = 1;
	private static final int XP_GIVEN = 25;

	public Chien() 
	{
		super(HP, STAMINA, NAME, AGILITY, STRENGTH, INTELLIGENCE, XP_GIVEN);
	}

}
