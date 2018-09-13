package enemies;

import dungeon.Direction;
import dungeon.Maze;
import dungeon.Tile;


/**
 * 
 * @author Kevin
 * This movement pattern is exclusive to the strategist.
 * The strategist will move towards the item nearest to it.
 * If the strategist is on a tile with an item, it will move
 * towards the player instead.
 */
public class Strategic implements MovementPattern {

	@Override
	public Direction chooseMove(Tile currentTile, Maze maze) {
		// TODO Auto-generated method stub
		return Direction.DOWN;
	}

}
