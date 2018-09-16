package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

public interface MovementPattern {
	
	public Direction chooseMove(Square s, Maze maze,
			                    Direction oldMove);
	
}
