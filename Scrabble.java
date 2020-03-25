import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
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
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		game = new Scene(ui.printGame(), screenBounds.getWidth()/1.92, screenBounds.getHeight()/1.2);


		window.setScene(game);
		window.setTitle("Scrabble");

		window.setResizable(false);
		window.show();
	}

}