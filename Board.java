import java.util.ArrayList;

/**
 * Team Name: El Cucharachas
 * 
 * Students:
 * - Ahmed Jouda 	18329393
 * - Sean Mcdonnell 18391961
 * - Lleno Anya 	18357493
 *
 */
public class Board {
	Square squares[][];
	boolean connects = false; // check if placed word connects with other words
	static String lettersToRemove = ""; // contains letters to remove from player's frame after placement

	// Allows board to be reset
	void resetBoard() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				squares[i][j].setTile(null);
				squares[i][j].setEmpty(true);
			}
		}
	}

	// Constructor
	Board() {
		squares = new Square[15][15];

		// Sets Value of Each Square
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				squares[i][j] = new Square();
				if ((i + j) % 7 == 0 && (i == 0 || i == 7 || i == 14)) {
					squares[i][j].setValue("3W");
				} else if (((i + j - 6) % 4 == 0 && (i == 5 || i == 9 || i == 13 || i == 1) && Math.abs(i - j) > 3
						&& Math.abs(i - j) < 9) || (i == j && j == 9) || (i == j && j == 5)) {
					squares[i][j].setValue("3L");
				} else if (((i == 6 || i == 8) && (j == 6 || j == 8))
						|| (i != j && (i == 3 || i == 11 || i == 14 || i == 0)
								&& (j == 3 || j == 11 || j == 14 || j == 0))
						|| (i != j && (i == 8 || i == 6 || i == 12 || i == 2)
								&& (j == 2 || j == 6 || j == 8 || j == 12))
						|| i == 7 && (j == 3 || j == 11) || j == 7 && (i == 3 || i == 11)) {
					squares[i][j].setValue("2L");
				} else if (i == j || i + j == 14) {
					squares[i][j].setValue("2W");
				}

			}
		}
		squares[7][7].setValue("**");
	}

	void printBoard() {
		System.out.println("\n----------------------------------------------------------------\n");
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				System.out.print(squares[i][j].toString());
			}
			System.out.print(" " + i + "\n");
		}

		// Prints Square Co-ordinates
		for (int i = 0; i < 10; i++) {
			System.out.print("  " + i + " ");
		}
		for (int i = 10; i < 15; i++) {
			System.out.print(" " + i + " ");
		}
		System.out.println("\n----------------------------------------------------------------\n");

	}

	// Places the word on the board if it is valid and removes the used tiles from
	// the Player's frame
	void placeWord(Player p, String word, int firstLetterX, int firstLetterY, String direction)
			throws IllegalArgumentException {
		
		word = word.toUpperCase();	//in case the user inputed lower case
		
		// if the word is invalid throw an illegal argument exception
		if (isValidWord(p, word, firstLetterX, firstLetterY, direction) != true) {
			throw new IllegalArgumentException("Word is invalid");
		}

		// Put string (given word) into a tile array list, each letter = 1 tile
		ArrayList<Tile> stringTiles = new ArrayList<Tile>();
		for (int i = 0; i < word.length(); i++) {
			Tile tempTile = new Tile(word.charAt(i));
			stringTiles.add(tempTile);
		}

		// place on board based on direction specified
		if (direction == "right") {
			for (int i = 0; i < stringTiles.size(); i++) {
				squares[firstLetterY][firstLetterX].setTile(stringTiles.get(i));
				firstLetterX++;
			}
		} else if (direction == "down") {
			for (int i = 0; i < stringTiles.size(); i++) {
				squares[firstLetterY][firstLetterX].setTile(stringTiles.get(i));
				firstLetterY++;
			}

		} else {
			throw new IllegalArgumentException("Invalid Direction");
		}
		p.getFrame().remove(lettersToRemove);
		lettersToRemove = ""; // reset letters to remove
	}

	// Checks if word is valid and returns an indicative result
	boolean isValidWord(Player p, String word, int firstLetterX, int firstLetterY, String direction) {
		boolean valid = true;	//boolean to return
		int taken[] = { 0, 0, 0, 0, 0, 0, 0 }; // needed to check whether word is placed around already paced letter
		boolean atLeastOne = false;		//placement uses at least one letter from the rack
		int x = firstLetterX;	//coordinates to place
		int y = firstLetterY;

		if (checkBounds(word, x, y, direction)) {

			// checks if word is being placed around letters and if they conflict
			if (direction == "right") {
				for (int i = 0; i < word.length(); i++) {
					if (squares[y][x].isEmpty() != true) {
						if (word.charAt(i) != squares[y][x].getTile().getLetter()) {
							System.out.println("Word cannot be placed because it is being placed on top of a letter");
							valid = false;
						} else {
							taken[i] = 1;
						}
					}
					x++;
				}
			} else if (direction == "down") {
				for (int i = 0; i < word.length(); i++) {
					if (squares[y][x].isEmpty() != true) {
						if (word.charAt(i) != squares[y][x].getTile().getLetter()) {
							System.out.println("Word cannot be placed because it conflicts with existing letters");
							valid = false;
						} else {
							taken[i] = 1;
						}
					}
					y++;
				}
			}

			for (int i = 0; i < word.length(); i++) {
				if (taken[i] == 0) {
					lettersToRemove += word.charAt(i);
					atLeastOne = true;	//placement uses at least one letter from the rack
				} else {
					connects = true;
				}
			}

			// test if frame has at least one letter
			if (!atLeastOne) {
				System.out.println("Placement does not include at least one letter from the frame");
				valid = false;
			}

			// test if players frame contains necessary letters
			if (p.getFrame().isAvailable(lettersToRemove) != true) {
				System.out.println("The players frame does not contain the letters needed for this word");
				valid = false;
			}

			x = firstLetterX;
			y = firstLetterY;

			if (squares[7][7].isEmpty()) {
				
				//test if its the first word being placed is in the center, if not its not valid
				if (!checkFirstWord(word, x, y, direction)) {
					valid = false;
				}
			} else if (!connects) // tests if word connects to another word, if not its not valid
			{
				if (!checkConnects(word, x, y, direction)) {
					valid = false;
				}
			}
		}

		return valid;
	}

	//Checks if the first word is being placed in the center
	boolean checkFirstWord(String word, int x, int y, String direction) {
		if (direction == "right") {
			for (int i = 0; i < word.length(); i++) {
				if (x == 7 && y == 7) {
					return true;
				}
				x++;
			}
		} else if (direction == "down") {
			for (int i = 0; i < word.length(); i++) {
				if (x == 7 && y == 7) {
					return true;
				}
				y++;
			}
		}

		System.out.println("The first word is not placed covering the centre square");
		return false;

	}

	//Checks if the the word being placed connects with previously placed words
	boolean checkConnects(String word, int x, int y, String direction) {
		if (direction == "right") {
			for (int i = 0; i < word.length() && !connects; i++) {
				if (i == 0 && x != 0) {
					if (squares[y][x - 1].isEmpty() == false) {
						return true;
					}
				}

				if (y != 0) {
					if (squares[y - 1][x].isEmpty() == false) {
						return true;
					}
				}

				if (y != 14) {
					if (squares[y + 1][x].isEmpty() == false) {
						return true;
					}
				}

				if (i == word.length() - 1 && x != 14) {
					if (squares[y][x + 1].isEmpty() == false) {
						return true;
					}
				}

				x++;
			}
		} else if (direction == "down") {
			for (int i = 0; i < word.length() && !connects; i++) {
				{
					if (i == 0 && y != 0) {
						if (squares[y - 1][x].isEmpty() == false) {
							return true;
						}
					}

					if (x != 0) {
						if (squares[y][x - 1].isEmpty() == false) {
							return true;
						}
					}

					if (x != 14) {
						if (squares[y][x + 1].isEmpty() == false) {
							return true;
						}
					}

					if (i == word.length() - 1 && y != 14) {
						if (squares[y + 1][x].isEmpty() == false) {
							return true;
						}
					}

					y++;
				}
			}
		}

		System.out.println("The word does not connect to an existing word");
		return false;
	}

	// tests if the word is within the bounds of the board
	public boolean checkBounds(String word, int x, int y, String direction) {
		if (x > 14 || x < 0 || y < 0 || y > 14) {
			System.out.println("The word is not within the bounds of the board");
			return false;
		} else if (direction == "right" && x + word.length() - 1 > 14) {
			System.out.println("The word is not within the bounds of the board");
			return false;
		} else if (direction == "down" && y + word.length() - 1 > 14) {
			System.out.println("The word is not within the bounds of the board");
			return false;
		} else
			return true;
	}

	public static void main(String[] args) {
		Pool pool = new Pool();
		Board board = new Board();
		Player player1 = new Player();
		player1.setName("Chris");
		player1.getFrame().refill(pool);
		System.out.println(player1.getFrame() + "\n\n");
		board.placeWord(player1, "i", 7, 7, "right");
		board.printBoard();
		System.out.println(player1.getFrame() + "\n\n");
		board.placeWord(player1, "E", 7, 8, "right");
		board.printBoard();
		System.out.println(player1.getFrame() + "\n\n");
		board.placeWord(player1, "A", 8, 8, "right");
		board.printBoard();
		System.out.println(player1.getFrame() + "\n\n");

	}
}