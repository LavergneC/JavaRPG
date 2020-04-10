package entities;
import entities.characterisics.*;
import game_management.GUI;

public abstract class Entity {
	protected int level;
	private int hp;
	private int stamina;
	protected String name;
	protected Characteristics characteristics;

	protected void receiveAttack(int dmgIncoming) {
		hpChange(false, dmgIncoming);
	}

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
		if(getCharacteristics() instanceof MagicianCharacteristiques) {
			((MagicianCharacteristiques)getCharacteristics()).setMana(manaValue);
		}
		else {
			System.out.println("This entity have no mana pool !!!!");
		}
	}

	public int getMana() {
		if(getCharacteristics() instanceof MagicianCharacteristiques) {
			return ((MagicianCharacteristiques)getCharacteristics()).getMana();
		}
		else {
			System.out.println("This entity have no mana pool !!!!");
			return 77;
		}
	}

	public void basicHit(Entity target)
	{
		int dmgs = getCharacteristics().getStrength() * 2;
		
		GUI.edit_message(name + " basic attack on " + target.getName());
		staminaChange(false, 200);
		this.attack(target, dmgs);
	}

	protected void attack(Entity target, int dmgs)
	{
		target.receiveAttack(dmgs);
	}

	public void rest() // TODO could be changed or implement in daughter class
	{
		GUI.edit_message(getName() + " take some rest.");
		staminaChange(true, getCharacteristics().getMax_stamina() / 10);
		hpChange(true, (int)Math.ceil(getCharacteristics().getMax_hp()/20));
	}

	public int getHp() {
		return hp;
	}

	protected void setHp(int hp) {
		this.hp = hp;
	}

	public int getStamina() {
		return stamina;
	}

	protected void setStamina(int stamina) {
		// Careful usage ! Prefer staminaChage(), better most of the time
		this.stamina = stamina;
	}

	public String getName() {
		return name;
	}

	protected void staminaChange(boolean add, int value) {
		if(value <= 0) {
			return;
		}
		
		if(add) {
			if(value + stamina > getCharacteristics().getMax_stamina()) {
				GUI.edit_message(name + " recovred " + (getCharacteristics().getMax_stamina() - stamina) + " stamina");
				stamina = getCharacteristics().getMax_stamina();
			}
			else {
				stamina += value;
				GUI.edit_message(name + " recovered " + value + " stamina");
			}
		}
		else {
			if(stamina - value <= 0) {
				stamina = 0;
			}
			else {
				stamina -= value;
			}
		}
	}

	protected void hpChange(boolean add, int value) {
		if(value <= 0) {
			return;
		}

		if(add) {
			if(value + hp > getCharacteristics().getMax_hp()) {
				GUI.edit_message( name + " healed of " + (getCharacteristics().getMax_hp() - hp));
				hp = getCharacteristics().getMax_hp();
			}
			else {
				hp += value;
				GUI.edit_message(name + " healed of " + value);
			}
		}
		else {
			if(hp - value <= 0) {
				hp = 0;
				GUI.edit_message(name + " took " + value + " damage(s) and died");
			}
			else {
				hp -= value;
				GUI.edit_message(name + " took " + value + " damage(s)");
			}
		}
		GUI.edit_message("\n");
	}

	public Characteristics getCharacteristics() {
		return characteristics;
	}

}
