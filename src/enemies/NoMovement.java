package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

public class NoMovement implements MovementPattern {

	@Override
	public Direction chooseMove(Square s, Maze maze, Direction oldMove) {
		return null;
	}

}
