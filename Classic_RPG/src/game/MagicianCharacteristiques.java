package game;

public class MagicianCharacteristiques extends Characteristics {
	private int manaMax;
	private int mana;
	
	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public MagicianCharacteristiques(int agility_, int strength_, int intelligence_, int m_hp, int m_stamina, int mana_) {
		super(agility_, strength_, intelligence_, m_hp, m_stamina);
		this.manaMax = mana_;
		this.mana = mana_;
	}

	public int getManaMax() {
		return manaMax;
	}

	public void setManaMax(int manaMax) {
		this.manaMax = manaMax;
	}
	
}
