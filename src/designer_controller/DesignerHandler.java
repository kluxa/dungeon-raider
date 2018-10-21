package designer_controller;

import controller.Controller;
import controller.MenuHandler;
import controller.Screen;
import fileIO.LevelBuilder;
import game.Level;
import javafx.stage.Stage;

public class DesignerHandler {
	private MenuHandler menus;
	
	private Stage stage;
	private Screen designerScreen;
	
	private Controller designerController;
	
	public DesignerHandler(Stage stage, MenuHandler menus) {
		this.stage = stage;
		this.menus = menus;
	}
	
	public void initialize() {
		switchToInitScreen();
	}
	
	public void switchToInitScreen() {
		Screen initScreen = new LevelDesignerInitScreen(stage);
		Controller c = new LevelDesignerInitController(stage, this);
		initScreen.display(c);
	}
	
	public void switchToLoadScreen() {
		Screen loadScreen = new LevelDesignerLoadScreen(stage);
		Controller c = new LevelDesignerLoadController(stage, this);
		loadScreen.display(c);
	}
	
	public void switchToSizeScreen() {
		Screen sizeScreen = new LevelDesignerSizeScreen(stage);
		Controller c = new LevelDesignerSizeController(stage, this);
		sizeScreen.display(c);
	}
	
	public void loadLevel(String levelName, String pathName) {
		designerScreen = new LevelDesignerMainScreen(stage);
		Level level = LevelBuilder.makeLevel(pathName);
		
		designerController = new LevelDesignerMainController(stage, this, levelName, level);
		switchToMainScreen();
	}
	
	public void newCustomLevel(int height, int width) {
		designerScreen = new LevelDesignerMainScreen(stage);
		designerController = new LevelDesignerMainController(stage, this, height, width);
		switchToMainScreen();
	}
	
	public void switchToMainScreen() {
		designerScreen.display(designerController);
	}
	
	public void quit() {
		menus.switchToMainMenu();
	}
	
}
