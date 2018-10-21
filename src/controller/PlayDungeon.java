package controller;

import dungeon.Maze;
import game.Level;
import game.SimpleLevel;
import javafx.stage.Stage;

public class PlayDungeon {
	private MenuHandler menus;
	
	private Stage stage;
	private Screen pauseScreen;
	private Screen playDungeonScreen;
	
	private Level level;
	private Maze maze;
	
	public PlayDungeon(Stage stage, MenuHandler menus, Level level) {
		this.stage = stage;
		this.menus = menus;
		this.pauseScreen = new PauseMenuScreen(stage);
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
	
	public void pauseGame() {
		Controller c = new PauseMenuController(stage, this);
		pauseScreen.display(c);
	}
	
	public void resumeGame() {
		Controller c = new PlayDungeonController(stage, this);
		playDungeonScreen.display(c);
	}
	
	public void restartLevel() {
		menus.restartLevel();
	}
	
	public void gameOver(String cause) {
		Controller c = new GameOverMenuController(stage, this, cause);
		Screen s = new GameOverMenuScreen(stage);
		s.display(c);
	}
	
	public void gameWon() {
		boolean hasNextLevel = menus.hasNextLevel();
		Controller c = new DungeonCompleteMenuController(stage, this, hasNextLevel);
		Screen s = new DungeonCompleteMenuScreen(stage);
		s.display(c);
	}
	
	public void beginGame() {
		Controller c = new PlayDungeonController(stage, this);
		playDungeonScreen.display(c);
	}
	
	public void nextLevel() {
		menus.playNextLevel();
	}
	
	public void returnToPrevious() {
		menus.switchToDungeonSelectMenu();
	}
}
