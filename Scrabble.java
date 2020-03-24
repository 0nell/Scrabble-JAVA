import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scrabble extends Application {
	Stage window;
	Scene game;
	UI ui;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ui = new UI();
		window = primaryStage;
		game = new Scene(ui.printGame(), 650, 600);
		window.setScene(game);
		window.setTitle("Scrabble");
		window.setResizable(false);
		window.show();
	}

}