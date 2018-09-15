package dungeon;

import java.util.ArrayList;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Maze {
	private Square[][] squares;
	private int height;
	private int width;
	private Square start;
	private Player player;
	
	private ArrayList<Enemy> enemies;
	
	/**
	 * Create a maze of given height and width.
	 * @param height the height of the maze
	 * @param width the width of the maze
	 */
	public Maze(int height, int width) {
		this.height = height;
		this.width = width;
		squares = new Square[height][width];
		this.resetMaze();
	}
	
	/**
	 * Copy constructor
	 * @param maze the maze to be copied
	 */
	public Maze(Maze maze) {
		Maze copy = new Maze(height, width);
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				copy.getSquares()[row][col] = new Square(squares[row][col]);
			}
		}
	}
	
	/**
	 * Clears the entire maze of all entities and
	 * sets all tiles to normal paths.
	 */
	private void resetMaze() {
		enemies = new ArrayList<Enemy>();
		
		// Sets all squares to have the default path tile
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				Square s = new Square(i, j);
				s.setTile(new Path());
				squares[i][j] = s;
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////
	// Getters/Setters
	public Square[][] getSquares() {
		return squares;
	}
	
	public Square getStartSquare() {
		return start;
	}
	
	public void setStart(int row, int col) {
		start = squares[row][col];
	}
	
	public void setPlayer(Player p) {
		player = p;
	}
	
	public Square getSquare(int row, int col) {
		if (row >= 0 && row < height && col >= 0 && col < width) {
			return squares[row][col];
		}
		return null;
	}
	
	////////////////////////////////////////////////////////////////////
	// Maze Playing
	/**
	 * Prepares the maze for playing
	 */
	public void prepMaze() {
		for (Entity e: getAllEntities()) {
			if (e instanceof Enemy) {
				enemies.add((Enemy) e);
			}
		}
		
		// Connects all squares together, like a 2D linked list
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				squares[row][col].setUp(getSquare(row - 1, col));
				squares[row][col].setDown(getSquare(row + 1, col));
				squares[row][col].setRight(getSquare(row, col + 1));
				squares[row][col].setLeft(getSquare(row, col - 1));
			}
		}
	}
	
	private ArrayList<Entity> getAllEntities() {
		ArrayList<Entity> entities = new ArrayList<>();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				entities.addAll(squares[row][col].getOccupants());
			}
		}
		return entities;
	}
	
	////////////////////////////////////////////////////////////////////
	// Maze Building
	/**
	 * Places an entity at the given coordinates.
	 * Silently fails if there is an incompatible
	 * entity there.
	 * @param row the y-coordinate
	 * @param col the x-coordinate
	 * @param e the entity to be placed
	 */
	public void placeEntity(int row, int col, Entity e) {
		if (!allowedToPlace(e)) {
			
			return;
		}
		if (e instanceof Tile) {
			squares[row][col].setTile((Tile) e);
		} else if (e instanceof SolidEntity) {
			if (squares[row][col].getCollidableOccupant() == null) {
				squares[row][col].placeSolidEntity((SolidEntity) e);
				((SolidEntity) e).setLocation(squares[row][col]);
			}
		} else if (e instanceof Item) {
			squares[row][col].addItem((Item) e);
		}
	}
	
	public void clearTile(int row, int col) {
		squares[row][col].clear();
	}
	
	public ArrayList<Entity> getEntities(int row, int col) {
		ArrayList<Entity> entities = new ArrayList<>();
		entities.addAll(squares[row][col].getItems());
		entities.addAll(squares[row][col].getOccupants());
		return entities;
	}
	
	public boolean allowedToPlace(Entity newEntity) {
		if (newEntity instanceof Key || newEntity instanceof Door)
			if (containsEntity(newEntity))
				return false;
		return true;
	}
	
	private boolean containsEntity(Entity e) {
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				if (squares[row][col].containsEntity(e)) {
					return true;
				}
			}
		}
		return false;
	}
	
	////////////////////////////////////////////////////////////////////
	// Maze Printing
	/**
	 * 
	 * @return a char[][] representation of the
	 *         maze for printing to the terminal
	 */
	public char[][] toCharArray() {
		char[][] maze = new char[2 * height + 1][4 * width + 1];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				maze[2 * row + 1][4 * col + 1] = squares[row][col].getTile().toChar();
				for (SolidEntity e: squares[row][col].getOccupants()) {
					maze[2 * row + 1][4 * col + 2] = e.toChar();
				}
				for (Item e: squares[row][col].getItems()) {
					maze[2 * row + 1][4 * col + 3] = e.toChar();
				}
			}
		}
		for (int col = 0; col < maze[0].length; col += 4) {
			for (int row = 0; row < maze.length; row++) {
				maze[row][col] = '|';
			}
		}
		for (int row = 0; row < maze.length; row += 2) {
			for (int col = 0; col < maze[0].length; col++) {
				maze[row][col] = '-';
			}
		}
		
		return maze;
	}
	
	@Override
	public String toString() {
		char[][] rep = toCharArray();
		StringBuilder sb = new StringBuilder(1000);
		for (int i = 0; i < rep.length; i++) {
			sb.append(rep[i]);
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
