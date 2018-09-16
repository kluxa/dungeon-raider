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
	public Direction chooseMove(Square s, Maze maze, Direction lastMove) {
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
		int minDist = 1000000;
		int minIndex = 0;
		for (int i = 0; i < 4; i++) {
			adjDistances[i] = distances[ys[i]][xs[i]] * 10000;
			adjDistances[i] += ((ys[i] - src.getY()) * (ys[i] - src.getY()) +
					            (xs[i] - src.getX()) * (xs[i] - src.getX()));
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
