
public class Tree {

    Tree[] children;
    Character letter;
    boolean endOfWord;

    public Tree() {
        children = new Tree[26];
        letter = null;
        endOfWord = false;
    }

    public boolean isEmpty(){
        return letter==null;
    }

    void fill(int i){
        this.letter = (char) (i + 65);
    }


    public Tree get(int i) {
        return children[i];
    }

    public void set(int i) {
        if(children[i] == null){
            children[i] = new Tree();
        }

        children[i].fill(i);
    }

    @Override
    public String  toString() {
        return  String.valueOf(letter);
    }

    public void set(String cat) {
        int len = cat.length();

        if(len == 0){
            endOfWord = true;
        }
        else{
            int index = cat.charAt(0) - 65;
            set(index);
            String newCat = cat.substring(1);
            children[index].set(newCat);
        }
    }

    public boolean find(String cat) {
        int len = cat.length();

        if(len == 0){
            return endOfWord;
        }
        else{
            int index = cat.charAt(0) - 65;
            if (children[index] != null){
                String newCat = cat.substring(1);
                return children[index].find(newCat);
            }
            else
                return false;
        }
    }
}

