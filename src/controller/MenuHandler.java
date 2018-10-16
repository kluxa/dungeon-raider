package controller;

import game.Level;
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
	Stage stage;
	
	Screen mainMenu;
	Screen zoneSelectMenu;
	Screen dungeonSelectMenu;
	
	Controller mainMenuController;
	Controller zoneSelectMenuController;
	Controller dungeonSelectMenuController;
	
	public MenuHandler(Stage stage) {
		this.stage = stage;
		mainMenu = new MainMenuScreen(this.stage);
		mainMenuController = new MainMenuController(this.stage, this);
		
		zoneSelectMenu = new ZoneSelectMenuScreen(this.stage);
		zoneSelectMenuController = new ZoneSelectMenuController(this.stage, this);
	}
	
	public void initiate() {
		mainMenu.display(mainMenuController);
	}
	
	public void switchToMainMenu() {
		mainMenu.display(mainMenuController);
	}
	
	public void switchToZoneSelectMenu() {
		zoneSelectMenu.display(zoneSelectMenuController);
	}
	
	public void setDungeonSelectScreen(Screen s, Controller c) {
		dungeonSelectMenu = s;
		dungeonSelectMenuController = c;
	}
	
	public void switchToDungeonSelectMenu() {
		dungeonSelectMenu.display(dungeonSelectMenuController);
	}
	
	public void switchToPlayingDungeon(int zone, String name) {
		Level level = new Level(TestMaze.LEVEL16);
		PlayDungeon playing = new PlayDungeon(stage, this, level);
		playing.beginGame();
	}
}
