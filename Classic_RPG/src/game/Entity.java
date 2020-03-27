package game;

public abstract class Entity {
	protected int level;
	protected int hp;
	protected int stamina;
	protected String name;
	protected Characteristics characteristics;

	protected abstract void receiveAttack(int dmgIncoming);
	public abstract String toString();

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
	}

	protected void levelUp() {
		this.level += 1;
	}

	protected void setMana(int manaValue) { //DownCast
		if(characteristics instanceof MagicianCharacteristiques) {
			((MagicianCharacteristiques)characteristics).setMana(manaValue);
		}
		else {
			System.out.println("This entity have no mana pool !!!!");
		}
	}

	protected int getMana() {
		if(characteristics instanceof MagicianCharacteristiques) {
			return ((MagicianCharacteristiques)characteristics).getMana();
		}
		else {
			System.out.println("This entity have no mana pool !!!!");
			return 77;
		}
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

	void rest()
	{
		if(stamina + (int)Math.ceil(stamina/10) > characteristics.getMax_stamina())
			stamina = characteristics.getMax_stamina();
		else
			stamina += (int)Math.ceil(stamina/10); // TODO could be changed or implement in daughter class
		if(hp + (int)Math.ceil(hp/20) > characteristics.getMax_hp())
			hp = characteristics.getMax_hp();
		else
			hp += (int)Math.ceil(hp/20);
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
