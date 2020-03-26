package game;

public abstract class Player extends Entity{
	
	static Player actual_player;
	boolean defense_position;
	
	public Player(int hp_, int stamina_, String name_, int agility_, int strength_) {
		super(hp_, stamina_, name_, agility_, strength_);
	}
	
	public Player(int hp_, int stamina_, String name_, int mana_, int agility_, int strength_) {
		super(hp_, stamina_, name_, agility_, strength_, mana_);
	}
	
	protected abstract void specialHit(Entity target);
	
	void setDefensePosition(boolean defense_position_) {
		this.defense_position = defense_position_;
	}
}
