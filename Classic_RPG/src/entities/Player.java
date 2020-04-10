package entities;

import game_management.Action_Enums.Attack;
import game_management.Action_Enums.Game_action;

public abstract class Player extends Entity{
	
	protected boolean defense_position;
	
	public Player(int hp_, int stamina_, String name_, int agility_, int strength_, int intelligence_) {
		super(hp_, stamina_, name_, agility_, strength_, intelligence_);
		defense_position = false;
	}
	
	public Player(int hp_, int stamina_, String name_, int mana_, int agility_, int strength_, int intelligence_) {
		super(hp_, stamina_, name_, agility_, strength_, intelligence_, mana_);
		defense_position = false;
	}
	
	public abstract void specialHit(Entity target);
	
	public void setDefensePosition(boolean defense_position_) {
		this.defense_position = defense_position_;
	}
	
	@Override
	public String toString() {
		return name + " | " + "HP: " + getHp() + "/" + getCharacteristics().getMax_hp() + " | Stamina: " + getStamina() + "/" + getCharacteristics().getMax_stamina();
	}
	
	public abstract boolean actionPossible(Attack attackType);
	public abstract boolean actionPossible(Game_action action);
}
