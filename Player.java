
public class Player {

	//instance variables
	private String name;
	private int score = 0;
	Frame frame;

	//Constructor
	public Player(String name, Frame frame) {
		this.name = name;
		this.frame = frame;
	}

	// Allows display of player's name
	public String getName() {
		String playerName = null;
		if (name != null) {
			playerName = name;
		} else {
			playerName = "";
		}
		return playerName;
	}

	
	public void setName(String name) {
		//doesn't allow the name to be set to nothing
		if (Character.isWhitespace(name.charAt(0))) {
			throw new IllegalArgumentException();
		} else
			this.name = name;
	}

	//returns the player's score
	public int getScore() {
		return score;
	}

	//adds the int passed as a parameter to the player's score
	public void increaseScore(int plusScore) {
		this.score = this.score + plusScore;
	}
	
	//resets the player's score to 0, name to null and frame to empty
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
