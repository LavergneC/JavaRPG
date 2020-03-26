package game;

public class Warrior extends Player{
	public Warrior(String name) {
		super(2000, 2000, name, 5, 30);
	}
	
	public void specialHit(Entity target) {
		basicHit(target);
		basicHit(target);
		basicHit(target);
		stamina -= 150;
	}
	
	public void receiveAttack(int dmgIncoming) {
		if(defense_position) {
			if(dmgIncoming > characteristics.getStrength()) {
				hp -= dmgIncoming / 2;
			}
		}
	}
}
