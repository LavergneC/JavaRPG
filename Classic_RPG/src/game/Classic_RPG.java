package game;

public class Classic_RPG {

	public static void main(String[] args) {
		System.out.println("This is Classic RPG game; enjoy !");
		
		Wizard player1 = new Wizard("Merlin");
		Monster m1 = new Monster(200, 200, "Bébé dragon", 0, 10, 0);
		
		System.out.println("Player HP :" + player1.hp +  "\nMonster HP :" + m1.hp);
		
		player1.specialHit(m1);
		m1.basicHit(player1);
		
		System.out.println("\nPlayer HP :" + player1.hp +  "\nMonster HP :" + m1.hp);
		
		player1.setDefensePosition(true);
		m1.basicHit(player1);
		m1.basicHit(player1);
		m1.basicHit(player1);
		m1.basicHit(player1);
		
		System.out.println("\nPlayer HP :" + player1.hp +  "\nMonster HP :" + m1.hp);
	}
}
