package entities.monsters;

import entities.Entity;
import entities.Monster;
import game_management.Interfaces.GUI;

public class Kiugg extends Monster{
	private static final int HP = 1500;
	private static final int STAMINA = 500;
	private static final String NAME = "Kiugg the resentful";
	private static final int AGILITY = 1000;
	private static final int STRENGTH = 1000;
	private static final int INTELLIGENCE = 1000;
	private static final int XP_GIVEN = 220;
	
	private int dmgtaken;

	public Kiugg() 
	{
		super(HP, STAMINA, NAME, AGILITY, STRENGTH, INTELLIGENCE, XP_GIVEN);
		dmgtaken = 0;
	}
	
	protected int receiveAttack(int dmgIncoming) {
		dmgtaken += dmgIncoming;
		
		int ret = super.receiveAttack(dmgIncoming);
		GUI.edit_message("He will remember that...");
		
		return ret;
	}
	
	public String action(Entity target) {
		if(getStamina() >= 500) {
			basicHit(target);
			return "Attack";
		}
		else {
			rest(true);
			return "Rest";
		}
	}
	
	public void basicHit(Entity target)
	{
		int dmgs = dmgtaken / 4;
		
		GUI.edit_message(name + " send back dommages to " + target.getName());
		staminaChange(false, 500);
		this.attack(target, dmgs);
	}
}
