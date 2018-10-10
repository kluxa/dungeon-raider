package controller;

import javafx.stage.Stage;

public abstract class Controller {
	private Stage stage;
	
	public Controller(Stage s) {
		this.stage = s;
	}
	
	public Stage getStage() {
		return stage;
	}
}
