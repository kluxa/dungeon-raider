package designer_controller;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LevelDesignerSizeController extends Controller {
	private DesignerHandler designerHandler;
	
	@FXML
	private AnchorPane display;
	
	@FXML
	private Slider heightSlider;
	@FXML
	private Slider widthSlider;
	@FXML
	private Button okButton;
	@FXML
	private Button cancelButton;
	
	@FXML
	private Label height;
	@FXML
	private Label width;
	
	public LevelDesignerSizeController(Stage s, DesignerHandler designerHandler) {
		super(s);
		this.designerHandler = designerHandler;
	}
	
	@FXML
	private void initialize() {
		display.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				returnToPreviousMenu();
			} else if (e.getCode() == KeyCode.TAB) {
				e.consume();
			}
		});
	}
	
	@FXML
	private void handleHeightSlider(KeyEvent e) {
		if (e.getCode() == KeyCode.RIGHT) {
			height.setText(String.valueOf(Math.min((int)heightSlider.getValue() + 1, 40)));
		} else if (e.getCode() == KeyCode.LEFT) {
			height.setText(String.valueOf(Math.max((int)heightSlider.getValue() - 1,  5)));
		}
	}
	
	@FXML
	private void handleWidthSlider(KeyEvent e) {
		if (e.getCode() == KeyCode.RIGHT) {
			width.setText(String.valueOf(Math.min((int)widthSlider.getValue() + 1, 40)));
		} else if (e.getCode() == KeyCode.LEFT) {
			width.setText(String.valueOf(Math.max((int)widthSlider.getValue() - 1,  5)));
		}
	}
	
	@FXML
	private void handleOkButton(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			designerHandler.newCustomLevel((int)heightSlider.getValue(),
					                       (int)widthSlider.getValue());
		}
	}
	
	@FXML
	private void handleCancelButton(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			returnToPreviousMenu();
		}
	}
	
	private void returnToPreviousMenu() {
		designerHandler.switchToInitScreen();
	}
}
