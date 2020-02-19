public class Square{
    private boolean blank;
    private Tile tile;
    String value;

    Square() {
        blank = true;
        value = " ";
    }

    void setTile(Tile tile) {
        this.tile = tile;
        this.setEmpty(false);
    }

    Tile getTile() {
        return this.tile;
    }

    String getValue() {
        return this.value;
    }

    void setValue(String value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return blank;
    }

    public void setEmpty(boolean empty) {
        this.blank = empty;
    }

}