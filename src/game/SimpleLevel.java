package game;

import java.util.ArrayList;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class SimpleLevel implements Level {
	private boolean isSimpleLevel;
	private Player player;
	private Maze maze;
	
	private SimpleLevel(Maze maze) {
		this.maze = maze;
		maze.prepMaze();
		player = new Player(maze);
		maze.setPlayer(player);
		isSimpleLevel = true;
	}
	
	public Maze getMaze() {
		return this.maze;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * This function is very
	 * pleasing to look at...
	 */
	private void update() {
		player.updateBombs();
		maze.markObstacles();
		maze.updateEnemies();
		player.updateState();
		System.out.println(showLevel());
	}
	
	
	/**
	 * Checks if the level has ended
	 * @return a boolean to indicate  if the level
	 *         has ended.
	 */
	@Override
	public boolean hasEnded() {
		return !player.isAlive() ||
				isComplete();
	}
	
	/**
	 * Checks if the player is alive
	 * @return a boolean to indicate if the player
	 *         is alive.
	 */
	public boolean playerIsAlive() {
		return player.isAlive();
	}
	
	/**
	 * Checks if the player has complete the level
	 * @return a boolean to indicate if the player
	 *         has completed the level
	 */
	@Override
	public boolean isComplete() {
		if (isSimpleLevel) {			
			return player.isOnExit();
		}
		return true;
	}
	
	/**
	 * Sets the isSimpleLevel attribute of a level
	 * to false, indicating that the level has
	 * special objectives.
	 */
	@Override
	public void setNotSimple() {
		isSimpleLevel = false;
	}
	
	////////////////////////////////////////////////////////////////////
	// Info Displaying
	
	/**
	 * 
	 * @return an ASCII representation of the level
	 */
	public String showLevel() {
		char[][] rep = maze.toCharArray();
		rep[2 * player.getY() + 1][4 * player.getX() + 2] = '@';
		StringBuilder sb = new StringBuilder();
		for (char[] row: rep) {
			sb.append(row); sb.append('\n');
		}
		return sb.toString();
	}
	
	public String showPlayer() {
		return player.toString();
	}
	
	////////////////////////////////////////////////////////////////////
	// Move Making
	
	/**
	* Plays out a turn with the given player move
	* @param the direction for the player to move
	*/
	public void move(Direction move) {
		System.out.printf("Moving %s\n",
		move.toString());
		player.move(move);
		update();
	}
	
	public void dropBomb() {
		if (player.hasItem(new UnlitBomb())) {
			System.out.println("Fire in the hole!");
			player.dropBomb();
			update();
		} else {
			System.out.println("You don't even have a bomb!");
			System.out.println("Try and do something else.");
		}
	}
	
	public void fireArrow(Direction move) {
		if (player.hasItem(new Arrow())) {
			System.out.printf("Firing an arrow %s\n",
			move.toString());
			player.fireArrow(move);
			update();
		} else {
			System.out.println("You don't even have an arrow!");
			System.out.println("Try and do something else.");
		}
	}
	
	////////////////////////////////////////////////////////////////////
	// Level Builder
	
	/**
	 * Level builder - makes applying decorators to a level
	 * easy. The decorators add on different checks for
	 * objective completion.
	 */
	public static class LevelBuilder {
		private Level level;
		
		public LevelBuilder(Maze maze) {
			level = new SimpleLevel(maze);
			System.out.println("Simple level");
		}
		
		public LevelBuilder collectTreasure(boolean bool) {
			if (bool) {
				System.out.println("You need to collect treasure");
				level = new CollectTreasureLevel(level);
			}
			return this;
		}
		
		public LevelBuilder triggerSwitches(boolean bool) {
			if (bool) {
				System.out.println("You need to trigger switches");
				level = new TriggerSwitchesLevel(level);
			}
			return this;
		}
		
		public LevelBuilder defeatEnemies(boolean bool) {
			if (bool) {
				System.out.println("You need to defeat enemies");
				level = new DefeatEnemiesLevel(level);
			}
			return this;
		}
		
		public Level build() {
			return level;
		}
	}
}
