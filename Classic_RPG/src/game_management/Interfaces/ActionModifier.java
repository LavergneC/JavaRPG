package game_management.Interfaces;

import game_management.Action_Enums.*;

public class ActionModifier {
	private Measure mesure;
	private int value;

	public ActionModifier(Measure m, int v) {
		mesure = m;
		value = v;
	}

	public Measure getMesure() {
		return mesure;
	}

	public boolean isPositive() {
		return value >= 0;
	}

	public String getValueS() {
		return String.valueOf(value);
	}

	public int getValue(){
		return value;
	}
	public String getIconSource() {
		String ret = "Images/";
		switch(mesure) {
		case DAMAGES:
			ret += "sword";
			break;

		case HP:
			ret += "heart";
			break;

		case MANA:
			ret += "mana";
			break;

		case MANA_SHEILD:
			ret += "shield";
			break;

		case STAMINA:
			ret += "flash";
			break;
		}
		return ret + ".png";
	}
}
