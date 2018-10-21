package controller_menus;

import java.util.concurrent.TimeUnit;

import controller.Controller;
import javafx.animation.FadeTransition;
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

public class ZoneSelectMenuController extends Controller {
	private MenuHandler menus;
	
	@FXML
	private AnchorPane display;
	@FXML
	private Button tutorialButton;
	@FXML
	private Button world1Button;
	@FXML
	private Button world2Button;
	@FXML
	private Button world3Button;
	
	public ZoneSelectMenuController(Stage s, MenuHandler menus) {
		super(s);
		this.menus = menus;
	}
	
	@FXML
	private void initialize() {
		EventHandler<MouseEvent> handler = MouseEvent::consume;
		display.addEventFilter(MouseEvent.ANY, handler);
	}
	
	@FXML
	private void handleTutorialButton(KeyEvent e) {
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.ESCAPE)) {
			exitToMainMenu();
		} else if (k.equals(KeyCode.ENTER)) {
			menus.setDungeonSelectScreen(new TutorialSelectMenuScreen(getStage()),
					new TutorialSelectMenuController(getStage(), menus));
			menus.switchToDungeonSelectMenu();
		}
	}
	
	@FXML
	private void handleZone1Button(KeyEvent e) {
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.ESCAPE)) {
			exitToMainMenu();
		} else if (k.equals(KeyCode.ENTER)) {
			menus.setDungeonSelectScreen(new Zone1SelectMenuScreen(getStage()),
					new Zone1SelectMenuController(getStage(), menus));
			menus.switchToDungeonSelectMenu();
		}
	}
	
	@FXML
	private void handleZone2Button(KeyEvent e) {
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.ESCAPE)) {
			exitToMainMenu();
		} else if (k.equals(KeyCode.ENTER)) {
			menus.setDungeonSelectScreen(new Zone2SelectMenuScreen(getStage()),
					new Zone2SelectMenuController(getStage(), menus));
			menus.switchToDungeonSelectMenu();
		}
	}
	
	@FXML
	private void handleZone3Button(KeyEvent e) {
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.ESCAPE)) {
			exitToMainMenu();
		} else if (k.equals(KeyCode.ENTER)) {
			menus.setDungeonSelectScreen(new Zone3SelectMenuScreen(getStage()),
					new Zone3SelectMenuController(getStage(), menus));
			menus.switchToDungeonSelectMenu();
		}
	}
	
	private void exitToMainMenu() {
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), display);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.play();
		fadeOut.setOnFinished(e -> {
			menus.switchToMainMenu();
			display.setOpacity(1.0);
		});
	}
	
}
