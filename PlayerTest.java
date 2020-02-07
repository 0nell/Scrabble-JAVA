import java.util.ArrayList;

public class PlayerTest {
	
public static void main(String[] args) {
	ArrayList<Character> letters = new ArrayList<Character>();
	Pool pool = new Pool();
	for (int i = 0; i < 7; i++) {
		letters.add(pool.draw());
	}
    
    Frame test = new Frame(letters);
	Player ahmed = new Player(" ahmed", 2, test);
	System.out.println(ahmed.getScore());
	ahmed.increaseScore(5);
	System.out.print(ahmed.toString());
	System.out.println(ahmed.getScore());
	ahmed.reset();
	System.out.println(ahmed.toString());
	ahmed.getFrame();
}
}
