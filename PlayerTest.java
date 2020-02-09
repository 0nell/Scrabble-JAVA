
public class PlayerTest {
	Frame firstFrame = new Frame();
	Frame secondFrame = new Frame();
	Frame ThirdFrame = new Frame();
	Player Ahmed;
	Player Lleno;
	Player Sean;

	public static void main(String[] args) {
		PlayerTest test = new PlayerTest();
		
		test.frameClassTest();
		test.playerClassTest();
		test.poolClassTest();
	}

	public PlayerTest() {
		
		//Initial Tile Storage
		firstFrame.refillFrame();
		secondFrame.refillFrame();
		ThirdFrame.refillFrame();

		//Initial player creation
		Ahmed = new Player("Ahmed", firstFrame);
		Lleno = new Player("Lleno", secondFrame);
		Sean = new Player("Sean", ThirdFrame);
	}

	public void playerClassTest() {
		System.out.println("\n------------------------------------");
		System.out.println("          PLAYER CLASS TEST           ");
		System.out.println("------------------------------------");
		// Allow Player's name to be set
		Ahmed.setName("Ahmed Jouda");
		Lleno.setName("Lleno Anya");
		Sean.setName("Sean McDonnell");
		System.out.println("TEST: NAME ALLOWED TO BE SET\n----------------------------");
		System.out.println("->Expected for Ahmed: Ahmed Jouda\nGot: " + Ahmed.getName());
		System.out.println("->Expected for Lleno: Lleno Anya\nGot: " + Lleno.getName());
		System.out.println("->Expected for Sean: Sean McDonnell\nGot: " + Sean.getName());

		// Allow Score to be increased
		Ahmed.increaseScore(7); // increase score by 7
		Lleno.increaseScore(5); // increase score by 5
		Sean.increaseScore(3); // increase score by 3
		System.out.println("\nTEST: SCORES ALLOWED TO INCREASE\n--------------------------------");
		System.out.println("->Expected for Ahmed: 7\nGot: " + Ahmed.getScore());
		System.out.println("->Expected for Lleno: 5\nGot: " + Lleno.getScore());
		System.out.println("->Expected for Sean: 3\nGot: " + Sean.getScore());

		// Display Player's name
		Ahmed.getName();
		Lleno.getName();
		Sean.getName();
		System.out.println("\nTEST: ALLOWS TO DISPLAY NAME\n--------------------------------");
		System.out.println("->Expected for Ahmed: Ahmed Jouda\nGot: " + Ahmed.getName());
		System.out.println("->Expected for Lleno: Lleno Anya\nGot: " + Lleno.getName());
		System.out.println("->Expected for Sean: Sean McDonnell\nGot: " + Sean.getName());

		// Allow access to player's frame
		System.out.println("\nTEST: ALLOWS ACCESS TO PLAYER'S FRAME\n-------------------------------------");
		System.out.println("Ahmed's Frame");
		Ahmed.displayFrame();
		System.out.println("Lleno's Frame");
		Lleno.displayFrame();
		System.out.println("Sean's Frame");
		Sean.displayFrame();

		// Allow Player to be reset
		System.out.println("\nTEST: RESETING THE PLAYER (NAME & SCORE)\n----------------------------------------");
		Ahmed.reset();
		Lleno.reset();
		Sean.reset();
		System.out.println("->Expected for ALL: Empty name & Empty Frame & score = 0 ");
		System.out.println(
				"\nGot for Ahmed: " + "\nName: " + Ahmed.getName() + "Score: " + Ahmed.getScore() + " Frame: ");
		Ahmed.displayFrame();
		System.out.println(
				"\nGot for Lleno: " + "\nName: " + Lleno.getName() + "Score: " + Lleno.getScore() + " Frame: ");
		Lleno.displayFrame();
		System.out.println("\nGot for Sean: " + "\nName: " + Sean.getName() + "Score: " + Sean.getScore() + " Frame: ");
		Sean.displayFrame();

		System.out.println("\n\n*** END OF PLAYER CLASS TEST ***");
	}

	public void frameClassTest() {
		System.out.println("\n------------------------------------");
		System.out.println("          FRAME CLASS TEST           ");
		System.out.println("------------------------------------");
		
		// Stores tiles and displays them
		System.out.println("TEST: TILES STORED AND DISPLAYED\n----------------------------");
		System.out.println("the first Frame:");
		firstFrame.displayFrame();
		System.out.println("the second Frame:");
		secondFrame.displayFrame();
		System.out.println("the third Frame:");
		ThirdFrame.displayFrame();

		//Allows tiles to be removed
		System.out.println("\nTEST: ALLOWS TILES TO BE REMOVED FROM A FRAME\n----------------------------");
		System.out.println("the first Frame:");
		firstFrame.displayFrame();
		
		System.out.println("Removing: |" + firstFrame.tiles.get(0)+"|");
		firstFrame.removeTile(firstFrame.tiles.get(0));
		firstFrame.displayFrame();
		
		System.out.println("Removing:|" + firstFrame.tiles.get(1)+"|");
		firstFrame.removeTile(firstFrame.tiles.get(1));
		firstFrame.displayFrame();

		//Allows frame to be checked for certain tiles
		System.out.println("\nTEST: CHECKS IF TILE IS IN THE FRAME\n----------------------------");
		System.out.println("Test if |"+ThirdFrame.tiles.get(3) + "| is in the third Frame");
		System.out.println("the third Frame:");
		ThirdFrame.displayFrame();
		System.out.println("->Result: " + ThirdFrame.contains(ThirdFrame.tiles.get(3)));
		System.out.println("Test if |A| is in the second Frame");
		System.out.println("the second Frame:");
		secondFrame.displayFrame();
		System.out.println("->Result: " + secondFrame.contains('A'));

		//Allows frame to be checked to see if it is empty
		System.out.println("\nTEST: CHECKS IF THE FRAME IS EMPTY\n----------------------------");
		firstFrame.emptyFrame();
		System.out.println("the first Frame:");
		firstFrame.displayFrame();
		System.out.println("->Expected for the first Frame: true\nGot: " + firstFrame.isEmpty());
		System.out.println("the second Frame:");
		secondFrame.displayFrame();
		System.out.println("->Expected for the second Frame: false\nGot: " + secondFrame.isEmpty());

		//Allows frame to be accessed to add tiles
		System.out.println("\nTEST: CHECKS IF THE FRAME CAN BE ACCESSED TO ADD ELEMENTS\n----------------------------");
		System.out.println("Adding |D|:");
		firstFrame.addTile('D');
		System.out.println("the first Frame:");
		firstFrame.displayFrame();

		//Allows frame to be refilled from the pool
		System.out.println("\nTEST: ALLOWS FRAME TO BE REFILLED FROM THE POOL\n----------------------------");
		firstFrame.refillFrame();
		System.out.println("the first Frame:");
		firstFrame.displayFrame();

		System.out.println("\n\n*** END OF FRAME CLASS TEST ***");
	}

