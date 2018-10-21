package designer_controller;

import java.io.File;

import controller.Controller;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelDesignerNameController extends Controller {
	private DesignerHandler designerHandler;
	
	@FXML
	private AnchorPane display;
	@FXML
	private TextField levelNameField;
	@FXML
	private Button okButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Label helpMessage;
	
	public LevelDesignerNameController(Stage s, DesignerHandler handler) {
		super(s);
		this.designerHandler = handler;
	}
	
	@FXML
	private void initialize() {
		display.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.TAB) {
				e.consume();
			} else if (e.getCode() == KeyCode.ESCAPE) {
				designerHandler.switchToMainScreen();
			}
		});
		
		levelNameField.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.TAB) {
				e.consume(); 
			} else if (e.getCode() == KeyCode.DOWN) {
				if (levelNameField.isFocused()) {
					okButton.requestFocus();
				}
			}
		});
		
		addTextLimiter(levelNameField, 15);
	}
	
	@FXML
	private void handleOkButton(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			String name = levelNameField.getText();
			if (validLevelName(name)) {
				designerHandler.saveNewLevel(name);
			} else {
				FadeTransition fadeOut = new FadeTransition(Duration.seconds(2.0), helpMessage);
				fadeOut.setFromValue(1.0);
				fadeOut.setToValue(0.0);
				fadeOut.play();
				fadeOut.setOnFinished(finished -> {
					helpMessage.setText("");
					helpMessage.setOpacity(1.0);
				});
			}
		}
	}
	
	@FXML
	private void handleCancelButton(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			designerHandler.switchToMainScreen();
		}
	}
	
	/**
	 * Limits the length of a text field
	 * Credit to https://stackoverflow.com/a/21978453/10366569
	 * @param tf
	 * @param maxLength
	 */
	public void addTextLimiter(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }
	    });
	}
	
	/**
	 * Validates the level name. Length limit is already imposed
	 * by the addTextLimiter
	 * @param name
	 * @return
	 */
	private boolean validLevelName(String name) {
		File folder = new File("src/game_files/levels/custom");
		File[] files = folder.listFiles();
		for (File f: files) {
			if (name.equals(basename(f.getName()))) {
				helpMessage.setText("A level with that name already exists.");
				return false;
			}
		}
		
		if (!name.matches("[a-zA-Z0-9]+")) {
			helpMessage.setText("Only alphanumeric characters allowed.");
			return false;
		}
		
		return true;
	}
	
	private String basename(String name) {
		if (name.contains(".")) {
			return name.substring(0, name.lastIndexOf("."));
		}
		return name;
	}
}
