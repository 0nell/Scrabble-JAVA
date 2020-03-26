import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Screen;




public class UI {
    static  Rectangle2D screenBounds = Screen.getPrimary().getBounds(); // used to make the program proportional to the users screen

    Square[] frameHolder = new Square[7];
    Label score1;
    Label score2;
    TextField textBox;
    TilePane framePane;
    Frame currentFrame;
    Label instructionLabel;
    String[] inputText;
    TilePane boardPane;
    Board board;
    Pool pool;
    Label turnLabel;
    TilePane scores;
    Player[] players = new Player[2];
    int turn = 0;
    int pass = 0;
    int lastScore = 0; //score of last word placed
    int player0negative = 0, player1negative = 0;
    int challenge = 0;


    UI(){
        pool = new Pool();
        board = new Board();
        textBox = new TextField();
        players[0] = new Player();
        players[1] = new Player();

        setInstructionLabel();
        setTurnLabel();
        setFramePane();
        setScores();
        setBoardPane();

        textBox.setOnAction(e->{
            turn += parseInput(textBox.getText());
            if(turn >1){
                turnLabel.setText("Enter command " + players[(turn)%2].getName());
            }

            setCurrentFrame(players[turn%2].getFrame());
            textBox.clear();
            if(checkWin()){
                instructionLabel.setText("Game Over");
                players[0].addScore(player0negative);
                players[1].addScore(player1negative);
            }
            pool.drawTiles(86);
        });


    }

    public void setInstructionLabel() {
        instructionLabel = new Label("'HELP' to get instructions");
        instructionLabel.setWrapText(true);
        instructionLabel.setMaxWidth(UI.screenBounds.getWidth()/10);
        instructionLabel.setMinHeight(UI.screenBounds.getHeight()* 2/4);
        instructionLabel.setAlignment(Pos.CENTER);
        instructionLabel.setStyle("-fx-background-color: rgba(5, 37, 4, .4)");
    }

    public void setTurnLabel() {
        turnLabel = new Label("Player 1 insert name: ");
        turnLabel.setAlignment(Pos.CENTER);
        turnLabel.setPrefSize(UI.screenBounds.getHeight()/5.2, UI.screenBounds.getHeight()/22.5);
        turnLabel.setStyle("-fx-background-color: rgba(5, 37, 4, .6)");
    }

    public void setFramePane() {
        framePane  = new TilePane();
        for(int j = 0; j < 7; j++) {
            frameHolder[j] = new Square();
        }
        for(int j = 0; j < 7; j++) {
            framePane.getChildren().add(frameHolder[j]);
            framePane.setTileAlignment(Pos.TOP_LEFT);
        }
    }

    public TilePane getScores() {
        return scores;
    }

