package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuScreen {
	private Stage stage;
	private FXMLLoader fxmlLoader;
	
	public MainMenuScreen(Stage stage) {
		this.stage = stage;
		this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/MainMenu.fxml"));
	}
	
	public void display(Controller cc) {
		fxmlLoader.setController(cc);
		
		try {
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
