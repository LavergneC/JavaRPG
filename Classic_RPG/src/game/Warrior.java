package game;

public class Warrior extends Player{
	final static int BASE_HP = 2000;
	final static int BASE_STAMINA = 2000;
	
	final static int BASE_STRENGTH = 30;
	final static int BASE_AGILITY = 5;
	final static int BASE_INTELLIGENCE = 5;
	
	public Warrior(String name) {
		super(BASE_HP, BASE_STAMINA, name, BASE_AGILITY, BASE_STRENGTH, BASE_INTELLIGENCE);
	}
	
	public void specialHit(Entity target) {
		basicHit(target);
		basicHit(target);
		stamina -= 100;
	}
	
	public void receiveAttack(int dmgIncoming) {
		if(defense_position) {
			if(dmgIncoming > characteristics.getStrength()) {
				hp -= dmgIncoming / 2;
				System.out.println(this.name + " took " + dmgIncoming / 2 + "damage(s)");
			}
			else
				System.out.println(this.name + " took 0 damage(s)");
		}
		else {
			hp -= dmgIncoming;
			System.out.println(this.name + " took " + dmgIncoming + "damage(s)");
		}
	}
}
