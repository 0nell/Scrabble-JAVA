public class Square{
    private boolean empty;
    private Tile tile;
    String value;

    Square() {
        empty = true;
        value = "  ";
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