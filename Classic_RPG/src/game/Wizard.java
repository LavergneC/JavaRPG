package game;

public class Wizard extends Player{
	final static int BASE_HP = 750;
	final static int BASE_STAMINA = 1000;
	
	final static int BASE_STRENGTH = 5;
	final static int BASE_AGILITY = 5;
	final static int BASE_INTELLIGENCE = 30;
	
	private int magicSheildHp;
	
	public Wizard(String name) {
		super(BASE_HP, BASE_STAMINA, name, BASE_AGILITY, BASE_STRENGTH, BASE_INTELLIGENCE);
		magicSheildHp = 0;
	}
	
	public void specialHit(Entity target) {
		attack(target, characteristics.getIntelligence() * 3);
		
		if(getMana() - 75 < 0) {
			setMana(0);
		}
		else {
			setMana(getMana() - 75);
		}
	}
	
	public void receiveAttack(int dmgIncoming) {
		if(defense_position) {
			int diff = magicSheildHp - dmgIncoming;
			magicSheildHp -= dmgIncoming;
			
			if(diff < 0) {
				hp -= Math.abs(diff);
			}
		}
		else {
			hp -= dmgIncoming;
		}
	}
	
	public void setDefensePosition(boolean defense_position_){
		if(defense_position_) {
			magicSheildHp = characteristics.getIntelligence() * 2;
			setMana(getMana() - 55);
		}
		super.setDefensePosition(defense_position_);
	}
}
