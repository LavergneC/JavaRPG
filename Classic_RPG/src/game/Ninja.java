package game;

public class Ninja extends Player{
	final static int BASE_HP = 1300;
	final static int BASE_STAMINA = 3000;
	
	final static int BASE_STRENGTH = 10;
	final static int BASE_AGILITY = 20;
	final static int BASE_INTELLIGENCE = 10;
	
	public Ninja(String name) {
		super(BASE_HP, BASE_STAMINA, name, BASE_AGILITY, BASE_STRENGTH, BASE_INTELLIGENCE);
	}
	
	/* Should not be pared */
	public void specialHit(Entity target) {
		attack(target, this.characteristics.getStrength() * 1 + 3 * this.characteristics.getAgility());
		stamina -= 50;
	}
	
	public void receiveAttack(int dmgIncoming) {
		// Add esquive based on agility and random
		if(defense_position) {
			if(dmgIncoming > characteristics.getStrength()) {
				hpChange(false, dmgIncoming / 2);
			}
			else {
				System.out.println(this.name + " did a beautiful dodge roll");
			}
		}
		else {
			super.receiveAttack(dmgIncoming);
		}
	}
}