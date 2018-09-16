package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

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
	public Direction chooseMove(Square s, Maze maze, Direction oldMove) {
		// TODO Auto-generated method stub
		return Direction.DOWN;
	}
	
}
