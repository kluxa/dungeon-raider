package enemies;

import dungeon.Direction;
import dungeon.Maze;
import dungeon.Tile;

/**
 * 
 * @author Kevin
 * This movement pattern is used by the coward if the
 * player gets close, and by all enemies if the player
 * is invincible due to the player consuming an
 * invincibility potion.
 * Enemies that are running away will try to make any
 * move that will get them further away from the
 * player.
 */
public class RunAway implements MovementPattern {
	
	@Override
	public Direction chooseMove(Tile currentTile, Maze maze) {
		// TODO Auto-generated method stub
		return Direction.DOWN;
	}

}
