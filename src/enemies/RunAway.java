package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

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
	public Direction chooseMove(Square s, Maze maze, Direction oldMove) {
		// TODO Auto-generated method stub
		return Direction.DOWN;
	}

}
