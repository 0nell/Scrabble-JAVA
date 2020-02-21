import java.util.ArrayList;

public class Board{
    Square squares[][];
    boolean connects = false;

    void resetBoard(){
        for(int i = 0; i < 15; i ++){
            for(int j = 0; j < 15; j++){
                squares[i][j].setTile(null);
                squares[i][j].setEmpty(true);
            }
        }
    }
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
        System.out.println("\n----------------------------------------------------------------\n");
        for(int i = 0; i < 15; i ++){
            for(int j = 0; j < 15; j++){
                System.out.print(squares[i][j].toString());
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
        System.out.println("\n----------------------------------------------------------------\n");

    }
    
    
    void placeWord(Player p, String word, int firstLetterX, int firstLetterY, String direction) throws IllegalArgumentException {
    	if (isValidWord(p,word,firstLetterX,firstLetterY,direction) != true) {
			throw new IllegalArgumentException("Word is invalid");
        }
        
    	//Put string into a tile array list
    	ArrayList<Tile> stringTiles = new ArrayList<Tile>();
    	for (int i = 0; i < word.length(); i++) {
    		Tile tempTile = new Tile(word.charAt(i));
    		stringTiles.add(tempTile);
		}
    	
    	//place based on direction
    	if (direction == "right") {
    		for (int i = 0; i < stringTiles.size(); i++) {
    			squares[firstLetterY][firstLetterX].setTile(stringTiles.get(i));
    			firstLetterX++;
			}
		}
    	else if (direction == "down") {
    		for (int i = 0; i < stringTiles.size(); i++) {
    			squares[firstLetterY][firstLetterX].setTile(stringTiles.get(i));
    			firstLetterY++;
			}
			
		}
    	else {
			throw new IllegalArgumentException("Invalid Direction");
		}
    }
    
    boolean isValidWord(Player p, String word, int firstLetterX, int firstLetterY, String direction) {
        boolean valid = true;
        int taken [] = {0,0,0,0,0,0,0}; //needed to check whether word is placed around already paced letter
        boolean atLeastOne = false;
        int x = firstLetterX;
        int y = firstLetterY;
        boolean out = false;

        //tests if the word is within the bounds of the board
        if(x > 14 || x < 0 || y < 0 || y > 0)
        {
            out = true;
            valid = false;
        }
        else if (direction == "right") 
        {
            if(x + word.length()-1 > 14)
            {
                System.out.println("The word is not within the bounds of the board");
                out = true;
                valid = false;
            }
        }
        else if (direction == "down") 
        {
            if(y + word.length()-1 > 14)
            {
                System.out.println("The word is not within the bounds of the board");
                out = true;
                valid = false;
            }
        }

        if(!out)
        {
            //checks if word is being placed around letters and if they conflict
            if (direction == "right") 
            {
                for (int i = 0; i < word.length(); i++) 
                {
                    if(squares[y][x].isEmpty() != true)
                    {
                        if(word.charAt(i) != squares[y][x].getTile().getLetter())
                        {
                            System.out.println("Word cannot be placed because it is being placed on top of a letter");
                            valid = false;
                        }
                        else
                        {
                            taken[i] = 1;
                        }
                    }
                    x++;
                }			
            }
            else if (direction == "down") 
            {
                for (int i = 0; i < word.length(); i++) 
                {
                    if(squares[y][x].isEmpty() != true)
                    {
                        if(word.charAt(i) != squares[y][x].getTile().getLetter())
                        {
                            System.out.println("Word cannot be placed because it conflicts with existing letters");
                            valid = false;
                        }
                        else
                        {
                            taken[i] = 1;
                        }
                    }
                    y++;
                }	
            }


            
            String letters = "";
            for(int i = 0; i  < word.length(); i++)
            {
                if(taken[i] == 0)
                {
                    letters += word.charAt(i);
                    atLeastOne = true;
                }
                else
                {
                    connects = true;
                }
            }


            //test if frame has at least one letter
            if(!atLeastOne)
            {
                System.out.println("Placement does not include at least one letter from the frame");
                valid = false;
            }

            //test if frame contains necessary letters
            if(p.getFrame().isAvailable(letters) != true)
            {
                System.out.println("The players frame does not contain the letters needed for this word");
                valid = false;
            }

            x = firstLetterX;
            y = firstLetterY;
            
            
            

            //test if this is the first word, its in the centre
            if(squares[7][7].isEmpty())
            {
                boolean centre = false;

                if (direction == "right") {
                    for (int i = 0; i < word.length(); i++) 
                    {
                        if(x == 7 && y == 7)
                        {
                            centre = true;
                        }
                        x++;
                    }
                }
                else if (direction == "down") {
                    for (int i = 0; i < word.length(); i++) 
                    {
                        if(x == 7 && y == 7)
                        {
                            centre = true;
                        }
                        y++;
                    }
                }

                if(centre == false)
                {
                    System.out.println("The first word is not placed covering the centre square");
                    valid = false;
                }
            }
            else if(!connects)  //tests if word connects to another word
            {
                if (direction == "right") {
                    for (int i = 0; i < word.length() && !connects; i++) 
                    {
                        if(i == 0 && x != 0)
                        {
                            if(squares[y][x-1].isEmpty() == false)
                            {
                                connects = true;
                            }
                        }

                        if(y != 0)
                        {
                            if(squares[y-1][x].isEmpty() == false)
                            {
                                connects = true;
                            }
                        }

                        if(y != 14)
                        {
                            if(squares[y+1][x].isEmpty() == false)
                            {
                                connects = true;
                            }
                        }

                        if(i == word.length() -1 && x != 14)
                        {
                            if(squares[y][x+1].isEmpty() == false)
                            {
                                connects = true;
                            }
                        }

                        x++;
                    }
                }
                else if (direction == "down") {
                    for (int i = 0; i < word.length() && !connects; i++) 
                    {
                        {
                            if(i == 0 && y != 0)
                            {
                                if(squares[y-1][x].isEmpty() == false)
                                {
                                    connects = true;
                                }
                            }
        
                            if(firstLetterX != 0)
                            {
                                if(squares[y][x-1].isEmpty() == false)
                                {
                                    connects = true;
                                }
                            }
        
                            if(firstLetterX != 14)
                            {
                                if(squares[y][x+1].isEmpty() == false)
                                {
                                    connects = true;
                                }
                            }
        
                            if(i == word.length() -1 && y != 14)
                            {
                                if(squares[y+1][x].isEmpty() == false)
                                {
                                    connects = true;
                                }
                            }
        
                            y++;
                        }
                    }
                }

                if(!connects)
                {
                    System.out.println("The word does not connect to an existing word");
                    valid = false;
                }
            }
        }

    	return valid;
    }
        
    public boolean checkBounds(String word, int pos[], boolean across){
        if(across){
            if(word.length() + pos[0] > 14)
                return false;
        }
        else{
            if(word.length() + pos[1] > 14)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Pool pool = new Pool();
        Board board = new Board();
        Player player1 = new Player();
        player1.setName("Chris");
        player1.getFrame().refill(pool);
        System.out.println(player1.getFrame() + "\n\n");
        board.placeWord(player1,"I", 7, 7, "right");
        board.printBoard();
        board.placeWord(player1,"E", 7, 15, "right");
        board.printBoard();

    }
}