package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class KeyRebindMenuController extends Controller {
	private MenuHandler menus;
	
	@FXML
	private AnchorPane display;
	
	@FXML
	private Label helpMessage;
	
	@FXML
	private Button upKeyButton;
	@FXML
	private Button downKeyButton;
	@FXML
	private Button rightKeyButton;
	@FXML
	private Button leftKeyButton;
	@FXML
	private Button dropBombKeyButton;
	@FXML
	private Button fireArrowKeyButton;
	@FXML
	private Button backButton;
	
	private KeyCode upKey;
	private KeyCode downKey;
	private KeyCode rightKey;
	private KeyCode leftKey;
	private KeyCode dropBombKey;
	private KeyCode fireArrowKey;
	
	private KeyCode[] validKeys;
	
	private String originalText;
	private boolean keyPending;
	
	public KeyRebindMenuController(Stage s, MenuHandler menus) {
		super(s);
		this.menus = menus;
	}
	
	@FXML
	private void initialize() {
		helpMessage.setText("Press ENTER on a key binding to change it.");
		EventHandler<MouseEvent> handler = MouseEvent::consume;
		display.addEventFilter(MouseEvent.ANY, handler);
		readKeyBindings();
		
		KeyCode[] validKeys = {
				KeyCode.UP, KeyCode.DOWN, KeyCode.RIGHT, KeyCode.LEFT,
				
				KeyCode.Q, KeyCode.W, KeyCode.E, KeyCode.R, KeyCode.T, KeyCode.Y, KeyCode.U,
				KeyCode.I, KeyCode.O, KeyCode.P, KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.F,
				KeyCode.G, KeyCode.H, KeyCode.J, KeyCode.K, KeyCode.L, KeyCode.Z, KeyCode.X,
				KeyCode.C, KeyCode.V, KeyCode.B, KeyCode.N, KeyCode.M,
				
				KeyCode.BACK_QUOTE, KeyCode.MINUS, KeyCode.EQUALS, KeyCode.BACK_SPACE,
				KeyCode.TAB, KeyCode.OPEN_BRACKET, KeyCode.CLOSE_BRACKET, KeyCode.BACK_SLASH,
				KeyCode.SEMICOLON, KeyCode.QUOTE, KeyCode.COMMA, KeyCode.PERIOD, KeyCode.SLASH,
				KeyCode.SPACE,
				
				KeyCode.DIGIT1, KeyCode.DIGIT2, KeyCode.DIGIT3, KeyCode.DIGIT4, KeyCode.DIGIT5,
				KeyCode.DIGIT6, KeyCode.DIGIT7, KeyCode.DIGIT8, KeyCode.DIGIT9, KeyCode.DIGIT0,
		};
		this.validKeys = validKeys;
		
		display.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.TAB) e.consume(); 
		});
	}
	
	private void handleKeyPress(Button button, KeyEvent e) {
		KeyCode k = e.getCode();
		if (keyPending) {
			e.consume();
			if (validKey(k)) {
				if (k == KeyCode.ESCAPE) {
					button.setText(originalText);
				} else {
					if (button == upKeyButton)        upKey = k;
					if (button == downKeyButton)      downKey = k;
					if (button == rightKeyButton)     rightKey = k;
					if (button == leftKeyButton)      leftKey = k;
					if (button == dropBombKeyButton)  dropBombKey = k;
					if (button == fireArrowKeyButton) fireArrowKey = k;
					
					button.setText(k.toString());
				}
				helpMessage.setText("Press ENTER on a key binding to change it.");
			} else {
				button.setText(originalText);
				helpMessage.setText("Key is invalid or already in use.");
			}
			keyPending = false;
		} else if (k == KeyCode.ENTER) {
			keyPending = true;
			helpMessage.setText("Enter a new key, or press ESC to cancel.");
			originalText = button.getText();
			button.setText("???");
		}
	}
	
	private boolean validKey(KeyCode k) {
		if (k == upKey || k == downKey || k == rightKey || k == leftKey ||
				k == dropBombKey || k == fireArrowKey) {
			return false;
		}
		for (int i = 0; i < validKeys.length; i++) {
			if (k == validKeys[i]) {
				return true;
			}
		}
		return false;
	}
	
	@FXML
	private void handleUpKeyButton(KeyEvent e) {
		handleKeyPress(upKeyButton, e);
	}
	
	@FXML
	private void handleDownKeyButton(KeyEvent e) {
		handleKeyPress(downKeyButton, e);
	}
	
	@FXML
	private void handleRightKeyButton(KeyEvent e) {
		handleKeyPress(rightKeyButton, e);
	}
	
	@FXML
	private void handleLeftKeyButton(KeyEvent e) {
		handleKeyPress(leftKeyButton, e);
	}
	
	@FXML
	private void handleDropBombKeyButton(KeyEvent e) {
		handleKeyPress(dropBombKeyButton, e);
	}
	
	@FXML
	private void handleFireArrowKeyButton(KeyEvent e) {
		handleKeyPress(fireArrowKeyButton, e);
	}
	
	@FXML
	private void handleBackButton(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			saveKeyBindings();
			menus.switchToOptionsMenu();
		}
	}
	
	private void readKeyBindings() {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("src/game_files/bindings.txt"));
			while (sc.hasNextLine()) {
				readKeyBinding(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} finally {
			if (sc != null) sc.close();
		}
	}
	
	private void readKeyBinding(String line) {
		String[] pair = line.split(" ");
		KeyCode k = KeyCode.valueOf(pair[1]);
		switch (pair[0]) {
		case "Up":        upKey = k;        upKeyButton.setText(pair[1]);        break;
		case "Down":      downKey = k;      downKeyButton.setText(pair[1]);      break;
		case "Right":     rightKey = k;     rightKeyButton.setText(pair[1]);     break;
		case "Left":      leftKey = k;      leftKeyButton.setText(pair[1]);      break;
		case "DropBomb":  dropBombKey = k;  dropBombKeyButton.setText(pair[1]);  break;
		case "FireArrow": fireArrowKey = k; fireArrowKeyButton.setText(pair[1]); break;
		}
	}
	
	private void saveKeyBindings() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("src/game_files/bindings.txt");
			writer.println("Up " + upKey.toString());
			writer.println("Down " + downKey.toString());
			writer.println("Right " + rightKey.toString());
			writer.println("Left " + leftKey.toString());
			writer.println("DropBomb " + dropBombKey.toString());
			writer.println("FireArrow " + fireArrowKey.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} finally {
			if (writer != null) writer.close();
		}
	}
}
