package game;

public class Random {
	static TestOutput test1D100(int sucess) {
		int dice = (int)(Math.random() * 100);
		
		if(dice <= 5) {
			return TestOutput.CRITICAL_SUCESS;
		}
		else if(dice >= 95) {
			return TestOutput.CRITICAL_FAILURE;
		}
		else if (dice <= sucess) {
			return TestOutput.SUCESS;
		}
		else {
			return TestOutput.FAILURE;
		}
	}
}
