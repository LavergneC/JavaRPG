package game;

public abstract class Player extends Entity{
	
	static Player actual_player;
	boolean defense_position;
	
	public Player(int hp_, int stamina_, String name_, int agility_, int strength_, int intelligence_) {
		super(hp_, stamina_, name_, agility_, strength_, intelligence_);
		defense_position = false;
	}
	
	public Player(int hp_, int stamina_, String name_, int mana_, int agility_, int strength_, int intelligence_) {
		super(hp_, stamina_, name_, agility_, strength_, intelligence_, mana_);
		defense_position = false;
	}
	
	protected abstract void specialHit(Entity target);
	
	void setDefensePosition(boolean defense_position_) {
		this.defense_position = defense_position_;
	}
	
	@Override
	public String toString() {
		return name + " | " + "HP: " + getHp() + "/" + characteristics.getMax_hp() + " | Stamina: " + getStamina() + "/" + characteristics.getMax_stamina();
	}

}
