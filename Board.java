public class Board{
    Square squares[][];
    Board(){
        squares = new Square[15][15];
        
        //Sets Value of Each Square
        for(int i = 0; i < 15; i ++){
            for(int j = 0; j < 15; j++){
                squares[i][j] = new Square();
                if((i + j) % 7 == 0 && (i == 0 || i == 7 || i == 14)){
                    squares[i][j].setValue("3W");
                }
                else if(((i+j - 6) % 4 == 0 && (i == 5 || i == 9 || i == 13 || i == 1) 
                            && Math.abs(i - j)> 3 && Math.abs(i - j) < 9)
                            || (i == j && j == 9) || (i == j && j == 5)){
                    squares[i][j].setValue("3L");
                }
                else if(((i == 6 || i == 8) && (j == 6 || j == 8))
                        || (i!=j && (i == 3 || i == 11 || i == 14 || i == 0) && (j == 3 || j == 11 || j == 14 || j == 0))
                        || (i!=j &&(i == 8 || i == 6 || i == 12 || i == 2) && (j == 2 || j == 6 || j == 8 || j == 12))
                        || i == 7 && (j == 3 || j == 11)
                        || j == 7 && (i == 3 || i == 11)){
                    squares[i][j].setValue("2L");
                }
                else if(i == j || i+j ==14){
                    squares[i][j].setValue("2W");
                }
                
                
            }
        }
        squares[7][7].setValue("**");
    }

    void printBoard(){
        for(int i = 0; i < 15; i ++){
            for(int j = 0; j < 15; j++){
                if(squares[i][j].isEmpty())
                    System.out.print("|"+ squares[i][j].getValue()+"|");
                else
                    System.out.print("|"+ squares[i][j].getTile()+"| ");
            }
            System.out.print(" " + i + "\n");
        }
        
        //Prints Square Co-ordinates
        for(int i = 0; i < 10; i ++){
            System.out.print("  " + i+ " ");
        }
        for(int i = 10; i < 15; i ++){
            System.out.print(" " + i+ " ");
        }

    }
        


    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

    }
}