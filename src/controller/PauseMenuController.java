package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class PauseMenuController extends Controller {
	private PlayDungeon playDungeon;
	
	@FXML
	private Button resumeButton;
	@FXML
	private Button restartButton;
	@FXML
	private Button returnButton;
	
	public PauseMenuController(Stage s, PlayDungeon playDungeon) {
		super(s);
		this.playDungeon = playDungeon;
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleResumeButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			playDungeon.resumeGame();
		}
	}
	
	@FXML
	private void handleRestartButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			System.out.println("Restarting");
			// playDungeon.restart();
		}
	}
	
	@FXML
	private void handleReturnButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			System.out.println("Returning");
			// playDungeon.returnToPrevious();
		}
	}
}
