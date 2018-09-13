package enemies;

import dungeon.Direction;
import dungeon.Maze;
import dungeon.Tile;

public interface MovementPattern {
	
	public Direction chooseMove(Tile currentTile, Maze maze);
	
}
