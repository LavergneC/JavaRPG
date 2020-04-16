package entities;

public class Monster extends Entity{
	
	private int xp_given;
	
	public Monster(int hp_, int stamina_, String name_, int agility_, int strength_, int intelligence_, int xp_given_) {
		super(hp_, stamina_, name_, agility_, strength_, intelligence_);
		xp_given = xp_given_;
	}
	
	public int getXpGiven() {
		return xp_given;
	}
	
	@Override
	public String toString() {
		return name + " | " + "HP: " + getHp() + "/" + getCharacteristics().getMax_hp();
	}
}
