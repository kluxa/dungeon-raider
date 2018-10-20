package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GameOverMenuController extends Controller {
	private PlayDungeon playDungeon;
	@FXML
	private Button restartButton;
	@FXML
	private Button returnButton;
	@FXML
	private Label causeOfDeath;
	
	private String cause;
	
	public GameOverMenuController(Stage s,
			                      PlayDungeon playDungeon,
			                      String causeOfDeath) {
		super(s);
		this.playDungeon = playDungeon;
		this.cause = causeOfDeath;
	}
	
	@FXML
	private void initialize() {
		causeOfDeath.setText("Killed by: " + cause);
	}
	
	@FXML
	private void handleRestartButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			playDungeon.restartLevel();
		}
	}
	
	@FXML
	private void handleReturnButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			playDungeon.returnToPrevious();
		}
	}
}
