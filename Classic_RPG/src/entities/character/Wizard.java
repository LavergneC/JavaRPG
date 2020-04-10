package entities.character;

import entities.Entity;
import entities.Player;
import entities.characterisics.MagicianCharacteristiques;
import game_management.Interfaces.GUI;
import game_management.Action_Enums.Attack;
import game_management.Action_Enums.Game_action;

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
		GUI.edit_message(name + " target " + target.getName() + " with fire breath");
		attack(target, getCharacteristics().getIntelligence() * 3);
		
		manaChange(false, 75);
	}
	
	public void receiveAttack(int dmgIncoming) {
		if(defense_position) {
			int diff = magicShieldHp - dmgIncoming;
			magicShieldHp -= dmgIncoming;
			
			if(diff < 0) {
				hpChange(false, Math.abs(diff));
			}
			else {
				GUI.edit_message(this.name + " stayed behind his magic shield");
			}
		}
		else {
			super.receiveAttack(dmgIncoming);
		}
	}
	
	public void setDefensePosition(boolean defense_position_){
		if(defense_position_) {
			magicShieldHp = getCharacteristics().getIntelligence() * 2;
			manaChange(false, 55);
		}
		super.setDefensePosition(defense_position_);
	}
	
	public void rest() {
		super.rest();
		int manaSup = ((MagicianCharacteristiques)getCharacteristics()).getManaMax() / 15;
		manaSup = (int)Math.ceil(manaSup);
		manaChange(true, manaSup);
	}
	
	@Override
	public String toString() {
		return name + " | " + "HP: " + getHp() + "/" + getCharacteristics().getMax_hp() + " | Stamina: " + getStamina() + "/" + getCharacteristics().getMax_stamina() + 
				" | Mana: " + getMana() + "/" + ((MagicianCharacteristiques)getCharacteristics()).getManaMax();
	}
	
	public boolean actionPossible(Game_action action) {
		boolean r = false;
		switch(action) {
		case ATTACK:
			System.out.println("class wizard::ERROR can't get action possible for attaque");
			r = false;
			break;
			
		case DEFENSE:
			r = getMana() >= 55;
			break;

		case REST:
			r = true;
			break;
		case PENDING:
		}
		return r;
	}
	
	public boolean  actionPossible(Attack attackType) {
		switch(attackType) {
		case BASIC_ATTACK:
			return getStamina() >= 200;
		case SPECIAL_ATTACK:
			return getMana() >= 75;
		default:
			System.out.println("class wizard::ERROR this attack don't exist");
			return false;
		}
	}
}
