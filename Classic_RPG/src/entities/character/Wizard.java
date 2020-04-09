package entities.character;

import entities.Entity;
import entities.Player;
import entities.characterisics.MagicianCharacteristiques;

public class Wizard extends Player{
	final static int BASE_HP = 750;
	final static int BASE_STAMINA = 1000;
	final static int BASE_MANA = 1000;
	
	final static int BASE_STRENGTH = 5;
	final static int BASE_AGILITY = 5;
	final static int BASE_INTELLIGENCE = 30;
	
	private int magicShieldHp;
	
	public Wizard(String name) {
		super(BASE_HP, BASE_STAMINA, name, BASE_MANA, BASE_AGILITY, BASE_STRENGTH, BASE_INTELLIGENCE);
		magicShieldHp = 0;
	}
	
	public void specialHit(Entity target) {
		System.out.println(name + " target " + target.getName() + " with fire breath");
		attack(target, getCharacteristics().getIntelligence() * 3);
		
		if(getMana() - 75 < 0) {
			setMana(0);
		}
		else {
			setMana(getMana() - 75);
		}
	}
	
	public void receiveAttack(int dmgIncoming) {
		if(defense_position) {
			int diff = magicShieldHp - dmgIncoming;
			magicShieldHp -= dmgIncoming;
			
			if(diff < 0) {
				hpChange(false, Math.abs(diff));
			}
			else {
				System.out.println(this.name + " stayed behind his magic shield");
			}
		}
		else {
			super.receiveAttack(dmgIncoming);
		}
	}
	
	public void setDefensePosition(boolean defense_position_){
		if(defense_position_) {
			magicShieldHp = getCharacteristics().getIntelligence() * 2;
			setMana(getMana() - 55);
		}
		super.setDefensePosition(defense_position_);
	}
	
	public void rest() {
		super.rest();
		setMana(getMana() + (int)Math.ceil(getMana() / 15)); 
	}
	
	@Override
	public String toString() {
		return name + " | " + "HP: " + getHp() + "/" + getCharacteristics().getMax_hp() + " | Stamina: " + getStamina() + "/" + getCharacteristics().getMax_stamina() + 
				" | Mana: " + getMana() + "/" + ((MagicianCharacteristiques)getCharacteristics()).getManaMax();
	}
}
