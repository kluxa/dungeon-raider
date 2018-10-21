package controller_menus;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public abstract class DungeonSelectMenuController extends Controller {
	protected MenuHandler handler;
	
	public DungeonSelectMenuController(Stage s, MenuHandler menus) {
		super(s);
		this.handler = menus;
	}
	
	/**
	 * 
	 * @return whether there is a next level in
	 *         the current 'zone'
	 */
	public abstract boolean hasNextLevel();
	
	/**
	 * 
	 * @return  the path of the file containing
	 *          the next level
	 */
	public abstract String nextLevel();
}
