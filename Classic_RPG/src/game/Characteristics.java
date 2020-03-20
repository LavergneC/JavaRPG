package game;

public class Characteristics {
	private int agility;
	private int strength;
	private int max_hp;
	private int max_stamina;
	
	public Characteristics(int agi, int stren, int m_hp, int max_sta) {
		this.agility = agi;
		this.strength = stren;
		this.max_hp = m_hp;
		this.max_stamina = max_sta;
	}
	
	public int getAgility() {
		return agility;
	}

	public int getStrength() {
		return strength;
	}

	public int getMax_hp() {
		return max_hp;
	}

	public int getMax_stamina() {
		return max_stamina;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setMax_hp(int max_hp) {
		this.max_hp = max_hp;
	}

	public void setMax_stamina(int max_stamina) {
		this.max_stamina = max_stamina;
	}
}
