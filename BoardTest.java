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
		
		System.out.println("\n***************************************************************");
		System.out.println("\nTEST FOUR : CHECK IF PLAYER HAS REQUIRED TILES");
		System.out.println("\n***************************************************************");
		Player player1 = new Player();
		player1.setName("Chris");
		player1.getFrame().refillForTest();
		//Check if player has required tiles
		System.out.println("Test Frame made: "+player1.getFrame() + "\n\n");
		boolean thrown = false;
		try {
			board.placeWord(player1, "i", 7, 7, "right");
		} catch (IllegalArgumentException e) {
			thrown = false;
		}
		System.out.println("Placed 'i' which shouldn't throw an error as it is in the frame.\nEXPECTED: error thrown = false\nGOT: error thrown ="+thrown);
		board.printBoard();
		System.out.println("Updated Frame: "+player1.getFrame() + "\n\n");		
		try {
			board.placeWord(player1, "z", 7, 8, "right");
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		System.out.println("Placed 'z' which should throw an error as it isn't in the frame.\nEXPECTED: error thrown = true\nGOT: error thrown = " + thrown);
		board.printBoard();
		System.out.println("Updated Frame: "+player1.getFrame() + "\n\n");
		
		
		System.out.println("\n***************************************************************");
		System.out.println("\nTEST FIVE : CHECK IF PLACEMENT IS WITHIN THE BOUNDS OF THE BOARD");
		System.out.println("\n***************************************************************");
		//Check if placement is within the bounds of the board
		System.out.println("Try place at coordinates 17 & 20:");
		System.out.println("EXPECTED: \nThe word is not within the bounds of the board" + "\nGOT: ");
		board.checkBounds("A", 17, 20, "right");
		System.out.println("\nTry place at coordinates 1 & 2:");
		System.out.println("EXPECTED: *nothing as it passes (no error)*" + "\nGOT: ");
		board.checkBounds("A", 1, 2, "right");
		
		System.out.println("\n***************************************************************");
		System.out.println("\nTEST FIVE : CHECK WHETHER PLACEMENT CONFLICTS WITH AN EXISTING LETTER");
		System.out.println("\n***************************************************************");
		//Check whether the word conflicts with any existing letters
		Board board2 = new Board();
		Player player2 = new Player();
		player2.setName("Mark");
		player2.getFrame().refillForTest();
		System.out.println("Test Frame made: "+player2.getFrame() + "\n\n");
		boolean errorThrown = false;
		//System.out.println("Test Frame made: "+player1.getFrame() + "\n\n");
		System.out.println("Try place tile 'C' over an empty square at 7,7");
		try {
			board2.placeWord(player2, "i", 7, 7, "right");
		} catch (Exception e) {
			errorThrown=true;
		}
		board2.printBoard();
		System.out.println("Placed 't' over empty square which shouldn't throw an error.\\nEXPECTED: error thrown = false\\nGOT: error thrown =" + errorThrown);
		System.out.println("\n\nTry place tile 'T' over 'I' at 7,7");
		try {
			board2.placeWord(player2, "i", 7, 7, "right");
		} catch (Exception e) {
			errorThrown=true;
		}
		System.out.println("Placed 't' over 'I' which should throw an error.\\nEXPECTED: error thrown = true\\nGOT: error thrown =" + errorThrown);
		
	}
	
	

	
	
	//Place word test both right and down
	//WORD VALID TEST: DONE - Check if player has required tiles
	//				   DONE - Check if the placement is within bounds of the board
	//				   Check whether the word conflicts with any existing letters
	//				   Check whether the placement uses atleast one letter from the rack
	//				   Check if it is the 1st word, it is in the center of the board
	//				   Check if it isn't the 1st word, it connects with words already on the board
	
	

}
