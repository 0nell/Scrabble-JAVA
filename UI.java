import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;



public class UI {
    Scene game;
    TextField textBox;
    Player currentPlayer;
    TilePane framePane;
    Frame frame;
    Label label;
    String[] text;
    Board board;
    Pool tempPool;

    UI(){
        board = new Board();
        textBox = new TextField();
        textBox.setOnAction(e->parseInput(textBox.getText()));
        label = new Label("Enter text");
        framePane  = new TilePane();
        currentPlayer = new Player();
        tempPool = new Pool();

        for(int j = 0; j < 7; j++) {
            framePane.getChildren().add(new Tile('_'));
            framePane.setTileAlignment(Pos.TOP_LEFT);
        }


    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public TilePane getFramePane() {
        return framePane;
    }

    public Frame getFrame() {

        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
        framePane.getChildren().removeAll(framePane.getChildren());
        for(int j = 0; j < frame.size(); j++) {
            framePane.getChildren().add(getFrame().getTiles().get(j));
        }
    }

    public Label getLabel() {
        return label;
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public Board getBoard() {
        return board;
    }

    TilePane printBoard(){
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(15);
        tilePane.setPrefRows(15);
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                tilePane.getChildren().add(board.squares[i][j]);
                tilePane.setTileAlignment(Pos.TOP_LEFT);
            }
        }
        return tilePane;
    }

    GridPane printGame(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(15);
        gridPane.add(printBoard(), 0,0);
        gridPane.add(textBox, 1,4);
        gridPane.add(label, 1,0);
        gridPane.add(getFramePane(), 0,4);
        return gridPane;
    }
    TilePane printFrame(){
        return framePane;
    }

    void parseInput(String command){
        text = command.split(" ", 0);
        int i = 0;
        if (!text[i].equals("QUIT") && !text[i].equals("PASS") && !text[i].equals("HELP") && !text[i].equals("EXCHANGE")) {
            i++;
            int x = Integer.parseInt(text[i++]);
            int y = Integer.parseInt(text[i++]);
            String direction = text[i++];
            String word = text[i++];


            board.placeWord(currentPlayer, word, x, y, direction);
            board.printBoard();

        }

        if (text[i].equals("QUIT")) {
            System.exit(0);
        }

        if (text[i].equals("HELP")) {
            label.setText("Display help message here");

        }

        if (text[i].equals("PASS")) {
            return;
        }

        if(text[i].equals("EXCHANGE")) {
            String letters =  text[i++];
            currentPlayer.getFrame().remove(letters);
            currentPlayer.getFrame().refill(tempPool);
        }
    }
}
