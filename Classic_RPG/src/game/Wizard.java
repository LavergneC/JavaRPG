package game;

public class Wizard extends Player{
	private int magicSheildHp;
	
	public Wizard(String name) {
		super(750, 1000, name, 3000, 5, 5, 30);
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
