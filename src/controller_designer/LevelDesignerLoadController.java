package controller_designer;

import java.io.File;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LevelDesignerLoadController extends Controller {
	private DesignerHandler designerHandler;
	
	@FXML
	private AnchorPane display;
	
	@FXML
	private Button button1;
	@FXML
	private Button button2;
	@FXML
	private Button button3;
	@FXML
	private Button button4;
	@FXML
	private Button button5;
	@FXML
	private Button backButton;
	
	@FXML
	private Label leftArrow;
	@FXML
	private Label rightArrow;
	
	private Button[] buttons;
	private File[] levels;
	private int index;
	
	public LevelDesignerLoadController(Stage s, DesignerHandler handler) {
		super(s);
		this.designerHandler = handler;
	}
	
	@FXML
	private void initialize() {
		index = 0;
		File folder = new File("src/game_files/levels/custom");
		levels = folder.listFiles(f -> !f.getName().contains(".temp"));
		
		Button[] buttons = {
				button1, button2, button3, button4, button5
		};
		this.buttons = buttons;
		
		display.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			KeyCode k = e.getCode();
			switch (k) {
			case TAB:    e.consume();            break;
			case LEFT:   handleLeft();           break;
			case RIGHT:  handleRight();          break;
			case ESCAPE: returnToPreviousMenu(); break;
			default:                             break;
			}
		});
		
		updateView();
	}
	
	/**
	 * Updates the levels shown on the screen
	 */
	private void updateView() {
		for (int i = 0; i < 5; i++) {
			if (index + i < levels.length) {
				buttons[i].setFocusTraversable(true);
				String name = levels[index + i].getName();
				buttons[i].setText(basename(name));
			} else {
				buttons[i].setFocusTraversable(false);
				buttons[i].setText("");
			}
		}
		
		leftArrow.setVisible(index > 0);
		rightArrow.setVisible(index + 5 < levels.length);
	}
	
	private String basename(String name) {
		if (name.contains(".")) {
			return name.substring(0, name.lastIndexOf("."));
		}
		return name;
	}
	
	private void handleLeft() {
		if (index > 0) {
			index -= 5;
			updateView();
		}
	}
	
	private void handleRight() {
		if (index + 5 < levels.length) {
			index += 5;
			updateView();
		}
	}
	
	@FXML
	private void handleButton1(KeyEvent e) {
		if (button1.isFocusTraversable() && (e.getCode() == KeyCode.ENTER)) {
			handlePress(button1);
		}
	}
	
	@FXML
	private void handleButton2(KeyEvent e) {
		if (button2.isFocusTraversable() && (e.getCode() == KeyCode.ENTER)) {
			handlePress(button2);
		}
	}
	
	@FXML
	private void handleButton3(KeyEvent e) {
		if (button3.isFocusTraversable() && (e.getCode() == KeyCode.ENTER)) {
			handlePress(button3);
		}
	}
	
	@FXML
	private void handleButton4(KeyEvent e) {
		if (button4.isFocusTraversable() && (e.getCode() == KeyCode.ENTER)) {
			handlePress(button4);
		}
	}
	
	@FXML
	private void handleButton5(KeyEvent e) {
		if (button5.isFocusTraversable() && (e.getCode() == KeyCode.ENTER)) {
			handlePress(button5);
		}
	}
	
	private void handlePress(Button button) {
		designerHandler.loadLevel(button.getText(),
				                  "./src/game_files/levels/custom/" +
				                  button.getText() + ".txt");
	}
	
	@FXML
	private void handleBackButton(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			returnToPreviousMenu();
		}
	}
	
	private void returnToPreviousMenu() {
		designerHandler.switchToInitScreen();
	}
}
