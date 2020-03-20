package game;

public abstract class Entity {
	protected int hp;
	protected int stamina;
	protected String name;
	protected Characteristics characteristics;
	
	protected abstract void receiveAttack(int dmgIncoming);
	
	protected Entity(int hp_, int stamina_, String name_, int agi, int stren, int m_hp, int max_sta)
	{
		this.characteristics = new Characteristics(agi, stren, m_hp, max_sta);
		this.hp = hp_;
		this.stamina = stamina_;
		this.name = name_;		
	}
	
	protected void basicHit(Entity target)
	{
		int dmgs = target.characteristics.getStrength();
		this.attack(target, dmgs);
	}
	
	protected void attack(Entity target, int dmgs)
	{
		target.receiveAttack(dmgs);
	}

	protected int getHp() {
		return hp;
	}

	protected void setHp(int hp) {
		this.hp = hp;
	}

	protected int getStamina() {
		return stamina;
	}

	protected void setStamina(int stamina) {
		this.stamina = stamina;
	}

	protected String getName() {
		return name;
	}
	
}
