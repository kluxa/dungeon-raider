package controller_ingame;

import controller.Controller;
import controller.Screen;
import controller_menus.MenuHandler;
import dungeon.Maze;
import game.Level;
import game.SimpleLevel;
import javafx.stage.Stage;

public class PlayDungeon implements IngameHandler {
	private MenuHandler menuHandler;
	
	private Stage stage;
	private Screen playDungeonScreen;
	
	private Level level;
	private Maze maze;
	
	public PlayDungeon(Stage stage, MenuHandler handler, Level level) {
		this.stage = stage;
		this.menuHandler = handler;
		this.playDungeonScreen = new PlayDungeonScreen(stage);
		this.maze = level.getMaze();
		this.level = level;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	public void beginGame() {
		Controller c = new PlayDungeonController(stage, this);
		playDungeonScreen.display(c);
	}
	
	public void pauseGame() {
		Screen pauseScreen = new PlayPauseMenuScreen(stage);
		Controller c = new PauseMenuController(stage, this);
		pauseScreen.display(c);
	}
	
	public void resumeGame() {
		Controller c = new PlayDungeonController(stage, this);
		playDungeonScreen.display(c);
	}
	
	public void restartLevel() {
		menuHandler.restartLevel();
	}
	
	public void gameOver(String cause) {
		Controller c = new GameOverMenuController(stage, this, cause);
		Screen s = new PlayGameOverMenuScreen(stage);
		s.display(c);
	}
	
	public void gameWon() {
		boolean hasNextLevel = menuHandler.hasNextLevel();
		Controller c = new DungeonCompleteMenuController(stage, this, hasNextLevel);
		Screen s = new PlayDungeonCompleteMenuScreen(stage);
		s.display(c);
	}
	
	public void nextLevel() {
		menuHandler.playNextLevel();
	}
	
	public void returnToPrevious() {
		menuHandler.switchToDungeonSelectMenu();
	}
}
