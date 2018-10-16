package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DungeonCompleteMenuController extends Controller {
	private PlayDungeon playDungeon;
	@FXML
	private Button nextLevelButton;
	@FXML
	private Button restartButton;
	@FXML
	private Button returnButton;
	
	public DungeonCompleteMenuController(Stage s, PlayDungeon playDungeon) {
		super(s);
		this.playDungeon = playDungeon;
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleNextLevelButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			System.out.println("Next level");
		}
	}
	
	@FXML
	private void handleRestartButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			System.out.println("Restarting");
		}
	}
	
	@FXML
	private void handleReturnButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			System.out.println("Returning");
		}
	}
}
