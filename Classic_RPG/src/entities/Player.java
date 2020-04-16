package entities;

import game_management.Action_Enums.Attack;
import game_management.Action_Enums.Game_action;

public abstract class Player extends Entity{
	
	protected boolean defense_position;
	protected int next_lvl_xp;
	protected int xp;
	private int level;
	
	public int getNext_lvl_xp() {
		return next_lvl_xp;
	}

	public Player(int hp_, int stamina_, String name_, int agility_, int strength_, int intelligence_) {
		super(hp_, stamina_, name_, agility_, strength_, intelligence_);
		defense_position = false;
		xp = 0;
		next_lvl_xp = 100;
		level = 1;
	}
	
	public Player(int hp_, int stamina_, String name_, int mana_, int agility_, int strength_, int intelligence_) {
		super(hp_, stamina_, name_, agility_, strength_, intelligence_, mana_);
		defense_position = false;
		xp = 0;
		next_lvl_xp = 100;
		level = 1;
	}
	
	public void setDefensePosition(boolean defense_position_) {
		this.defense_position = defense_position_;
	}
	
	public void levelUp() {
		this.level += 1;
		this.characteristics.setMax_hp((int)Math.ceil(this.characteristics.getMax_hp() * 1.1));
		this.setHp(getHp() + (int)Math.ceil(this.characteristics.getMax_hp() * 0.1));
		this.characteristics.setMax_stamina((int)Math.ceil(this.characteristics.getMax_stamina() * 1.1));
		this.setStamina(getStamina() + (int)Math.ceil(this.characteristics.getMax_stamina() * 0.1));
		System.out.println("Player lvl : "  + level + " - max hp : " + characteristics.getMax_hp() + " - max stamina " + characteristics.getMax_stamina());
	}
	
	public boolean earn_xp(int xp_earned) {
		boolean earn_lvl = false;
		if (xp + xp_earned >= next_lvl_xp) {
			xp = (xp + xp_earned) - next_lvl_xp;
			levelUp();
			earn_lvl = true;
		}
		else {
			xp += xp_earned;
		}
		
		return earn_lvl;
	}
	
	public int getXp() {
		return xp;
	}
	
	public int getLevel() {
		return level;
	}
	
	@Override
	public String toString() {
		return name + " | " + "HP: " + getHp() + "/" + getCharacteristics().getMax_hp() + " | Stamina: " + getStamina() + "/" + getCharacteristics().getMax_stamina();
	}
	
	public abstract boolean actionPossible(Attack attackType);
	public abstract boolean actionPossible(Game_action action);
}
