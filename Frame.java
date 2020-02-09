import java.util.ArrayList;

public class Frame {
    ArrayList<Character> tiles;
    static int maxSize = 7;

    public Frame() {
        this.tiles = new ArrayList<Character>(maxSize);
    }

    /*
    *   Refills the frame as long as the pool is not empty
    */
    public void refillFrame() {
        int currentSize = this.numOfTiles();
        for (int i = 0; i < maxSize - currentSize && !Pool.isEmpty(); i++) {
            this.addTile(Pool.draw());
        }
    }

    public void emptyFrame() {
        tiles.clear();
    }

    public int numOfTiles() {
        return tiles.size();
    }

    public void addTile(char tile) {
        tiles.add(tile);
    }

    public void removeTile(char tile) {
        tiles.remove(tiles.indexOf(tile));
    }

    public boolean contains(char tile) {
        return tiles.contains(tile);
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public void displayFrame() {
        for (int i = 0; i < numOfTiles(); i++)
            System.out.print("|" + tiles.get(i) + "|");
        System.out.print("\n");
    }

}