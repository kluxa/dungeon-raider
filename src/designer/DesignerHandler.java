package designer;

import controller.Controller;
import controller.MenuHandler;
import controller.Screen;
import game.Level;
import javafx.stage.Stage;

public class DesignerHandler {
	private MenuHandler menus;
	
	private Stage stage;
	private Screen loadScreen;
	private Screen designerScreen;
	
	private Controller designerController;
	
	public DesignerHandler(Stage stage, MenuHandler menus) {
		this.stage = stage;
		this.menus = menus;
	}
	
	public void initialize() {
		designerScreen = new LevelDesignerMainScreen(stage);
		designerController = new LevelDesignerController(stage, this);
		designerScreen.display(designerController);
	}
	
	
	
}
