
public class PlayerTest {
	Frame firstFrame = new Frame();
	Frame secondFrame = new Frame();
	Frame ThirdFrame = new Frame();
	Player Ahmed;
	Player Lleno;
	Player Sean;

	public static void main(String[] args) {
		PlayerTest test = new PlayerTest();
		//test.poolClassTest();
		//test.frameClassTest();
		test.playerClassTest();
	}

	public PlayerTest() {
		firstFrame.refillFrame();
		secondFrame.refillFrame();
		ThirdFrame.refillFrame();

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
		if(Ahmed.getName() == "Ahmed Jouda"){
			System.out.println("SUCCESS");
		}
		else{
			System.out.println("=>FAILIURE");
		}
		if(Lleno.getName() == "Lleno Anya"){
			System.out.println("SUCCESS");
		}
		else{
			System.out.println("=>FAILIURE");
		}
		if(Sean.getName() == "Sean McDonnell"){
			System.out.println("SUCCESS");
		}
		else{
			System.out.println("->FAILIURE");
		}

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

		System.out.println("\nTEST: ALLOWS TILES TO BE REMOVED FROM A FRAME\n----------------------------");
		System.out.println("the first Frame:");
		firstFrame.displayFrame();
		
		System.out.println("Removing: " + firstFrame.tiles.get(0));
		firstFrame.removeTile(firstFrame.tiles.get(0));
		firstFrame.displayFrame();
		
		System.out.println("Removing: " + firstFrame.tiles.get(1));
		firstFrame.removeTile(firstFrame.tiles.get(1));
		firstFrame.displayFrame();

		System.out.println("\nTEST: CHECKS IF TILE IS IN THE FRAME\n----------------------------");
		System.out.println("Test if "+ThirdFrame.tiles.get(3) + " is in the third Frame");
		System.out.println("the third Frame:");
		ThirdFrame.displayFrame();
		System.out.println("->Result: " + ThirdFrame.contains(ThirdFrame.tiles.get(3)));
		System.out.println("Test if A is in the second Frame");
		System.out.println("the second Frame:");
		secondFrame.displayFrame();
		System.out.println("->Result: " + ThirdFrame.contains('A'));

		System.out.println("\nTEST: CHECKS IF THE FRAME IS EMPTY\n----------------------------");
		firstFrame.emptyFrame();
		System.out.println("the first Frame:");
		firstFrame.displayFrame();
		System.out.println("->Expected for the first Frame: true\nGot: " + firstFrame.isEmpty());
		System.out.println("the second Frame:");
		secondFrame.displayFrame();
		System.out.println("->Expected for the second Frame: false\nGot: " + secondFrame.isEmpty());


		System.out.println("\nTEST: CHECKS IF THE FRAME CAN BE ACCESSED TO ADD ELEMENTS\n----------------------------");
		System.out.println("Adding D:");
		firstFrame.addTile('D');
		System.out.println("the first Frame:");
		firstFrame.displayFrame();

		System.out.println("\nTEST: ALLOWS FRAME TO BE REFILLED FROM THE POOL\n----------------------------");
		firstFrame.refillFrame();
		System.out.println("the first Frame:");
		firstFrame.displayFrame();
	}

	public void poolClassTest() {
		System.out.println("\n------------------------------------");
		System.out.println("          POOL CLASS TEST           ");
		System.out.println("------------------------------------");

		// test getValue()
		System.out.println(
				"------------------------------------\nTest the getValue method\n------------------------------------");
		System.out.println("\nExpected: 1");
		System.out.println("Got: " + Pool.getValue('A'));
		System.out.println("\nExpected: 10");
		System.out.println("\nGot: " + Pool.getValue('Z'));
		System.out.println("\nExpected: 0");
		System.out.println("Got: " + Pool.getValue(' '));
		System.out.println("\nExpected: 3");
		System.out.println("Got: " + Pool.getValue('B'));
	

		System.out.println("----------------------------------------------\nTest the reset, draw and getNumOfTiles methods\n----------------------------------------------");
		System.out.println("\nReset the pool");
		Pool.reset(); // resets the pool back to its full 100 tiles

		System.out.println("\nExpected: 100");
		System.out.println("Got: " + Pool.getNumOfTiles());
	
		System.out.println("\ndrew " + Pool.draw());
		System.out.println("drew " + Pool.draw());
		System.out.println("drew " + Pool.draw());
		System.out.println("drew " + Pool.draw());

		System.out.println("\nExpected: 96");
		System.out.println("Got: " + Pool.getNumOfTiles());
	
		System.out.println("\nswapped char in and drew " + Pool.draw('A'));

		System.out.println("\nExpected: 96");
		System.out.println("Got: " + Pool.getNumOfTiles());
	
		System.out.println("\ndrew " + Pool.draw());

		System.out.println("\nExpected: 95");
		System.out.println("Got: " + Pool.getNumOfTiles());
	
	
		System.out.println("\nReset the pool");
		Pool.reset();

		System.out.println("\nExpected: 100");
		System.out.println("Got: " + Pool.getNumOfTiles());

		System.out.println("------------------------------------------\nTest isEmpty method\n------------------------------------------");
		System.out.println("\nExpected: false");
		System.out.println("Got: " + Pool.isEmpty());


	}

}
