package controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Dungeon Raider");
		MainMenuScreen screen = new MainMenuScreen(primaryStage);
		Controller cc = new MainMenuController(primaryStage);
		screen.display(cc);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
