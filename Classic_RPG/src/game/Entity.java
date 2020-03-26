package game;

public abstract class Entity {
	protected int level;
	protected int hp;
	protected int stamina;
	protected String name;
	protected Characteristics characteristics;
	
	protected ManaPool manaPool;
	
	protected abstract void receiveAttack(int dmgIncoming);
	
	protected Entity(int hp_, int stamina_, String name_, int agility_, int strength_, int intelligence_)
	{
		this.characteristics = new Characteristics(agility_, strength_, intelligence_,  hp_, stamina_);
		this.hp = hp_;
		
		this.stamina = stamina_;
		this.name = name_;
		this.level = 0;
	}

	protected Entity(int hp_, int stamina_, String name_, int agility_, int strength_, int intelligence_, int mana_)
	{
		this.characteristics = new MagicianCharacteristiques(agility_, strength_, intelligence_, hp_, stamina_, mana_);
		this.hp = hp_;
		this.stamina = stamina_;
		this.name = name_;
		this.level = 0;
		this.manaPool = new ManaPool(mana_);
	}
	
	protected void levelUp() {
		this.level += 1;
	}

	protected void setMana(int manaValue) {
		if(manaPool != null)
			manaPool.setMana(manaValue);
		else
			System.out.println("This entity have no mana pool !!!!");
	}
	
	protected void basicHit(Entity target)
	{
		int dmgs = characteristics.getStrength() * 2;
		stamina -= 50;
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
