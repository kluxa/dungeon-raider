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
	public Direction chooseMove(Square enemySqr, Maze maze, Direction oldMove) {
		Square playerSqr = maze.getPlayerLocation();
		int[][] distances = maze.getDistances(playerSqr, enemySqr);

		// Debugging...
//		for (int i = 0; i < distances.length; i++) {
//			System.out.print("|");
//			for (int j = 0; j < distances[0].length; j++) {
//				System.out.printf("%3d|", distances[i][j]);
//			}
//			System.out.print("\n");
//		}

		int enemy_y = enemySqr.getY();
		int enemy_x = enemySqr.getX();
		int ys[] = {enemy_y - 1, enemy_y + 1, enemy_y + 0, enemy_y + 0};
		int xs[] = {enemy_x + 0, enemy_x + 0, enemy_x + 1, enemy_x - 1};

		int[] adjDistances = new int[4];
		int maxDist = -1;
		int maxIndex = 0;
		for (int i = 0; i < 4; i++) {
			adjDistances[i] = distances[ys[i]][xs[i]];
			if (adjDistances[i] > maxDist && adjDistances[i] >= 0) {
				maxDist = adjDistances[i]; maxIndex = i;
			}
		}

		return Direction.intToDirection(maxIndex);
	}

}
