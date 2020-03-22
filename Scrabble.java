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
		System.out.print("\n\n****************\n   SCRABBLE   \n****************");
		
		//fill both players frames
		player1.getFrame().refill(pool);
		player2.getFrame().refill(pool);
		
		Player p;
		String word, direction, command, letters;
		int loop_breaker = 0;
		int loop_breaker2 = 0;
		int x, y;
		
		//game ends when pool is empty & one player has no tiles left || no possible plays
		outerloop:
		while (!pool.isEmpty()&&(!player1.getFrame().isEmpty()||!player2.getFrame().isEmpty())) {
			System.out.println("\nGAME ON");
			Board board = new Board();
			board.printBoard();
			loop_breaker = 0;
			loop_breaker2 = 0;
			
			
			p = player1;
			innerloop:
			while (loop_breaker == 0) {
				
				System.out.println(player1.getFrame());
				System.out.println(player1.getName() + " insert command: ");
				command = scan.next();
				if (!command.equals("QUIT") && !command.equals("PASS") && !command.equals("HELP")) {
					System.out.println(player1.getName() + " insert x coordinate: ");
					scan.nextLine();
					x = scan.nextInt();
					System.out.println(player1.getName() + " insert y coordinate: ");
					y = scan.nextInt();
					System.out.println(player1.getName() + " insert direction: ");
					direction = scan.next();
					System.out.println(player1.getName() + " insert word: ");
					word = scan.next();
					
					System.out.println(x + word + direction + y);
					board.placeWord(p, word, x, y, direction);
					board.printBoard();
					loop_breaker = 1;
				}
				
				if (command.equals("QUIT")) {
					break outerloop;
				}

				if (command.equals("HELP")) {
					System.out.println("Display help message here");
					
				}
				
				if (command.equals("PASS")) {
					break innerloop;
				}
				
				if(command.equals("EXCHANGE")) {
					System.out.println(player1.getName() + " what letters do you want exchanged? ");
					letters = scan.next();
					player1.getFrame().remove(letters);
					player1.getFrame().refill(pool);
					loop_breaker = 1;
				}
				
			}
			
			
			p= player2;
			innerloop:
			while(loop_breaker2 == 0) {
				System.out.println(player2.getFrame());
				System.out.println(player2.getName() + " insert command: ");
				command = scan.next();
				if (!command.equals("QUIT") && !command.equals("PASS") && !command.equals("HELP")) {
					System.out.println(player2.getName() + " insert x coordinate: ");
					scan.nextLine();
					x = scan.nextInt();
					System.out.println(player2.getName() + " insert y coordinate: ");
					y = scan.nextInt();
					System.out.println(player2.getName() + " insert direction: ");
					direction = scan.next();
					System.out.println(player2.getName() + " insert word: ");
					word = scan.next();
					
					System.out.println(x + word + direction + y);
					board.placeWord(p, word, x, y, direction);
					board.printBoard();
					loop_breaker2 = 1;
				}
				
				if (command.equals("QUIT")) {
					break outerloop;
				}

				if (command.equals("HELP")) {
					System.out.println("Display help message here");
					
				}
				
				if (command.equals("PASS")) {
					break innerloop;
				}
				
				if(command.equals("EXCHANGE")) {
					System.out.println(player2.getName() + " what letters do you want exchanged? ");
					letters = scan.next();
					player2.getFrame().remove(letters);
					player2.getFrame().refill(pool);
					loop_breaker2 = 1;
				}
			}
			
			
			
		}
		
		scan.close();
	}
	
}
