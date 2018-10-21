package controller_menus;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public abstract class DungeonSelectMenuController extends Controller {
	protected MenuHandler menus;
	
	public DungeonSelectMenuController(Stage s, MenuHandler menus) {
		super(s);
		this.menus = menus;
	}
	
	public abstract boolean hasNextLevel();
	
	public abstract String nextLevel();
}
