package entities.character;

import java.util.ArrayList;

import entities.Entity;
import entities.Player;
import entities.characterisics.MagicianCharacteristiques;
import game_management.Interfaces.ActionModifier;
import game_management.Interfaces.GUI;
import game_management.Action_Enums.Attack;
import game_management.Action_Enums.Game_action;
import game_management.Action_Enums.Measure;


public class Wizard extends Player{
	final static int BASE_HP = 850;
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
		attack(target, getCharacteristics().getIntelligence() * 6);
		
		manaChange(false, 100);
	}
	
	public void basicHit(Entity target) {
		int dmgs = getCharacteristics().getIntelligence() * 3;
		
		GUI.edit_message(name + " fire a minor fire ball on " + target.getName());
		manaChange(false, 40);
		this.attack(target, dmgs);
	}

	public int receiveAttack(int dmgIncoming) {
		int dmgTaken = 0;
		
		if(defense_position) {
			int diff = magicShieldHp - dmgIncoming;
			magicShieldHp -= dmgIncoming;

			if(diff < 0) {
				hpChange(false, Math.abs(diff));
				dmgTaken = Math.abs(diff);
			}
			else {
				GUI.edit_message(this.name + " stayed behind his magic shield (" + magicShieldHp + ")");
			}
		}
		else {
			super.receiveAttack(dmgIncoming);
			dmgTaken = dmgIncoming;
		}
		
		return dmgTaken;
	}

	public void setDefensePosition(boolean defense_position_){
		if(defense_position_) {
			magicShieldHp = getMagicShieldMaxHp();
			manaChange(false, 64);
			super.setDefensePosition(defense_position_);
		}
	}
	
	public void rest(boolean printView) {
		super.rest(printView);
		int manaSup = ((MagicianCharacteristiques)getCharacteristics()).getManaMax() / 9;
		manaSup = (int)Math.ceil(manaSup);
		manaChange(true, manaSup);
	}

	@Override
	public String toString() {
		return name + " | " + "HP: " + getHp() + "/" + getCharacteristics().getMax_hp() + " | Stamina: " + getStamina() + "/" + getCharacteristics().getMax_stamina() + 
				" | Mana: " + getMana() + "/" + ((MagicianCharacteristiques)getCharacteristics()).getManaMax();
	}

	public boolean actionPossible(Game_action action, ArrayList<ActionModifier> modifiers) {
		boolean r = false;
		switch(action) {
		case ATTACK:
			System.out.println("class wizard::ERROR can't get action possible for attaque");
			r = false;
			break;

		case DEFENSE:
			modifiers.add(new ActionModifier(Measure.MANA_SHEILD, getMagicShieldMaxHp() - magicShieldHp));
			modifiers.add(new ActionModifier(Measure.MANA, -64));
			
			r = getMana() >= 64;
			break;

		case REST:
			modifiers.add(new ActionModifier(Measure.HP, getCharacteristics().getMax_hp() / 14));
			modifiers.add(new ActionModifier(Measure.STAMINA, getCharacteristics().getMax_stamina() / 5));
			
			MagicianCharacteristiques charac = (MagicianCharacteristiques)getCharacteristics();
			modifiers.add(new ActionModifier(Measure.MANA, charac.getManaMax() / 9));
			r = true;
			break;
		case PENDING:
		}
		return r;
	}

	public boolean  actionPossible(Attack attackType, ArrayList<ActionModifier> modifiers) {
		switch(attackType) {
		case BASIC_ATTACK:
			modifiers.add(new ActionModifier(Measure.DAMAGES, getCharacteristics().getIntelligence() * 3));
			modifiers.add(new ActionModifier(Measure.MANA, -40));
			return getMana() >= 40;
			
		case SPECIAL_ATTACK:
			modifiers.add(new ActionModifier(Measure.DAMAGES, getCharacteristics().getIntelligence() * 6));
			modifiers.add(new ActionModifier(Measure.MANA, -100));
			return getMana() >= 100;
			
		default:
			System.out.println("class wizard::ERROR this attack don't exist");
			return false;
		}
	}
	
	public void levelUp() {
		super.levelUp();
		((MagicianCharacteristiques)this.characteristics).setManaMax(((int)Math.ceil(((MagicianCharacteristiques)this.characteristics).getManaMax() * 1.1)));
		this.manaChange(true, getMana() + (int)Math.ceil(((MagicianCharacteristiques)this.characteristics).getManaMax() * 0.1));
		this.characteristics.setIntelligence(this.characteristics.getIntelligence() + 1);
	}

	public int getMagicShieldHp() {
		return magicShieldHp;
	}

	public int getMagicShieldMaxHp() {
		return getCharacteristics().getIntelligence() * 8;
	}
}

