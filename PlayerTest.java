


public class PlayerTest {
	
	public static void main(String[] args) {
		Frame frame1 = new Frame();
		Frame frame2 = new Frame();

		for (int i = 0; i < 7; i++) {
			frame1.addLetter(Pool.draw());
			frame2.addLetter(Pool.draw());
		}
	
		Player Ahmed = new Player("Ahmed", 0, frame1);
		Player Lleno = new Player("Lleno", 0, frame2);
		

	}
}
	