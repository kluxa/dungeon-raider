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

public class Zone2SelectMenuController extends Controller {
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
			{ "zone2/01", "zone2/02", "zone2/03", "zone2/04", "zone2/05" },
			{ "zone2/06", "zone2/07", "zone2/08", "zone2/09", "zone2/10" }
	};
	
	private int height  = 2;
	private int width   = 5;
	
	private int cursorX = 0;
	private int cursorY = 0;
	
	public Zone2SelectMenuController(Stage s, MenuHandler menus) {
		super(s);
		this.menus = menus;
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
			PlayDungeon d = new PlayDungeon(getStage(), null);
			d.beginGame();
			
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
