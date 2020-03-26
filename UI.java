import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Screen;
import java.util.ArrayList;




public class UI {
    static  Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    int STEP = 0;
    Square[] frames = new Square[7];
    Label score1;
    Label score2;
    TextField textBox;
    Player currentPlayer;
    TilePane framePane;
    Frame frame;
    Label label;
    String[] text;
    Board board;
    Pool pool;
    Label turnLabel;
    TilePane scores;
    Player[] players = new Player[2];
    int turn = 0;
    int pass = 0;
    int lastScore = 0; //score of last word placed
    int player0negative = 0, player1negative = 0;
    String challenge = null;

    UI(){

        board = new Board();
        textBox = new TextField();
        players[0] = new Player();
        players[1] = new Player();

        textBox.setOnAction(e->{
            turn += parseInput(textBox.getText());
            if(STEP >1){
                turnLabel.setText("Enter command " + players[(turn)%2].getName());
            }
            STEP++;
            setFrame(players[turn%2].getFrame());
            textBox.clear();
            if(checkWin()){
                label.setText("Game Over");
                players[0].addScore(player0negative);
                players[1].addScore(player1negative);
                int scoreFromPlayerFrame1 = 0;
                int scoreFromPlayerFrame2 = 0;

                ArrayList<Tile> temp = players[1].getFrame().getTiles();
                for(int i = 0; i < players[1].getFrame().size(); i++)
                {
                    scoreFromPlayerFrame1 += temp.get(i).getValue();
                }


                temp = players[0].getFrame().getTiles();
                for(int i = 0; i < players[0].getFrame().size(); i++)
                {
                    scoreFromPlayerFrame2 += temp.get(i).getValue();
                }


                if(players[0].getFrame().isEmpty())
                {
                    scoreFromPlayerFrame1 *= 2;
                    players[0].addScore(scoreFromPlayerFrame1);
                }else if(players[1].getFrame().isEmpty())
                {
                    scoreFromPlayerFrame2 *= 2;
                    players[1].addScore(scoreFromPlayerFrame2);

                }else
                {
                    scoreFromPlayerFrame1 *= -1;
                    scoreFromPlayerFrame2 *= -1;
                    players[0].addScore(scoreFromPlayerFrame1);
                    players[1].addScore(scoreFromPlayerFrame2);
                }

                if(players[0].getScore() > players[1].getScore())
                {
                    label.setText(players[0].getName() + " WINS!!!");
                }else if(players[1].getScore() > players[0].getScore())
                {
                    label.setText(players[1].getName() + " WINS!!!");
                }else
                {
                    label.setText(" ITS A TIE!!!");
                }
            }
        });
        label = new Label("'HELP' to get instructions");
        label.setWrapText(true);
        label.setMaxWidth(200);
        label.setAlignment(Pos.CENTER);

        turnLabel = new Label("Player 1 insert name: ");
        turnLabel.setAlignment(Pos.CENTER);
        turnLabel.setMaxSize(200, 50);
        framePane  = new TilePane();
        currentPlayer = new Player();
        pool = new Pool();


        for(int j = 0; j < 7; j++) {
            frames[j] = new Square();
        }
        for(int j = 0; j < 7; j++) {
            framePane.getChildren().add(frames[j]);
            framePane.setTileAlignment(Pos.TOP_LEFT);
        }

        setScores();



    }

    public TilePane getScores() {
        return scores;
    }

    public void setScores() {
        scores = new TilePane();
        scores.setMaxWidth(200);
        scores.setHgap(25);
        score1 = new Label();

        score2 = new Label();

        scores.getChildren().add(score1);
        scores.getChildren().add(score2);
    }

    boolean checkWin(){
        if(pass >= 6){
            return true;
        }
        else return (pool.isEmpty() && (players[0].getFrame().isEmpty() || players[1].getFrame().isEmpty()));
    }

    public TilePane getFramePane() {
        return framePane;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
        for(int i = 0; i < 7; i++){
            frames[i].setText(" ");
        }
        for(int j = 0; j < frame.size(); j++) {
            frames[j].setTile(frame.getTiles().get(j));
        }
    }

