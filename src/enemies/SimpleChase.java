package enemies;

import dungeon.Direction;
import dungeon.Maze;
import dungeon.Tile;

/**
 * 
 * @author Kevin
 * Enemies with this movement pattern will simply move
 * towards the player.
 */
public class SimpleChase implements MovementPattern {

	@Override
	public Direction chooseMove(Tile currentTile, Maze maze) {
		// TODO Auto-generated method stub
		return Direction.DOWN;
	}

}
