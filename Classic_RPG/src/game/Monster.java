package game;

public class Monster extends Entity{
	
	public Monster(int hp_, int stamina_, String name_, int agility_, int strength_, int intelligence_) {
		super(hp_, stamina_, name_, agility_, strength_, intelligence_);
	}
	
	@Override
	public void receiveAttack(int dmgIncoming) {
		int newHp = hp - dmgIncoming;
		
		if (newHp <= 0) 
			hp = 0;
		else
			hp = newHp;
	}
}
