
public class Player {

	private String name;
	private int score = 0;
	Frame frame;

	public Player(String name, Frame frame) {
		super();
		this.name = name;
		this.frame = frame;
	}

	// Allows display of player's name
	public String getName() {
		if (name != null) {
			return name;
		} else
			return "";
	}

	public void setName(String name) {
		if (Character.isWhitespace(name.charAt(0))) {
			throw new IllegalArgumentException();
		} else
			this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore(int plusScore) {
		this.score = this.score + plusScore;
	}

	public void reset() {
		score = 0;
		name = null;
		frame.emptyFrame();
	}

	public void displayFrame() {
		frame.displayFrame();
	}

	@Override
	public String toString() {
		return "The player's name is: " + getName();
	}
}
