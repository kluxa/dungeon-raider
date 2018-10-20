package game;

import java.util.ArrayList;
import java.util.HashMap;

import dungeon.Direction;
import dungeon.Maze;
import items.Arrow;
import items.UnlitBomb;
import player.Player;

public interface Level {
	
	public Maze getMaze();
	public Player getPlayer();
	
	/**
	 * Checks if the level has ended
	 * @return a boolean to indicate  if the level
	 *         has ended.
	 */
	public boolean hasEnded();
	
	/**
	 * Checks if the player is alive
	 * @return a boolean to indicate if the player
	 *         is alive.
	 */
	public boolean playerIsAlive();
	
	/**
	 * Adds the objectives for a given  level into
	 * an ArrayList.
	 */
	public void getObjective(ArrayList<String> objs);
	
	/**
	 * Stores the progress value of each objective
	 * in a hashmap. For example {"treasure" => 3}
	 * indicates that there are three treasures
	 * remaining in the level.
	 */
	public void getProgress(HashMap<String, Integer> values);
	
	/**
	 * Checks if the player has complete the level
	 * @return a boolean to indicate if the player
	 *         has completed the level
	 */
	public boolean isComplete();
	
	/**
	 * Sets the isSimpleLevel attribute of a level
	 * to false, indicating that the level has
	 * special objectives.
	 */
	public void setNotSimple();
	
	//////////////////////////////////////////////
	// Move-making
	
	/**
	 * Plays out a turn with the given player move
	 * @param the direction for the player to move
	 */
	public void move(Direction move);
	
	/**
	 * Plays out a turn with the player dropping a
	 * bomb.
	 */
	public void dropBomb();
	
	/**
	 * Plays out a turn with the  player firing an
	 * arrow in the given direction.
	 * @param move
	 */
	public void fireArrow(Direction move);
}
