package entities.monsters;

import entities.Monster;

public class Baby_Dragon extends Monster{
	private static final int HP = 360;
	private static final int STAMINA = 1000;
	private static final String NAME = "Baby dragon";
	private static final int AGILITY = 1;
	private static final int STRENGTH = 38;
	private static final int INTELLIGENCE = 1;
	private static final int XP_GIVEN = 50;

	public Baby_Dragon() 
	{
		super(HP, STAMINA, NAME, AGILITY, STRENGTH, INTELLIGENCE, XP_GIVEN);
	}
}