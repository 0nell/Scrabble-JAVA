import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;




public class UI {
    int STEP = 0;
    Square[] frames = new Square[7];
    TextField textBox;
    Player currentPlayer;
    TilePane framePane;
    Frame frame;
    Label label;
    String[] text;
    Board board;
    Pool pool;
    Player[] players = new Player[2];
    int turn = 0;
    UI(){
        board = new Board();
        textBox = new TextField();
        players[0] = new Player();
        players[1] = new Player();

        textBox.setOnAction(e->{
            turn += parseInput(textBox.getText());
            STEP++;
            setFrame(players[turn%2].getFrame());
            textBox.clear();
        });
        label = new Label("Player 1 insert name: ");
        framePane  = new TilePane();
        currentPlayer = new Player();
        pool = new Pool();

        for(int j = 0; j < 7; j++) {
            frames[j] = new Square();
        }
        for(int j = 0; j < 7; j++) {
            framePane.getChildren().add(frames[j]);
            framePane.setTileAlignment(Pos.TOP_LEFT);
        }


    }

    

    public TilePane getFramePane() {
        return framePane;
    }


    public void setFrame(Frame frame) {
        this.frame = frame;
        for(int j = 0; j < frame.size(); j++) {
            frames[j].setText(frame.getTiles().get(j).toString());
        }
    }


    TilePane printBoard(){
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(15);
        tilePane.setPrefRows(15);
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                tilePane.getChildren().add(board.squares[i][j]);
                tilePane.setTileAlignment(Pos.TOP_LEFT);
            }
        }
        return tilePane;
    }

    GridPane printGame(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(15);
        gridPane.add(printBoard(), 0,0);
        gridPane.add(textBox, 1,4);
        gridPane.add(label, 1,0);
        gridPane.add(getFramePane(), 0,4);
        return gridPane;
    }


    int parseInput(String command){
        if(STEP == 0){
            label.setText("Player 2 insert name: ");
            players[0].setName(command);
            players[0].getFrame().refill(pool);
            return 0;
        }
        else if(STEP == 1){
            label.setText("Enter command " + players[turn%2].getName());
            players[1].setName(command);
            players[1].getFrame().refill(pool);
            setFrame(players[turn].getFrame());
            return 0;
        }
        else {

            text = command.split(" ", 0);
            int i = 0;

            switch(text[i]) {
                case "QUIT":
                    System.exit(0);
                case "PASS":
                    return 1;
                case "HELP":
                    label.setText("Display help message here");
                    return 0;
                case "EXCHANGE":
                    String letters = text[++i];
                    currentPlayer.getFrame().remove(letters);
                    currentPlayer.getFrame().refill(pool);
                    return 1;
                default:
                    try{
                    int x = Integer.parseInt(text[i++]);
                    int y = Integer.parseInt(text[i++]);
                    String direction = text[i++];
                    String word = text[i];
                    board.placeWord(players[turn], word, x, y, direction);
                    return 1;
                    } catch (Exception e){
                        System.out.println(e);
                    }
                    return  0;
            }



        }
    }
}
