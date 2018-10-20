package game;

import java.util.HashMap;

import dungeon.Direction;
import dungeon.Maze;
import player.Player;

public abstract class LevelDecorator implements Level {
	protected Level level;
	
	public LevelDecorator(Level level) {
		this.level = level;
		level.setNotSimple();
	}
	
	@Override
	public Maze getMaze() {
		return level.getMaze();
	}
	
	@Override
	public Player getPlayer() {
		return level.getPlayer();
	}
	
	/**
	 * Checks if the level has ended
	 * @return a boolean to indicate if the  level
	 *         has ended.
	 */
	@Override
	public boolean hasEnded() {
		return !playerIsAlive() ||
				isComplete();
	}
	
	/**
	 * Checks if the player is alive
	 * @return a boolean to indicate if the player
	 *         is alive.
	 */
	@Override
	public boolean playerIsAlive() {
		return level.playerIsAlive();
	}
	
	/**
	 * Check if the player has completed the level
	 * @return a boolean to indicate if the player
	 *         has completed the level
	 */
	@Override
	public boolean isComplete() {
		return level.isComplete();
	}
	
	/**
	 * Sets the isSimpleLevel attribute of a level
	 * to false, indicating that the level has
	 * special objectives.
	 */
	@Override
	public void setNotSimple() {
		level.setNotSimple();
	}
	
	//////////////////////////////////////////////
	// Move-Making
	
	/**
	 * Plays out a turn with the given player move
	 * @param the direction for the player to move
	 */
	@Override
	public void move(Direction move) {
		level.move(move);
	}
	
	/**
	 * Plays out a turn with the player dropping a
	 * bomb.
	 */
	@Override
	public void dropBomb() {
		level.dropBomb();
	}
	
	/**
	 * Plays out a turn with the  player firing an
	 * arrow in the given direction.
	 * @param move
	 */
	@Override
	public void fireArrow(Direction move) {
		level.fireArrow(move);
	}
}