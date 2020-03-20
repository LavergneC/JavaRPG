package game;

public class Monster extends Entity{
	
	public Monster(int hp_, int stamina_, String name_, int agi, int stren) {
		super(hp_, stamina_, name_, agi, stren);
	}
	
	@Override
	public void receiveAttack(int dmgIncoming) {
		int newHp = super.hp - dmgIncoming;
		
		if (newHp <= 0) 
			super.hp = 0;
		else
			super.hp = newHp;
	}
}
