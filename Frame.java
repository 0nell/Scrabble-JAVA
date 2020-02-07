import java.util.ArrayList;

/*A class called Frame that:
oStores the letters that each player has in their frame
oAllows letters to be removed from a frame
oAllows a check to be made if letters are in the frame
oAllows a check to be made to see if the frame is empty
oAllows access to the letters in the frame
oAllows a frame to be refilled from the pool
oAllows a frame to be displayed */
public class Frame{
    ArrayList<Character> letters;

    /**
     * 
     */
    public Frame() {
        this.letters = new ArrayList<Character>(7);
        
    }

    /**
     * @return the letters
     */
    public ArrayList<Character> getLetters() {
        return letters;
    }

    /**
     * @param letters the letters to set
     */
    public void setLetters(ArrayList<Character> letters) {
        this.letters = letters;
    }
    public int getSize()
    {
        return letters.size();
    }
    public void addLetter(char l){
        letters.add(l);
    }

    public void removeLetter(char l){
        letters.remove(letters.indexOf(l));
    }
    public boolean checkForLetter(char l){
        return letters.contains(l);
    }
    public boolean checkEmpty(){
        return letters.isEmpty();
    }

    public void displayFrame(){
        for(int i = 0; i < letters.size(); i ++){
            System.out.print("|"+letters.get(i)+"|");
        }
        System.out.print("\n");
        
    } 



    

}