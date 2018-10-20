package controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenuController extends Controller {
	MenuHandler menus;
	@FXML
	private AnchorPane display;
	@FXML
	private Button playGameButton;
	@FXML
	private Button levelDesignerButton;
	@FXML
	private Button optionsButton;
	@FXML
	private Button quitButton;
	
	public MainMenuController(Stage s, MenuHandler menus) {
		super(s);
		this.menus = menus;
	}
	
	@FXML
	private void initialize() {
		EventHandler<MouseEvent> handler = MouseEvent::consume;
		display.addEventFilter(MouseEvent.ANY, handler);
	}
	
	@FXML
	private void handlePlayGameButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			menus.switchToZoneSelectMenu();
		}
	}
	
	@FXML
	private void handleLevelDesignerButton(KeyEvent e) {
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.ENTER)) {
			menus.switchToLevelDesigner();
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
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), display);
			fadeOut.setFromValue(1.0);
			fadeOut.setToValue(0.0);
			fadeOut.play();
			fadeOut.setOnFinished(finished -> {
				System.exit(0);
			});
		}
	}
}
