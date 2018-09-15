package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

/**
 * 
 * @author Kevin
 * Enemies with this movement pattern will simply move
 * towards the player.
 */
public class SimpleChase implements MovementPattern {

	@Override
	public Direction chooseMove(Square s, Maze maze) {
		// TODO Auto-generated method stub
		return Direction.DOWN;
	}

}
