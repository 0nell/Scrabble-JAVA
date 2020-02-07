


public class PlayerTest {
	Frame frame1 = new Frame();
	Frame frame2 = new Frame();
	Frame frame3 = new Frame();
	Player Ahmed;
	Player Lleno;
	Player Sean;

	public void frameClassTest(){	
		//Stores letters and displays them
		System.out.println("Stores and displays the letters in the player's frame");
		frame1.displayFrame();
		frame2.displayFrame();
		frame3.displayFrame();

		frame1.removeLetter(frame1.letters.get(0));
		System.out.println(frame2.contains('G'));
		System.out.println(frame3.contains(frame3.letters.get(3)));
		frame1.displayFrame();

		for(int i = 0; i < 6; i++){
			if(frame1.contains(frame1.letters.get(0)))
				frame1.removeLetter(frame1.letters.get(0));
		}
		System.out.println(frame1.isEmpty());
		System.out.println(frame2.isEmpty());
		frame1.addLetter('D');
		frame1.displayFrame();

		frame1.addLetter(Pool.draw());
		frame1.addLetter(Pool.draw());
		frame1.displayFrame();
		
	}

	public static void main(String[] args) {
		PlayerTest pt = new PlayerTest();
		pt.frameClassTest();

	}

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
}
	