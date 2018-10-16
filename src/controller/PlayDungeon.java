package controller;

import game.Level;
import javafx.stage.Stage;

public class PlayDungeon {
	
	private Stage stage;
	private Screen pauseScreen;
	private Screen playDungeonScreen;
	
	public PlayDungeon(Stage stage, Level level) {
		this.stage = stage;
		this.pauseScreen = new PauseMenuScreen(stage);
		this.playDungeonScreen = new PlayDungeonScreen(stage, level);
	}
	
	public void pauseGame() {
		Controller c = new PauseMenuController(stage, this);
		pauseScreen.display(c);
	}
	
	public void resumeGame() {
		Controller c = new PlayDungeonController(stage, this);
		playDungeonScreen.display(c);
	}
	
	public void gameOver() {
		Controller c = new GameOverMenuController(stage, this);
		Screen s = new GameOverMenuScreen(stage);
		s.display(c);
	}
	
	public void gameWon() {
		Controller c = new DungeonCompleteMenuController(stage, this);
		Screen s = new DungeonCompleteMenuScreen(stage);
		s.display(c);
	}
	
	public void beginGame() {
		Controller c = new PlayDungeonController(stage, this);
		playDungeonScreen.display(c);
	}
	
	
}
