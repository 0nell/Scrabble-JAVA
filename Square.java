public class Square{
    String value;
    char tile;
    boolean empty;

    Square() {
        empty = true;
        value = " ";
    }
    
    void setTile(char tile){
        this.tile = tile;
    }
    char getTile(){
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

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

}