package controller_menus;

import controller.Controller;
import controller.Screen;
import controller_designer.DesignerHandler;
import controller_ingame.PlayDungeon;
import dungeon.Maze;
import fileIO.LevelBuilder;
import fileIO.MazeToFileWriter;
import game.Level;
import game.MazeLoader;
import game.SimpleLevel;
import game.TestMaze;
import javafx.stage.Stage;

/**
 * 
 * @author Kevin
 * Handles the switching of screens before the
 * player enters a dungeon. This includes:
 * - Main menu
 * - Zone selection menu
 * - Level selection menu
 */
public class MenuHandler {
	private Stage stage;
	
	private Screen mainMenu;
	private Screen zoneSelectMenu;
	private Screen dungeonSelectMenu;
	
	private MainMenuController mainMenuController;
	private ZoneSelectMenuController zoneSelectMenuController;
	private DungeonSelectMenuController dungeonSelectMenuController;
	
	private String currLevel;
	
	public MenuHandler(Stage stage) {
		this.stage = stage;
		mainMenu = new MainMenuScreen(this.stage);
		mainMenuController = new MainMenuController(this.stage, this);
		
		zoneSelectMenu = new ZoneSelectMenuScreen(this.stage);
		zoneSelectMenuController = new ZoneSelectMenuController(this.stage, this);
	}
	
	public void initiate() {
		switchToMainMenu();
	}
	
	public void switchToMainMenu() {
		mainMenu.display(mainMenuController);
	}
	
	public void switchToZoneSelectMenu() {
		zoneSelectMenu.display(zoneSelectMenuController);
	}
	
	public void setDungeonSelectScreen(Screen s, DungeonSelectMenuController c) {
		dungeonSelectMenu = s;
		dungeonSelectMenuController = c;
	}
	
	public void switchToDungeonSelectMenu() {
		dungeonSelectMenu.display(dungeonSelectMenuController);
	}
	
	public void restartLevel() {
		switchToPlayingDungeon(currLevel);
	}
	
	public boolean hasNextLevel() {
		return dungeonSelectMenuController.hasNextLevel();
	}
	
	public void playNextLevel() {
		currLevel = dungeonSelectMenuController.nextLevel();
		switchToPlayingDungeon(currLevel);
	}
	
	public void switchToPlayingDungeon(String pathName) {
		currLevel = pathName;
		Level level = LevelBuilder.makeLevel(pathName);
		PlayDungeon playing = new PlayDungeon(stage, this, level);		
		playing.beginGame();
	}
	
	public void switchToLevelDesigner() {
		DesignerHandler designerHandler = new DesignerHandler(stage, this);
		designerHandler.initialize();
	}
	
	public void switchToOptionsMenu() {
		Screen optionsMenuScreen = new OptionsMenuScreen(stage);
		Controller optionsMenuController = new OptionsMenuController(stage, this);
		optionsMenuScreen.display(optionsMenuController);
	}
	
	public void switchToKeyRebindMenu() {
		Screen keyRebindMenuScreen = new KeyRebindMenuScreen(stage);
		Controller optionsMenuController = new KeyRebindMenuController(stage, this);
		keyRebindMenuScreen.display(optionsMenuController);
	}
}
