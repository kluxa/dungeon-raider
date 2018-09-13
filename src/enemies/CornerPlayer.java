package enemies;

import dungeon.Direction;
import dungeon.Maze;
import dungeon.Tile;

/**
 * 
 * @author Kevin
 * This movement pattern is exclusive to the hound.
 * If there is a hunter near the player, the hound will
 * approach the tile on the side of the player opposite
 * the hunter. Otherwise, the hound will simply move
 * towards the player.
 */
public class CornerPlayer implements MovementPattern {

	@Override
	public Direction chooseMove(Tile currentTile, Maze maze) {
		// TODO Auto-generated method stub
		return Direction.DOWN;
	}

}
