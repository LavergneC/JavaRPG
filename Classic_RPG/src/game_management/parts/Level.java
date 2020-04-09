package game_management.parts;
import java.util.ArrayList;

public class Level {
	private String name;
	private int number;
	private ArrayList<Wave> waves;
	
	public Level(String name_, int number_, ArrayList<Wave> list_waves){
		name = name_;
		number = number_;
		waves = list_waves;
	}
	
	public int getNumber() {
		return number;
	}

	public ArrayList<Wave> getWaves() {
		return waves;
	}

	public String getName() {
		return name;
	}
}
