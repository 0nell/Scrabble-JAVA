

/*
 * Team Name: El Cucharachas
 *
 * Students:
 * - Ahmed Jouda 	18329393
 * - Sean Mcdonnell 18391961
 * - Lleno Anya 	18357493
 *
 */
import javafx.scene.control.Label;

public class Square extends Label {
    private boolean empty;
    private Tile tile;
    String value;

    Square() {
        empty = true;
        value = "  ";
        this.setPrefSize(30,30);
        this.setStyle("-fx-font-size: 50; ");
        this.setStyle("-fx-border-color: black;");
    }

    void setTile(Tile tile) {
        this.tile = tile;
        this.setEmpty(false);
        if(tile == null){
            this.setText("");
        }
        else
            this.setText(String.valueOf(tile.getLetter()));
    }

    Tile getTile() {
        return this.tile;
    }

    String getValue() {
        return this.value;
    }

    void setValue(String value) {
        this.value = value;
        this.setText(value);
    }

    public boolean isEmpty() {
        return empty;
    }

    public String toString(){
        if(this.isEmpty()){
            return "|" + getValue() + "|";
        }
        else
            return "| " + tile.getLetter() + "|";
    }
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

}