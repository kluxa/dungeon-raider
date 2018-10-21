package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

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
	public Direction chooseMove(Square s, Maze maze, Direction oldMove) {
		// find hunter
				Square hunterSqr = maze.getHunterLocation();
				
				// if there's no hunter, chase player
				if(hunterSqr == null) {
					MovementPattern chase = new SimpleChase();
					return chase.chooseMove(s, maze, oldMove);
				}
				
				// determine which side of the player the hunter is on
				Square playerSqr = maze.getPlayerLocation();
				int player_x = playerSqr.getX();
				int player_y = playerSqr.getY();
				
				int hunter_x = hunterSqr.getX();
				int hunter_y = hunterSqr.getY();
				
				Direction oppositeDirection = Direction.NO_MOVE;
				if(hunter_x > player_x) { // hunter is on right of player
					oppositeDirection = Direction.LEFT; // hound should move to left of player
				} else if (hunter_x < player_x) {
					oppositeDirection = Direction.RIGHT;
				} else if (hunter_y > player_y) {
					oppositeDirection = Direction.DOWN;
				} else if (hunter_y < player_y) {
					oppositeDirection = Direction.UP;
				} else {
					oppositeDirection = Direction.NO_MOVE;
				}
				
				int houndDistance = 2;
				int behind_x = player_x + oppositeDirection.getDX()*houndDistance;
				int behind_y = player_y + oppositeDirection.getDY()*houndDistance;
				
				// try find a square on the opposite side of the player
				Square houndTargetSqr = maze.getSquare(behind_y, behind_x);
				if(houndTargetSqr == null || houndTargetSqr.getCollidableOccupant() != null) {
					houndTargetSqr = playerSqr;
				}
				
				// find the next move to get to that square
				int[][] distances = maze.getDistances(houndTargetSqr, s);
		 		int y = s.getY();
				int x = s.getX();
				int ys[] = {y - 1, y + 1, y + 0, y + 0};
				int xs[] = {x + 0, x + 0, x + 1, x - 1};
		 		int[] adjDistances = new int[4];
				int minDist = 1000000;
				int minIndex = 0;
				for (int i = 0; i < 4; i++) {
					adjDistances[i] = distances[ys[i]][xs[i]] * 10000;
					adjDistances[i] += ((ys[i] - houndTargetSqr.getY()) * (ys[i] - houndTargetSqr.getY()) +
							(xs[i] - houndTargetSqr.getX()) * (xs[i] - houndTargetSqr.getX()));
					if (adjDistances[i] < minDist && adjDistances[i] >= 0) {
						minDist = adjDistances[i]; minIndex = i;
					}
				}
				if (oldMove != null && adjDistances[oldMove.toInt()] == minDist) {
					return oldMove;
				}
				return Direction.intToDirection(minIndex);
	}
	
}
