package game;

public class Ninja extends Player{
	
	public Ninja(String name) {
		super(1300, 3000, name, 10, 10, 20);
	}
	
	/* Should not be pared */
	public void specialHit(Entity target) {
		attack(target, this.characteristics.getStrength() * 1 + 3 * this.characteristics.getAgility());
	}
	
	public void receiveAttack(int dmgIncoming) {
		// Add esquive based on agility and random
		if(defense_position) {
			if(dmgIncoming > characteristics.getStrength()) {
				hp -= dmgIncoming / 2;
			}
		}
	}
}