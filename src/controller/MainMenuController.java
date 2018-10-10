package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainMenuController extends Controller {
	@FXML
	private Button playGameButton;
	@FXML
	private Button levelDesignerButton;
	@FXML
	private Button optionsButton;
	@FXML
	private Button quitButton;
	
	public MainMenuController(Stage s) {
		super(s);
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handlePlayGameButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			System.out.println("Play Game button pressed");			
		}
	}
	
	@FXML
	private void handleLevelDesignerButton(KeyEvent e) {
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.ENTER)) {
			System.out.println("Level Designer button pressed");			
		}
	}
	
	@FXML
	private void handleOptionsButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			System.out.println("Options button pressed");			
		}
	}
	
	@FXML
	private void handleQuitButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			System.exit(0);		
		}
	}
}
