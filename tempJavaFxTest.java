
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

public class tempJavaFxTest extends Application {
    Stage window;
    Scene game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        UI ui = new UI();
        window = primaryStage;
        game = new Scene(ui.printBoard(), 900, 900);
        runScrabble(ui.getBoard());
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

        Player p;
        String word, direction;
        int x, y;

        //game ends when pool is empty & one player has no tiles left || no possible plays
        while (!pool.isEmpty()&&(!player1.getFrame().isEmpty()||!player2.getFrame().isEmpty())) {
            System.out.println("\nGAME ON");
            board.printBoard();

            p = player1;
            System.out.println(player1.getFrame());
            System.out.println(player1.getName() + " insert word: ");
            word = scan.next();
            System.out.println(player1.getName() + " insert x coordinate: ");
            scan.nextLine();
            x = scan.nextInt();
            System.out.println(player1.getName() + " insert y coordinate: ");
            y = scan.nextInt();
            System.out.println(player1.getName() + " insert direction: ");
            direction = scan.next();
            System.out.println(x + word + direction + y);
            Player finalP = p;
            String finalWord = word;
            int finalX = x;
            int finalY = y;
            String finalDirection = direction;
            Platform.runLater(() -> {
                        board.placeWord(finalP, finalWord, finalX, finalY, finalDirection);
                    });
            board.printBoard();

            p= player2;
            System.out.println(player2.getFrame());
            System.out.println(player2.getName() + " insert word: ");
            word = scan.next();
            System.out.println(player2.getName() + " insert x coordinate: ");
            x = scan.nextInt();
            System.out.println(player2.getName() + " insert y coordinate: ");
            y = scan.nextInt();
            System.out.println(player2.getName() + " insert direction: ");
            direction = scan.next();
            Player finalP1 = p;
            String finalWord1 = word;
            int finalX1 = x;
            int finalY1 = y;
            String finalDirection1 = direction;
            Platform.runLater(() -> {
                        board.placeWord(finalP1, finalWord1, finalX1, finalY1, finalDirection1);
                    });
            board.printBoard();
            break;

        }

        scan.close();
    }
}