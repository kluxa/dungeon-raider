package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Screen {
	private Scene scene;
	private Stage stage;
	private FXMLLoader fxmlLoader;
	
	public Screen(Stage stage, String file) {
		this.stage = stage;
		this.fxmlLoader = new FXMLLoader(getClass()
				                        .getClassLoader()
				                        .getResource(file));
		scene = null;
	}
	
	public void display(Controller c) {
		if (scene == null) {
			try {
				fxmlLoader.setController(c);
				Parent root = fxmlLoader.load();
				this.scene = new Scene(root);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		stage.setScene(scene);
		stage.show();
	}
}
