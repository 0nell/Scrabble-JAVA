public class Board{
    Square squares[][];
    Board(){
        squares = new Square[15][15];
        
        for(int i = 0; i < 15; i ++){
            for(int j = 0; j < 15; j++){
                squares[i][j] = new Square();
            }
        }
        squares[7][7].setValue("*");
    }

    void printBoard(){
        for(int i = 0; i < 15; i ++){
            for(int j = 0; j < 15; j++){
                if(squares[i][j].isEmpty())
                    System.out.print("|"+ squares[i][j].getValue()+"|");
                else
                    System.out.print("|"+ squares[i][j].getTile()+"|");
            }
            System.out.print("\n");
        }
    }
        


    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

    }
}