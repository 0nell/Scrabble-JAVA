import java.util.Scanner;

//allow 2 players to play
//include scoring
//do challenges manually

public class Scrabble {
	
	
	public static void main(String[] args) {
		Pool pool = new Pool();
		Player player1 = new Player();
		Player player2 = new Player();
		System.out.println("Player 1 insert name: ");
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		player1.setName(s);
		System.out.println("Player 2 insert name: ");
		s = scan.next();
		player2.setName(s);
		System.out.print("Player 1 is " + player1.getName() + "\n" + "Player 2 is " + player2.getName());
	}
	
}
