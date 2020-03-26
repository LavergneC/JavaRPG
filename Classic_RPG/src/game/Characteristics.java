package game;

public class Characteristics {
	private int agility;
	private int strength;
	private int intelligence;
	private int max_hp;
	private int max_stamina;
	
	public Characteristics(int agility_, int strength_, int intelligence_, int m_hp, int m_stamina) {
		this.agility = agility_;
		this.strength = strength_;
		this.max_hp = m_hp;
		this.max_stamina = m_stamina;
		this.intelligence = intelligence_;
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
