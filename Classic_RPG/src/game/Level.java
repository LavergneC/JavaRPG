package game;
import java.util.ArrayList;

public class Level {
	private String name;
	private int number;
	private ArrayList<Wave> waves;
	
	Level(String name_, int number_, ArrayList<Wave> list_waves){
		name = name_;
		number = number_;
		waves = list_waves;
	}
}
