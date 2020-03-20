package game;

public class MagicianCharacteristiques extends Characteristics {
	private int manaMax;
	
	public MagicianCharacteristiques(int agi, int stren, int m_hp, int max_sta, int mana) {
		super(agi, stren, m_hp, max_sta);
		this.manaMax = mana;
	}

	public int getManaMax() {
		return manaMax;
	}

	public void setManaMax(int manaMax) {
		this.manaMax = manaMax;
	}
	
}
