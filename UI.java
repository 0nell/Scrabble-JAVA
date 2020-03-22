import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;

import java.util.Objects;

public class UI {
    public Board getBoard() {
        return board;
    }

    Board board;


    UI(){
        board = new Board();
    }

    TilePane printBoard(){
        TilePane tilePane = new TilePane();
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                tilePane.getChildren().add(board.squares[i][j]);
                tilePane.setTileAlignment(Pos.TOP_LEFT);
            }
        }
        return tilePane;
    }

    void parseInput(String input){

    }
}
