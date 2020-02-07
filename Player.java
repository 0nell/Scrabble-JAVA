

public class Player {

	private String name;
	private int score = 0;
	Frame frame;
	
	
	public Player(String name, Frame frame) {
		super();
		this.name = name;
		this.frame = frame;
	}

	
	//Allows display of player's name
	public String getName() {
		if (name != null) {
			return name;
		}
		else return "";
	}


	//Allows player name to be set
	//If the first character is a space or new line reject it
	public void setName(String name) {
		if (Character.isWhitespace(name.charAt(0))) {
			throw new IllegalArgumentException();
		}
		else this.name = name;
	}


	//Allows access to the score
	public int getScore() {
		return score;
	}


	//Allows score to be increased
	public void increaseScore(int plusScore) {
		this.score = this.score + plusScore;
	}
	
	//Allows data reset
	public void reset() {
		score = 0;
		name = null;
		//empty frame
		int size = frame.getSize();
		for(int i = 0; i < size; i++){
			if(frame.contains(frame.letters.get(0)))
				frame.removeLetter(frame.letters.get(0));
		}
	}
	
	
	//displays the player's frame
	public void getFrame() {
		frame.displayFrame();
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "The player's name is: " + getName();
	}

		
	}
