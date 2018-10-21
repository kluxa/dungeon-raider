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
	public Direction chooseMove(Square enemySquare, Maze maze, Direction lastMove) {
		Square playerSquare = maze.getPlayerLocation();
		int[][] distances = maze.getDistances(playerSquare, enemySquare);

		int y = enemySquare.getY();
		int x = enemySquare.getX();
		int ys[] = {y - 1, y + 1, y + 0, y + 0};
		int xs[] = {x + 0, x + 0, x + 1, x - 1};

		int[] adjDistances = new int[4];
		int minDist = 1000000;
		int minIndex = 0;
		for (int i = 0; i < 4; i++) {
			adjDistances[i] = distances[ys[i]][xs[i]] * 10000;
			adjDistances[i] += ((ys[i] - playerSquare.getY()) * (ys[i] - playerSquare.getY()) +
					            (xs[i] - playerSquare.getX()) * (xs[i] - playerSquare.getX()));
			if (adjDistances[i] < minDist && adjDistances[i] >= 0) {
				minDist = adjDistances[i]; minIndex = i;
			}
		}
		if (lastMove != null && adjDistances[lastMove.toInt()] == minDist) {
			return lastMove;
		}
		return Direction.intToDirection(minIndex);
	}

}
