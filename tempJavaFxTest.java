
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

public class tempJavaFxTest extends Application {
    Stage window;
    Scene game;
    UI ui;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ui = new UI();
        window = primaryStage;
        runScrabble(ui.getBoard());
        game = new Scene(ui.printGame(), 650, 550);
        window.setScene(game);
        window.setTitle("Scrabble");
        window.setResizable(false);
        window.show();
    }

    private void runScrabble(Board board) {
        Runnable task =  () -> {
            gameLoop(board);
        };
        Thread scrabbleThread = new Thread(task);
        scrabbleThread.setDaemon(true);
        scrabbleThread.start();
    }

    private void gameLoop(Board board) {
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
        Platform.runLater(() -> ui.setFrame(player1.getFrame()));

        Player p;
		String word, direction, command, letters;
		int loop_breaker = 0;
		int loop_breaker2 = 0;
		int x, y;
		System.out.println("\nGAME ON");
        //game ends when pool is empty & one player has no tiles left || no possible plays
		
        while (!pool.isEmpty()&&(!player1.getFrame().isEmpty()||!player2.getFrame().isEmpty())) {
            
            board.printBoard();
            loop_breaker = 0;
			loop_breaker2 = 0;
			
			
            p = player1;
            innerloop:
            while(loop_breaker == 0) {
            	System.out.println(player1.getFrame());
				System.out.println(player1.getName() + " insert command: ");
				command = scan.next();
	
				if (!command.equals("QUIT") && !command.equals("PASS") && !command.equals("HELP") && !command.equals("EXCHANGE")) {
					
					x = Integer.parseInt(command);
					y = Integer.parseInt(scan.next());
					direction = scan.next();
					word = scan.next();
					
					board.placeWord(p, word, x, y, direction);
					Player finalP = p;
		            String finalWord = word;
		            int finalX = x;
		            int finalY = y;
		            String finalDirection = direction;
		            Platform.runLater(() -> {
		                        board.placeWord(finalP, finalWord, finalX, finalY, finalDirection);
		                    });
					board.printBoard();
					loop_breaker = 1;
				}
				
				if (command.equals("QUIT")) {
					System.exit(0);
				}

				if (command.equals("HELP")) {
					System.out.println("Display help message here");
					
				}
				
				if (command.equals("PASS")) {
					break innerloop;
				}
				
				if(command.equals("EXCHANGE")) {
					//System.out.println(player1.getName() + " what letters do you want exchanged? ");
					letters = scan.next();
					player1.getFrame().remove(letters);
					player1.getFrame().refill(pool);
					loop_breaker = 1;
					System.out.println(player1.getName()+ "'s New Frame: " + player1.getFrame());
				}
            }


            p= player2;
            innerloop:
            while(loop_breaker2 == 0) {
            	System.out.println(player2.getFrame());
				System.out.println(player2.getName() + " insert command: ");
				command = scan.next();
				if (!command.equals("QUIT") && !command.equals("PASS") && !command.equals("HELP") && !command.equals("EXCHANGE")) {
					//System.out.println(player2.getName() + " insert x coordinate: ");
					//scan.nextLine();
					x = Integer.parseInt(command);
					//System.out.println(player2.getName() + " insert y coordinate: ");
					y = Integer.parseInt(scan.next());
					//System.out.println(player2.getName() + " insert direction: ");
					direction = scan.next();
					//System.out.println(player2.getName() + " insert word: ");
					word = scan.next();
					
					//System.out.println(x + word + direction + y);
					board.placeWord(p, word, x, y, direction);
					Player finalP1 = p;
		            String finalWord1 = word;
		            int finalX1 = x;
		            int finalY1 = y;
		            String finalDirection1 = direction;
		            Platform.runLater(() -> {
		                        board.placeWord(finalP1, finalWord1, finalX1, finalY1, finalDirection1);
		                    });
					board.printBoard();
					loop_breaker2 = 1;
				}
				
				if (command.equals("QUIT")) {
					System.exit(0);
				}

				if (command.equals("HELP")) {
					System.out.println("Display help message here");
					
				}
				
				if (command.equals("PASS")) {
					break innerloop;
				}
				
				if(command.equals("EXCHANGE")) {
					//System.out.println(player2.getName() + " what letters do you want exchanged? ");
					letters = scan.next();
					player2.getFrame().remove(letters);
					player2.getFrame().refill(pool);
					System.out.println(player2.getName()+ "'s New Frame: " +player2.getFrame());
					loop_breaker2 = 1;
				}
            }

        }

        scan.close();
    }
}