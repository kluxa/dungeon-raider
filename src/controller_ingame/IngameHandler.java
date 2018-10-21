package controller_ingame;

import dungeon.Maze;
import game.Level;

public interface IngameHandler {
	
	public Level getLevel();
	
	public Maze getMaze();
	
	public void beginGame();
	
	public void pauseGame();
	
	public void resumeGame();
	
	public void restartLevel();
	
	public void gameOver(String cause);
	
	public void gameWon();
	
	public void nextLevel();
	
	public void returnToPrevious();
	
}