    TilePane printBoard(){
        TilePane tilePane = new TilePane();
        tilePane.setPrefSize(800, 0);
        tilePane.setPrefColumns(16);
        tilePane.setPrefRows(16);
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                tilePane.getChildren().add(board.squares[i][j]);
                tilePane.setTileAlignment(Pos.TOP_LEFT);
            }
            tilePane.getChildren().add(new Label(Integer.toString(i+1)));
        }
        for (int i = 0; i < 15; i++) {
            tilePane.getChildren().add(new Label(" "+ (char)(i+65)));
        }
        return tilePane;
    }

    GridPane printGame(){
        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.add(printBoard(), 0,0);
        gridPane.add(textBox, 2,4);
        gridPane.add(label, 2,1);
        gridPane.add(turnLabel, 2, 2);
        gridPane.add(getFramePane(), 0,4);
        gridPane.add(getScores(), 2, 0);
        return gridPane;
    }

    int parseInput(String command){
        if(STEP == 0){
            turnLabel.setText("Player 2 insert name: ");
            players[0].setName(command);
            players[0].getFrame().refill(pool);
            score1.setText(players[0].getName() + "\n"+ players[0].getScore());

            return 0;
        }
        else if(STEP == 1){
            turnLabel.setText("Enter command " + players[turn%2].getName());
            players[1].setName(command);
            players[1].getFrame().refill(pool);
            setFrame(players[turn].getFrame());
            score2.setText(players[1].getName() + "\n"+ players[1].getScore());
            return 0;
        }
        else {

            text = command.split(" ", 0);
            int i = 0;

            switch(text[i]) {
                case "QUIT":
                    System.exit(0);
                case "PASS":
                    pass++;
                    return 1;
                case "HELP":
                    label.setText("- To place words enter: <x-coordinate><y-coordinate> <across/down> <word>\n to use a blank tile simply type the word you want\n" +
                            "the blank tile will automatically be placed instead of the tile you don't have\n"
                    		+ "- To Exchange letters, enter: EXCHANGE <letters to exchange>\n with blank tiles represented by an underscore\n"
                    		+ "- To pass turn, enter: PASS\n"
                    		+ "- To challenge previous word, enter CHALLENGE\n"
                    		+ "- To quit game, enter QUIT");
                    return 0;
                case "EXCHANGE":
                    if(pool.size() < 7)
                {
                    label.setText("Cannot exchange\n" +
                            "There are less than 7 tiles left in the pool");
                    return 0;
                }else {
                        String letters = text[++i];
                        if(!players[turn % 2].getFrame().isAvailable(letters).contains("t"))
                        {
                            label.setText("Cannot exchange\n" +
                                    "Your frame does not contain the tiles you entered\n" + "(Use _ to represent a blank tile for exchange");
                            return 0;
                        }else
                        {
                            players[turn % 2].getFrame().remove(letters);
                            players[turn % 2].getFrame().refill(pool);
                            pass = 0;
                            return 1;
                        }
                    }
                case "CHALLENGE":
                    label.setText("Previous word has been challenged" +
                    		"\nLast Score was: " + lastScore + "\nIs it a correct word? (YES / NO)");
                    
                    challenge = "YES";
                    if (challenge.equals("YES") && players[turn%2] == players[1]) {
						player0negative -= lastScore;
					}
                    else if (challenge.equals("YES") && players[turn%2] == players[0]) {
						player1negative -= lastScore;
					}
                    else {
                    	label.setText("Challenge failed");
                    }
                    return 0;
                default:
                    try{
                        String gridRef = text[i++];
                        char xGridRef = gridRef.charAt(0);
                        String yCoord = gridRef.substring(1);
                        int y = Integer.parseInt(yCoord) - 1;
                    String direction = text[i++];
                    String word = text[i];

                    players[turn%2].addScore(lastScore = board.placeWord(players[turn%2], word, xGridRef, y, direction));


                    score1.setText(players[0].getName() + "\n"+ players[0].getScore());
                    score2.setText(players[1].getName() + "\n"+ players[1].getScore());
                    players[turn%2].getFrame().refill(pool);
                    pass = 0;
                    return 1;
                    } catch (Exception e){
                        System.out.println(e);
                    }
                    return  0;
            }
        }
    }
}