    public void setScores() {
        scores = new TilePane();
        scores.setMaxWidth(225);
        scores.setHgap(25);
        scores.setMinHeight(50);

        scores.setPrefWidth(300);
        score1 = new Label();
        score2 = new Label();


        score1.setAlignment(Pos.CENTER);
        score2.setAlignment(Pos.CENTER);
        String scoreFont = "-fx-font-size: " + UI.screenBounds.getHeight()/72 + "px;";
        score1.setStyle(scoreFont);
        score2.setStyle(scoreFont);

        score1.setPrefSize(100,50);
        score2.setPrefSize(100,50);


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

    public void setCurrentFrame(Frame currentFrame) {

        this.currentFrame = currentFrame;
        for(int i = 0; i < 7; i++){
            frameHolder[i].setText(" ");
            frameHolder[i].setStyle("-fx-background-color: rgb(216, 226, 238); -fx-text-fill: black; -fx-border-color: black;");
        }
        for(int j = 0; j < currentFrame.size(); j++) {
            frameHolder[j].setTile(currentFrame.getTiles().get(j));
        }

    }

    public TilePane getBoardPane() {
        return boardPane;
    }

    void setBoardPane(){
        boardPane = new TilePane();
        boardPane.setPrefSize(UI.screenBounds.getWidth()/2.4, 0);
        boardPane.setPrefColumns(16);
        boardPane.setPrefRows(16);
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                boardPane.getChildren().add(board.squares[i][j]);
                boardPane.setTileAlignment(Pos.TOP_LEFT);
            }
            boardPane.getChildren().add(new Label(Integer.toString(i+1))); //Adding the Y co-ordinates
        }
        for (int i = 0; i < 15; i++) {
            boardPane.getChildren().add(new Label(" "+ (char)(i+65))); //Adding the x co-ordinates
        }

    }

    GridPane printGame(){
        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.add(getScores(), 0, 0);
        gridPane.add(getBoardPane(), 0,1);
        gridPane.add(getFramePane(), 0,2);

        gridPane.add(instructionLabel, 1,1);
        gridPane.add(turnLabel, 1, 2);
        gridPane.add(textBox, 1,3);

        gridPane.setStyle("-fx-background-color: rgb(5, 37, 4, 0.658);");
        return gridPane;
    }

    int parseInput(String command){
        command = command.toUpperCase();
        //If the command is HELp it overrides other commands
        if(command.equals("HELP")){
            instructionLabel.setText("- To place words enter: <x-coordinate> <y-coordinate> <across/down> <word>\n"
                    + "- To Exchange letters, enter: EXCHANGE <letters to exchange>\n"
                    + "- To pass turn, enter: PASS\n"
                    + "- To challenge previous word, enter CHALLENGE\n"
                    + "- To quit game, enter QUIT");
            return 0;
        }
        else{

        if(turn == 0){
            turnLabel.setText("Player 2 insert name: ");
            players[0].setName(command);
            players[0].getFrame().refill(pool);
            score1.setText(players[0].getName() + "\n"+ players[0].getScore());

            return 1;
        }
        else if(turn == 1){
            turnLabel.setText("Enter command " + players[0].getName());
            players[1].setName(command);
            players[1].getFrame().refill(pool);
            setCurrentFrame(players[turn].getFrame());
            score2.setText(players[1].getName() + "\n"+ players[1].getScore());
            return 1;
        }
        else if(challenge == 1){
            instructionLabel.setText("'HELP' to get instructions");
            if(command.equals("NO") && players[turn % 2].equals(players[1])) {
                player0negative -= lastScore;
                challenge = 0;
            } else if(command.equals("NO") && players[turn % 2].equals(players[0])) {
                player1negative -= lastScore;
                challenge = 0;
            } else if(command.equals("YES")){
                challenge = 0;
            }
            else{
                instructionLabel.setText("Incorrect Input\n Does the word exist? (YES / NO)");
                challenge = 1;
            }

            return 0;
        }
        else {

            inputText = command.split(" ", 0);
            int i = 0;

            switch(inputText[i]) {
                case "QUIT":
                    System.exit(0);
                case "PASS":
                    pass++;
                    return 1;
                case "EXCHANGE":
                    if(pool.size() < 7) {
                        instructionLabel.setText("Cannot exchange\n" +
                                "There are less than 7 tiles left in the pool");
                        return 0;
                    } else {
                        String letters = inputText[++i];
                        if(!players[turn % 2].getFrame().isAvailable(letters).contains("t")) {
                            instructionLabel.setText("Cannot exchange\n" +
                                    "Your frame does not contain the tiles you entered\n" + "(Use _ to represent a blank tile for exchange");
                            return 0;
                        } else {
                            players[turn % 2].getFrame().remove(letters);
                            players[turn % 2].getFrame().refill(pool);
                            pass = 0;
                            return 1;
                        }
                    }
                case "CHALLENGE":
                    instructionLabel.setText("Previous word has been challenged" +
                            "\nLast Score was: " + lastScore + "\nDoes the word exist? (YES / NO)");

                    challenge = 1;

                    return 0;
                default:
                    try {
                        String gridRef = inputText[i++];
                        char xGridRef = gridRef.charAt(0);
                        String yCoord = gridRef.substring(1);
                        int y = Integer.parseInt(yCoord) - 1;
                        String direction = inputText[i++];
                        String word = inputText[i];

                        players[turn % 2].addScore(lastScore = board.placeWord(players[turn % 2], word, xGridRef, y, direction));


                        score1.setText(players[0].getName() + "\n" + players[0].getScore());
                        score2.setText(players[1].getName() + "\n" + players[1].getScore());
                        players[turn % 2].getFrame().refill(pool);
                        pass = 0;
                        return 1;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    return 0;
                }
            }
        }
    }
}
