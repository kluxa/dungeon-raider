package controller_ingame;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GameOverMenuController extends Controller {
	private IngameHandler handler;
	@FXML
	private Button restartButton;
	@FXML
	private Button returnButton;
	@FXML
	private Label causeOfDeath;
	
	private String cause;
	
	public GameOverMenuController(Stage s,
			                      IngameHandler handler,
			                      String causeOfDeath) {
		super(s);
		this.handler = handler;
		this.cause = causeOfDeath;
	}
	
	@FXML
	private void initialize() {
		causeOfDeath.setText("Killed by: " + cause);
	}
	
	@FXML
	private void handleRestartButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			handler.restartLevel();
		}
	}
	
	@FXML
	private void handleReturnButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			handler.returnToPrevious();
		}
	}
}
