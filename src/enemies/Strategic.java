package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

/**
 * 
 * @author Kevin
 * This movement pattern is exclusive to the strategist.
 * The strategist will move towards where the player would
 * move if they kept walking in the same direction
 */
public class Strategic implements MovementPattern {
	
	@Override
	public Direction chooseMove(Square enemySquare, Maze maze, Direction lastMove) {
		// predict square player will move to next
		// 		get players last move
		Direction playersPreviousMove = maze.getPlayersLastMove();
		// 		get players current position
		Square playersCurrentPosition = maze.getPlayerLocation();
		int player_x = playersCurrentPosition.getX();
		int player_y = playersCurrentPosition.getY();

		// 		calculate co-ords of square stepsAhead moves
		int stepsAhead = 2;

		int predicted_x = player_x + playersPreviousMove.getDX()*stepsAhead;
		int predicted_y = player_y + playersPreviousMove.getDY()*stepsAhead;

		Square predictedPlayerSquare = maze.getSquare(predicted_y, predicted_x);

		// 		check predicted square is inside map and can be moved to, otherwise chase player
		if (predictedPlayerSquare == null || predictedPlayerSquare.getCollidableOccupant() != null){
			predicted_x = player_x;
			predicted_y = player_y;
			predictedPlayerSquare = maze.getSquare(predicted_y, predicted_x);
		}

		// move towards predicted
//		MovementPattern simpleChase = new SimpleChase();
////		return simpleChase.chooseMove(predictedPlayerSquare, maze, oldMove);
//		return simpleChase.chooseMove(playersCurrentPosition, maze, oldMove);
		int[][] distances = maze.getDistances(predictedPlayerSquare, enemySquare);

		int y = enemySquare.getY();
		int x = enemySquare.getX();
		int ys[] = {y - 1, y + 1, y + 0, y + 0};
		int xs[] = {x + 0, x + 0, x + 1, x - 1};

		int[] adjDistances = new int[4];
		int minDist = 1000000;
		int minIndex = 0;
		for (int i = 0; i < 4; i++) {
			adjDistances[i] = distances[ys[i]][xs[i]] * 10000;
			adjDistances[i] += ((ys[i] - predictedPlayerSquare.getY()) * (ys[i] - predictedPlayerSquare.getY()) +
					(xs[i] - predictedPlayerSquare.getX()) * (xs[i] - predictedPlayerSquare.getX()));
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
