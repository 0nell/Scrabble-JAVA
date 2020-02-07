


public class PlayerTest {
	Frame frame1 = new Frame();
	Frame frame2 = new Frame();
	Frame frame3 = new Frame();
	
	Player Ahmed;
	Player Lleno;
	Player Sean;
	
	
	public PlayerTest() {

		for (int i = 0; i < 7; i++) {
			frame1.addLetter(Pool.draw());
			frame2.addLetter(Pool.draw());
			frame3.addLetter(Pool.draw());
		}
		
		Ahmed = new Player("Ahmed", frame1);
		Lleno = new Player("Lleno", frame2);
		Sean = new Player("Sean", frame3);
	}


	public void PlayerClassTest() {
		
		//Allow Player's name to be set
		Ahmed.setName("Ahmed Jouda");
		Lleno.setName("Lleno Anya");
		Sean.setName("Sean McDonnell");
		System.out.println("TEST: NAME ALLOWED TO BE SET\n----------------------------");
		System.out.println("->Expected for Ahmed: Ahmed Jouda\nGot: " + Ahmed.getName());
		System.out.println("->Expected for Lleno: Lleno Anya\nGot: " + Lleno.getName());
		System.out.println("->Expected for Sean: Sean McDonnell\nGot: " + Sean.getName());
		
		//Allow Score to be increased
		Ahmed.increaseScore(7); 	//increase score by 7
		Lleno.increaseScore(5); 	//increase score by 5
		Sean.increaseScore(3); 		//increase score by 3
		System.out.println("\nTEST: SCORES ALLOWED TO INCREASE\n--------------------------------");
		System.out.println("->Expected for Ahmed: 7\nGot: " + Ahmed.getScore());
		System.out.println("->Expected for Lleno: 7\nGot: " + Lleno.getScore());
		System.out.println("->Expected for Sean: 7\nGot: " + Sean.getScore());
		
		//Display Player's name
		Ahmed.getName();
		Lleno.getName();
		Sean.getName();
		System.out.println("\nTEST: ALLOWS TO DISPLAY NAME\n--------------------------------");
		System.out.println("->Expected for Ahmed: Ahmed Jouda\nGot: " + Ahmed.getName());
		System.out.println("->Expected for Lleno: Lleno Anya\nGot: " + Lleno.getName());
		System.out.println("->Expected for Sean: Sean McDonnell\nGot: " + Sean.getName());
		
		
		//Allow access to player's frame
		System.out.println("\nTEST: ALLOWS ACCESS TO PLAYER'S FRAME\n-------------------------------------");
		System.out.println("Ahmed's Frame"); Ahmed.getFrame();
		System.out.println("Lleno's Frame"); Lleno.getFrame();
		System.out.println("Sean's Frame"); Sean.getFrame();
		
		//Allow Player to be reset
		System.out.println("\nTEST: RESETING THE PLAYER (NAME & SCORE)\n----------------------------------------");
		Ahmed.reset();
		Lleno.reset();
		Sean.reset();
		System.out.println("->Expected for ALL: Empty name & score = 0");
		System.out.println("\nGot for Ahmed: " + "\nName: " + Ahmed.getName() + "Score: " + Ahmed.getScore());
		System.out.println("\nGot for Lleno: " + "\nName: " + Lleno.getName() + "Score: " + Lleno.getScore());
		System.out.println("\nGot for Sean: " + "\nName: " + Sean.getName() + "Score: " + Sean.getScore());
		
		System.out.println("\n\n*** END OF PLAYER CLASS TEST ***");
	}

	public void poolTest()
	{
		System.out.println("\n------------------------------------");
		System.out.println("          POOL CLASS TEST           ");
		System.out.println("------------------------------------");
		//test getValue()
		System.out.println("\nExpected: 1");
		System.out.println("Got: " + Pool.getValue('A'));
		System.out.println("\nExpected: 10");
		System.out.println("\nGot: " + Pool.getValue('Z'));
		System.out.println("\nExpected: 0");
		System.out.println("Got: " + Pool.getValue(' '));
		System.out.println("\nExpected: 3");
		System.out.println("Got: " + Pool.getValue('B'));
	
		//test toString
		System.out.println("\nReset the pool");
		Pool.reset(); //resets the pool back to its full 100 tiles
		
		System.out.println("\nExpected: 100");
		System.out.println("Got: " + Pool.remaining());
	
		System.out.println("\ndrew " + Pool.draw());
		System.out.println("drew " + Pool.draw());
		System.out.println("drew " + Pool.draw());
		System.out.println("drew " + Pool.draw());
	
		System.out.println("\nExpected: 96");
		System.out.println("Got: " + Pool.remaining());
	
		System.out.println("\nswapped char in and drew " + Pool.draw('A'));
	
		System.out.println("\nExpected: 96");
		System.out.println("Got: " + Pool.remaining());
	
		System.out.println("\ndrew " + Pool.draw());
	
		System.out.println("\nExpected: 95");
		System.out.println("Got: " + Pool.remaining());
	
	
		System.out.println("\nReset the pool");
		Pool.reset();
	
		System.out.println("\nExpected: 100");
		System.out.println("Got: " + Pool.remaining());
	}
	

	public static void main(String[] args) {
		PlayerTest ourTest = new PlayerTest();
		ourTest.PlayerClassTest();
		ourTest.poolTest();
	}
	
	
}
	