	public void poolClassTest() {
		System.out.println("\n----------------------------------------");
		System.out.println("            POOL CLASS TEST             ");
		System.out.println("----------------------------------------");

		//tests if the the score associated with a tile can be queried
		System.out.println("TEST: ALLOWS VALUE OF TILE TO BE QUERIED\n----------------------------------------");
		System.out.println("->Expected value for tile A: 1");
		System.out.println("Got: " + Pool.getValue('A'));
		System.out.println("->Expected value for tile Z: 10");
		System.out.println("Got: " + Pool.getValue('Z'));
		System.out.println("->Expected value for blank tile: 0");
		System.out.println("Got: " + Pool.getValue(' '));
		System.out.println("->Expected vallue for tile B: 3");
		System.out.println("Got: " + Pool.getValue('B'));
	
		//tests if the pool can be reset and if the number of tiles left in the pool can be queried
		System.out.println("\nTEST: ALLOWS POOL TO BE RESET\n      ALLOWS DISPLAY OF NUMBER OF TILES IN THE POOL\n----------------------------------------------------------------------");
		System.out.println("The above tests for player and frame have drawn 27 tiles from the pool");
		System.out.println("->Expected amount of tiles in the pool: 73");
		System.out.println("Got: " + Pool.getNumOfTiles());
		System.out.println("\nReset the pool using reset() method");
		Pool.reset(); // resets the pool back to its full 100 tiles
		System.out.println("\n->Expected amount of tiles in the pool: 100");
		System.out.println("Got: " + Pool.getNumOfTiles());

		//tests whether tiles can be drawn from the pool at random
		System.out.println("\nTEST: ALLOWS TILES TO BE DRAWN AT RANDOM FROM POOL\n--------------------------------------------------");
		System.out.println("->Drew " + "|" + Pool.draw() + "|");
		System.out.println("->Expected amount of tiles in the pool: 99");
		System.out.println("Got: " + Pool.getNumOfTiles());
		System.out.println("->Drew " + "|" + Pool.draw() + "|");
		System.out.println("->Expected amount of tiles in the pool: 98");
		System.out.println("Got: " + Pool.getNumOfTiles());
		System.out.println("->Drew " + "|" + Pool.draw() + "|");
		System.out.println("->Expected amount of tiles in the pool: 97");
		System.out.println("Got: " + Pool.getNumOfTiles());
		System.out.println("->Drew " + "|" + Pool.draw() + "|");
		System.out.println("->Expected amount of tiles in the pool: 96");
		System.out.println("Got: " + Pool.getNumOfTiles());
		System.out.println("->Drew " + "|" + Pool.draw() + "|");
		System.out.println("->Expected amount of tiles in the pool: 95");
		System.out.println("Got: " + Pool.getNumOfTiles());
		System.out.println("\nDrawing 10 more tiles from pool at random\n");
		for(int i = 1; i<= 10; i++)	//draws and prints 10 tiles
		{
			System.out.print("|" + Pool.draw() + "|");
		}
		System.out.println("\n\n->Expected amount of tiles in the pool: 85");
		System.out.println("Got: " + Pool.getNumOfTiles());
		System.out.println("\n->Swapped in an unwanted tile and drew " +  "|" + Pool.swapTile('A') + "|");
		System.out.println("->Expected amount of tiles in the pool: 90");
		System.out.println("Got: " + Pool.getNumOfTiles());

		//tests whether the pool can be checked to see if its empty
		System.out.println("\nTEST: ALLOWS THE POOL TO BE CHECKED TO SEE IF ITS EMPTY\n-------------------------------------------------------");
		System.out.println("->Reset the pool using reset() method\n");
		Pool.reset(); // resets the pool back to its full 100 tiles
		System.out.println("->Check if pool is empty");
		System.out.println("->Expected: false");
		System.out.println("Got: " + Pool.isEmpty());
		System.out.println("\n->Drew 100 tiles\n");
		for(int i = 1; i<= 100; i++)	//prints the tiles drawn in rows of 10
		{
			System.out.print("|" + Pool.draw() + "|");
			if(i % 10 == 0)
			{
				System.out.print("\n");
			}
		}
		System.out.println("\n\n->Check if pool is empty");
		System.out.println("->Expected: true");
		System.out.println("Got: " + Pool.isEmpty());

		System.out.println("\n\n*** END OF POOL CLASS TEST ***");
	}
}
