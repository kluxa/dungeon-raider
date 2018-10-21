package controller_ingame;

import controller.Controller;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PauseMenuController extends Controller {
	private IngameHandler dungeonPlayer;
	
	@FXML
	private AnchorPane display;
	@FXML
	private Button resumeButton;
	@FXML
	private Button restartButton;
	@FXML
	private Button returnButton;
	
	public PauseMenuController(Stage s, IngameHandler dungeonPlayer) {
		super(s);
		this.dungeonPlayer = dungeonPlayer;
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleResumeButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			dungeonPlayer.resumeGame();
		}
	}
	
	@FXML
	private void handleRestartButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			dungeonPlayer.restartLevel();
		}
	}
	
	@FXML
	private void handleReturnButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), display);
			fadeOut.setFromValue(1.0);
			fadeOut.setToValue(0.0);
			fadeOut.play();
			fadeOut.setOnFinished(finished -> {
				dungeonPlayer.returnToPrevious();
			});
		}
	}
}
