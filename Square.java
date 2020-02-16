public class Square{
    String value;
    char tile;

    Square() {
        value = "  ";
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

}