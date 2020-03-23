import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;



public class UI {
    Scene game;
    TextField textBox;

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    Frame frame;

    public Label getLabel() {
        return label;
    }

    Label label;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    String text;

    public Board getBoard() {
        return board;
    }

    Board board;


    UI(){
        board = new Board();
        textBox = new TextField();
        textBox.setOnAction(e->setText(textBox.getText()));
        label = new Label("Enter command");

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
        gridPane.add(printBoard(), 0,0);
        gridPane.add(textBox, 1,4);
        gridPane.add(label, 1,0);
        gridPane.add(printFrame(), 0,4);
        return gridPane;
    }
    TilePane printFrame(){
        TilePane tilePane = new TilePane();
        for(int j = 0; j < 7; j++) {
            tilePane.getChildren().add(getFrame().getTiles().get(j));
            tilePane.setTileAlignment(Pos.TOP_LEFT);
        }
        return tilePane;
    }

    void parseInput(String input){

    }
}
