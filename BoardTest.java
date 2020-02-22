/**
 * Team Name: El Cucharachas
 * 
 * Students:
 * - Ahmed Jouda 	18329393
 * - Sean Mcdonnell 18391961
 * - Lleno Anya 	18357493
 *
 */
public class BoardTest {

	public static void main(String[] args) {
		
		System.out.println("***************************************************************");
		System.out.println("\nTEST ONE : CONSTRUCT BOARD & DISPLAY BOARD W/ COORDINATES");
		System.out.println("\n***************************************************************");
		//Board Construction with Square Values
		Board board = new Board();
		//Print / Display board with coordinates
		System.out.println("EXPECTED: Full Scrabble board with square values and coordinates.\nGOT:");
		board.printBoard();
		
		System.out.println("\n***************************************************************");
		System.out.println("\nTEST TWO : PLACES & STORES CURRENT TILE POSITIONS");
		System.out.println("\n***************************************************************");
		//Placing tiles
		Tile tempTile = new Tile('W');
		board.squares[7][7].setTile(tempTile);
		System.out.println("EXPECTED: Full Scrabble board with 'W' at square 7,7.\nGOT:");
		board.printBoard();
		System.out.println("Get tile stored at 7,7\n EXPECTED: W" + "\n GOT:" + board.squares[7][7].getTile());
		
		System.out.println("\n***************************************************************");
		System.out.println("\nTEST THREE : BOARD RESET");
		System.out.println("\n***************************************************************");
		//Test board reset
		System.out.println("EXPECTED: Full Scrabble board with square values and coordinates.\n'W' must be gone.\nGOT:");
		board.resetBoard();
		board.printBoard();
	}
	
	

	
	//Stores current tile positions test??
	//Place word test both right and down
	//WORD VALID TEST: Check if player has required tiles
	//				   Check if the placement is within bounds of the board
	//				   Check whether the word conflicts with any existing letters
	//				   Check whether the placement uses atleast one letter from the rack
	//				   Check if it is the 1st word, it is in the center of the board
	//				   Check if it isn't the 1st word, it connects with words already on the board
	
	

}
