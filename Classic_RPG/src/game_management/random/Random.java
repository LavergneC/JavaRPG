package game_management.random;

import game_management.Interfaces.GUI;

public class Random {

	public static TestOutput test1D100(int sucess) {
		int dice = (int) (Math.random() * 100);

		if (dice <= 5) {
			return TestOutput.CRITICAL_SUCESS;
		} else if (dice >= 95) {
			return TestOutput.CRITICAL_FAILURE;
		} else if (dice <= sucess) {
			return TestOutput.SUCESS;
		} else {
			return TestOutput.FAILURE;
		}
	}

	public static int EffectiveDamage(int success, int dmgs) {

		switch (Random.test1D100(success)) {

		case CRITICAL_FAILURE:
			dmgs = 0;
			GUI.edit_message("NOOOOOB'S SHIT");
			break;

		case FAILURE:
			dmgs = dmgs / 2;
			GUI.edit_message("NOOOOOB");
			break;

		case SUCESS:
			GUI.edit_message("NOT BAD");
			break;

		case CRITICAL_SUCESS:
			dmgs = dmgs * 2;
			GUI.edit_message("PGM!!!!");
			break;

		}
		return dmgs;

	}
}
