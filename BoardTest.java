import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.*;

/**
 * Team Name: El Cucharachas
 * 
 * Students:
 * - Ahmed Jouda 	18329393
 * - Sean Mcdonnell 18391961
 * - Lleno Anya 	18357493
 *
 */
public class BoardTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
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
			thrown = true;
		}
		System.out.println("Placed 'i' which shouldn't throw an error as it is in the frame.\nEXPECTED: error thrown = false\nGOT: error thrown ="+thrown);
		board.printBoard();
		System.out.println("Updated Frame: "+player1.getFrame() + "\n\n");	
		thrown = false;	
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
		System.out.println("\nTry place a 3 letter word right, so not all letters are on the board, at coordinates 13 & 13:");
		System.out.println("EXPECTED: \nThe word is not within the bounds of the board" + "\nGOT: ");
		board.checkBounds("CAT", 13, 13, "right");
		
		System.out.println("\n***************************************************************");
		System.out.println("\nTEST SIX : CHECK WHETHER PLACEMENT CONFLICTS WITH AN EXISTING LETTER");
		System.out.println("\n***************************************************************");
		//Check whether the word conflicts with any existing letters
		boolean errorThrown = false;
		System.out.println("Test Frame made: "+player1.getFrame() + "\n\n");
		System.out.println("Try place tile 'C' over an empty square at 7,8");
		try {
			board.placeWord(player1, "c", 7, 8, "right");
		} catch (IllegalArgumentException e) {
			errorThrown=true;
		}
		board.printBoard();
		System.out.println("Placed 'C' over empty square which shouldn't throw an error.\nEXPECTED: error thrown = false\nGOT: error thrown =" + errorThrown);
		System.out.println("\n\nTry place tile 'T' over 'I' at 7,7");
		try {
			board.placeWord(player1, "t", 7, 7, "right");
		} catch (Exception e) {
			errorThrown=true;
		}
		System.out.println("Placed 't' over 'I' which should throw an error.\nEXPECTED: error thrown = true\nGOT: error thrown =" + errorThrown);
		
		System.out.println("\n***************************************************************");
		System.out.println("\nTEST SEVEN : IF ITS THE 1ST WORD, IT MUST BE AT THE CENTRE");
		System.out.println("\n***************************************************************");
		//reset the board to start fresh
		board.resetBoard();
		System.out.println("Remaining tiles: "+player1.getFrame() + "\n\n");
		System.out.println("Try place 'WAT' at the center.");
		board.placeWord(player1, "WAT", 7, 7, "right");
		board.printBoard();
		System.out.println("Reset Board. Try place 'NL' at anywhere but the center the center.");
		board.resetBoard();
		boolean center = true;
		try {
			board.placeWord(player1, "NL", 7, 8, "right");
		} catch (IllegalArgumentException e) {
			center = false;
		}
		System.out.println("It fails. Its is not placed at center.\nExpected: false\nGot: "+center);
		board.printBoard();
		
		System.out.println("\n***************************************************************");
		System.out.println("\nTEST EIGHT : IF IT ISN'T THE 1ST WORD, IT CONNECTS WITH WORDS ALREADY ON THE BOARD");
		System.out.println("\n***************************************************************");
		//reset the board to start fresh
		board.resetBoard();
		System.out.println("Remaining tiles: "+player1.getFrame() + "\n\n");
		System.out.println("Place 'N' at the center.");
		board.placeWord(player1, "N", 7, 7, "right");
		board.printBoard();
		System.out.println("Now try place L somewhere unconnected with N.");
		boolean connect = true;		
		try {
			board.placeWord(player1, "L", 10, 8, "right");
		} catch (IllegalArgumentException e) {
			connect = false;
		}
		System.out.println("It fails. Its is not connected.\nExpected: false\nGot: "+connect);
		board.printBoard();
		System.out.println("Now try place L somewhere connected with N.");
		board.placeWord(player1, "L", 7, 8, "right");
		board.printBoard();
		System.out.println("It works and L is added.");
		
		System.out.println("\n***************************************************************");
		System.out.println("\nTEST NINE : PLACE WORDS TEST - DOWN & RIGHT");
		System.out.println("\n***************************************************************");
		//reset the board to start fresh & refill frame
		board.resetBoard();
		player1.getFrame().refillForTest();
		System.out.println("Player's Tiles: "+player1.getFrame() + "\n\n");
		System.out.println("\nTest to place 'CAT' down");
		board.placeWord(player1, "CAT", 7, 7, "down");
		board.printBoard();
		System.out.println("\nTest to place 'TIN' right");
		board.placeWord(player1, "TIN", 7, 9, "right");
		board.printBoard();

		System.out.println("\n***************************************************************");
		System.out.println("\nTEST TEN : CHECK WHETHER AT LEAST ONE TILE FROM THE RACK IS USED");
		System.out.println("\n***************************************************************");
		//reset the board to start fresh & refill frame
		board.resetBoard();
		player1.getFrame().refillForTest();
		System.out.println("Player's Tiles: "+player1.getFrame() + "\n\n");
		System.out.println("\nPlace 'CAT' down");
		board.placeWord(player1, "CAT", 7, 7, "down");
		board.printBoard();
		boolean used = false;
		//Trying to put 'CAT' over 'CAT' which uses no new tiles form frame
		try {
			board.placeWord(player1, "CAT", 7, 7, "down");
		} catch (IllegalArgumentException e) {
			used = true;
		}
		System.out.println("Placed 'CAT'over 'CAT' which should return an error.\nEXPECTED: error thrown = true\nGOT: error thrown ="+used);
		board.printBoard();
	}
	

}
