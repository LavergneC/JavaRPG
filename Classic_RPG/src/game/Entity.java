package game;

public abstract class Entity {
	protected int hp;
	protected int stamina;
	protected String name;
	protected Characteristics characteristics;
	
	protected abstract void receiveAttack(int dmgIncoming);
	
	protected Entity(int hp_, int stamina_, String name_)
	{
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
		int new_life = target.getHp() - dmgs;
		if (new_life < 0)
			target.setHp(0);
		else
			target.setHp(new_life);
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
