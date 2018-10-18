package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import game.SimpleLevel;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Dungeon Raider");
		MenuHandler intro = new MenuHandler(primaryStage);
		intro.initiate();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
