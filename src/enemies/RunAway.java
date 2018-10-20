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
		Square src = maze.getPlayerLocation();
		int[][] distances = maze.getDistances(src, s);

		// Debugging...
//		for (int i = 0; i < distances.length; i++) {
//			System.out.print("|");
//			for (int j = 0; j < distances[0].length; j++) {
//				System.out.printf("%3d|", distances[i][j]);
//			}
//			System.out.print("\n");
//		}

		int y = s.getY();
		int x = s.getX();
		int ys[] = {y - 1, y + 1, y + 0, y + 0};
		int xs[] = {x + 0, x + 0, x + 1, x - 1};

		int[] adjDistances = new int[4];
		int maxDist = 1000000;
		int maxIndex = 0;
		for (int i = 0; i < 4; i++) {
			adjDistances[i] = distances[ys[i]][xs[i]] * 10000;
			adjDistances[i] += ((ys[i] - src.getY()) * (ys[i] - src.getY()) +
					(xs[i] - src.getX()) * (xs[i] - src.getX()));
			if (adjDistances[i] > maxDist && adjDistances[i] >= 0) {
				maxDist = adjDistances[i]; maxIndex = i;
			}
		}
		if (oldMove != null && adjDistances[oldMove.toInt()] == maxDist) {
			return oldMove;
		}
		return Direction.intToDirection(maxIndex);
	}

}
