public class Board{
    Square squares[][];
    Board(){
        squares = new Square[15][15];
        
        for(int i = 0; i < 15; i ++){
            for(int j = 0; j < 15; j++){
                squares[i][j] = new Square();
            }
        }
        squares[7][7].setValue("**");
    }

    void printBoard(){
        for(int i = 0; i < 15; i ++){
            for(int j = 0; j < 15; j++){
                System.out.print("|"+ squares[i][j].getValue()+"|");
            }
            System.out.print("\n");
        }
    }
        


    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

    }
}