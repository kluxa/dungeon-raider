package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GameOverMenuController extends Controller {
	private PlayDungeon playDungeon;
	@FXML
	private Button restartButton;
	@FXML
	private Button returnButton;
	
	public GameOverMenuController(Stage s, PlayDungeon playDungeon) {
		super(s);
		this.playDungeon = playDungeon;
	}
	
	@FXML
	private void initialize() {
		
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
			playDungeon.returnToPrevious();
		}
	}
}
