package game;

public class ManaPool{
	private int manaMax;
	private int mana;
	
	protected ManaPool(int size) {
		this.mana = size;
		this.manaMax = size;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		if(this.mana + mana > manaMax)
			this.mana = manaMax;
		else
			this.mana = this.mana + mana;
	}

}
