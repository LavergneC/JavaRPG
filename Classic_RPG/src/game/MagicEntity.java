package game;

public abstract class MagicEntity extends Entity{
	private int mana;
	
	protected MagicEntity(int hp_, int stamina_, String name, int mana_, int intelligence_, int agility_, int strength_) {
		super(hp_, stamina_,  name, agility_, strength_);
		this.mana = mana_;
	}

	protected int getMana() {
		return mana;
	}

	protected void setMana(int mana) {
		this.mana = mana;
	}
}
