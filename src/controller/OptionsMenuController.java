package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OptionsMenuController extends Controller {
	private MenuHandler menus;
	
	@FXML
	private AnchorPane display;
	@FXML
	private Button musicButton;
	@FXML
	private Button rebindKeysButton;
	@FXML
	private Button backButton;
	
	public OptionsMenuController(Stage s, MenuHandler menus) {
		super(s);
		this.menus = menus;
	}
	
	@FXML
	private void initialize() {
		EventHandler<MouseEvent> handler = MouseEvent::consume;
		display.addEventFilter(MouseEvent.ANY, handler);
	}
	
	@FXML
	private void handleMusicButton(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			System.out.println("Turning music ON/OFF");
		} else if (e.getCode() == KeyCode.ESCAPE) {
			returnToPreviousMenu();
		}
	}
	
	@FXML
	private void handleRebindKeysButton(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			menus.switchToKeyRebindMenu();
		} else if (e.getCode() == KeyCode.ESCAPE) {
			returnToPreviousMenu();
		}
	}
	
	@FXML
	private void handleBackButton(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			returnToPreviousMenu();
		} else if (e.getCode() == KeyCode.ESCAPE) {
			returnToPreviousMenu();
		}
	}
	
	private void returnToPreviousMenu() {
		menus.switchToMainMenu();
	}
	
}
