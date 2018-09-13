package enemies;

import dungeon.Direction;
import dungeon.Maze;
import dungeon.Tile;

public class NoMovement implements MovementPattern {

	@Override
	public Direction chooseMove(Tile currentTile, Maze maze) {
		// TODO Auto-generated method stub
		return null;
	}

}
