package controller_ingame;

import controller.Controller;
import controller.Screen;
import controller_designer.DesignerHandler;
import controller_menus.MenuHandler;
import dungeon.Maze;
import game.Level;
import javafx.stage.Stage;

public class TestDungeon implements IngameHandler {
	private DesignerHandler designerHandler;
	
	private Stage stage;
	private Screen playDungeonScreen;
	
	private Level level;
	private Maze maze;
	
	public TestDungeon(Stage stage, DesignerHandler handler, Level level) {
		this.stage = stage;
		this.designerHandler = handler;
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
		Screen pauseScreen = new TestPauseMenuScreen(stage);
		Controller c = new PauseMenuController(stage, this);
		pauseScreen.display(c);
	}
	
	public void resumeGame() {
		Controller c = new PlayDungeonController(stage, this);
		playDungeonScreen.display(c);
	}
	
	public void restartLevel() {
		designerHandler.restartLevel();
	}
	
	public void gameOver(String cause) {
		Controller c = new GameOverMenuController(stage, this, cause);
		Screen s = new TestGameOverMenuScreen(stage);
		s.display(c);
	}
	
	public void gameWon() {
		Controller c = new DungeonCompleteMenuController(stage, this, false);
		Screen s = new TestDungeonCompleteMenuScreen(stage);
		s.display(c);
	}
	
	public void nextLevel() {
		// Do nothing
	}
	
	public void returnToPrevious() {
		designerHandler.switchToMainScreen();
	}
}
