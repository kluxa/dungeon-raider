package controller;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Zone1SelectMenuController extends DungeonSelectMenuController {
	private MenuHandler menus;
	
	@FXML
	private AnchorPane display;
	@FXML
	private Button dungeon01Button;
	@FXML
	private Button dungeon02Button;
	@FXML
	private Button dungeon03Button;
	@FXML
	private Button dungeon04Button;
	@FXML
	private Button dungeon05Button;
	@FXML
	private Button dungeon06Button;
	@FXML
	private Button dungeon07Button;
	@FXML
	private Button dungeon08Button;
	@FXML
	private Button dungeon09Button;
	@FXML
	private Button dungeon10Button;
	
	private Button[][] buttons;
	
	private String[][] files = {
			{ "zone1/01", "zone1/02", "zone1/03", "zone1/04", "zone1/05" },
			{ "zone1/06", "zone1/07", "zone1/08", "zone1/09", "zone1/10" }
	};
	
	private int height  = 2;
	private int width   = 5;
	
	private int cursorX = 0;
	private int cursorY = 0;
	
	public Zone1SelectMenuController(Stage s, MenuHandler menus) {
		super(s, menus);
	}
	
	@FXML
	private void initialize() {
		display.addEventFilter(MouseEvent.ANY, e -> {
			e.consume();
		});
		
		cursorX = 0;
		cursorY = 0;
		
		Button[][] buttons = {
				{ dungeon01Button, dungeon02Button, dungeon03Button, dungeon04Button, dungeon05Button },
				{ dungeon06Button, dungeon07Button, dungeon08Button, dungeon09Button, dungeon10Button }
		};
		this.buttons = buttons;
	}
	
	@FXML
	private void handleKeyPress(KeyEvent e) {
		e.consume();
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.ESCAPE)) {
			goToPreviousScreen();
			
		} else if (k.equals(KeyCode.ENTER)) {
			menus.switchToPlayingDungeon(0,
					files[cursorY][cursorX]);
			
		} else if (k.equals(KeyCode.UP)) {
			moveCursorUp();
		} else if (k.equals(KeyCode.DOWN)) {
			moveCursorDown();
		} else if (k.equals(KeyCode.RIGHT)) {
			moveCursorRight();
		} else if (k.equals(KeyCode.LEFT)) {
			moveCursorLeft();
			
		}
	}
	
	private void moveCursorUp() {
		cursorY--;
		if (cursorY < 0) {
			cursorY += height;
		}
		buttons[cursorY][cursorX].requestFocus();
	}
	
	private void moveCursorDown() {
		cursorY++;
		if (cursorY >= height) {
			cursorY = (cursorY % height);
		}
		buttons[cursorY][cursorX].requestFocus();
	}
	
	private void moveCursorRight() {
		cursorX++;
		if (cursorX >= width) {
			cursorX = (cursorX % width);
		}
		buttons[cursorY][cursorX].requestFocus();
	}
	
	private void moveCursorLeft() {
		cursorX--;
		if (cursorX < 0) {
			cursorX += width;
		}
		buttons[cursorY][cursorX].requestFocus();
	}
	
	private void goToPreviousScreen() {
//		FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), display);
//		fadeOut.setFromValue(1.0);
//		fadeOut.setToValue(0.0);
//		fadeOut.play();
//		fadeOut.setOnFinished(e -> {
			menus.switchToZoneSelectMenu();
//		});
	}
}
