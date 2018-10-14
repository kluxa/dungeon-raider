package dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

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
	private ArrayList<FloorSwitch> switches;
	
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
	
	public Square getPlayerLocation() {
		return player.getLocation();
	}
	
	public int getHeight () {
		return this.height;
	}
	
	public int getWidth () {
		return this.width;
	}
	
	// Only for testing
	public int getNumOfEntity(Entity e) {
		ArrayList<SolidEntity> entities = getAllSolidEntities();
		int count = 0;
		for (int i = 0; i < entities.size(); i++) {
			if (e.sameType(entities.get(i))) {
				count++;
			}
		}
		return count;
	}
	
	// Only for testing
	public boolean itemIsAt(Item i, int row, int col) {
		ArrayList<Item> items = squares[row][col].getItems();
		for (Item o: items) {
			if (i.sameType(o)) {
				return true;
			}
		}
		return false;
	}
	
	// Only for testing
	public int getNumEnemies() {
		int count = 0;
		for (Enemy e: enemies) {
			if (e.isAlive()) {
				count++;
			}
		}
		return count;
	}
	
	public int getNumTriggeredSwitches() {
		int count = 0;
		for (FloorSwitch f: switches) {
			if (f.isTriggered()) {
				count++;
			}
		}
		return count;
	}
	
	public boolean allSwitchesTriggered() {
		return (getNumTriggeredSwitches() == switches.size());
	}
	
	////////////////////////////////////////////////////////////////////
	// Maze Playing
	/**
	 * Prepares the maze for playing
	 */
	public void prepMaze() {
		// Adds all the enemies to a list to aid completion checking
		for (Entity e: getAllSolidEntities()) {
			if (e instanceof Enemy) {
				enemies.add((Enemy) e);
			}
		}
		
		// Adds all the floor switches to a list to aid completion
		// checking
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Tile t = squares[row][col].getTile();
				if (t instanceof FloorSwitch) {
					switches.add((FloorSwitch) t);
				}
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
	
	private ArrayList<SolidEntity> getAllSolidEntities() {
		ArrayList<SolidEntity> entities = new ArrayList<>();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				entities.addAll(squares[row][col].getOccupants());
			}
		}
		return entities;
	}
	
	public void updateEnemies() {
		for (Enemy e: enemies) {
			if (e.isAlive()) {
				e.makeMove(this);
			}
		}
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
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
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
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rep.length; i++) {
			sb.append(rep[i]);
			sb.append("\n");
		}
		return sb.toString();
	}
	
	////////////////////////////////////////////////////////////////////
	// Maze Searching - this is for enemies
	
	// Arrays to mark the location of obstacles
	private boolean[][] walls;
	private boolean[][] boulders;
	private boolean[][] doors;
	
	private boolean[][] wallsOrBoulders;
	private boolean[][] wallsBouldersOrDoors;
	
	/**
	 * Mark obstacles
	 */
	public void markObstacles() {
		walls = new boolean[height][width];
		boulders = new boolean[height][width];
		doors = new boolean[height][width];
		
		wallsOrBoulders = new boolean[height][width];
		wallsBouldersOrDoors = new boolean[height][width];
		
		Wall w = new Wall();
		Boulder b = new Boulder();
		Door d = new Door();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Entity e = squares[row][col].getCollidableOccupant();
				walls[row][col] = w.sameType(e);
				boulders[row][col] = b.sameType(e);
				doors[row][col] = d.sameType(e);
				
				wallsOrBoulders[row][col] = walls[row][col] || boulders[row][col];
				wallsBouldersOrDoors[row][col] = wallsOrBoulders[row][col] || doors[row][col];
			}
		}
	}
	
	/**
	 * 
	 * @param start
	 * @param goal
	 * @return
	 */
	public int[][] getDistances(Square src, Square s) {
		int[][] distances;
		distances = getDistancesBFS(src, wallsBouldersOrDoors);
		if (distances[s.getY()][s.getX()] != -1) return distances;
		distances = getDistancesBFS(src, wallsOrBoulders);
		if (distances[s.getY()][s.getX()] != -1) return distances;
		distances = getDistancesBFS(src, walls);
		return distances;
	}
	
	/**
	 * Tries to find the best move
	 * Tries to prevent 'oppressive' movement, such as the shuffle dance
	 * @preconditions the goal square should not contain an obstacle and
	 *                should not be the same as the start square
	 * @param start the start square
	 * @param goal the goal square
	 * @param obstacles an array of booleans indicating which squares contain
	 *                  obstacles, and hence should not be visited
	 * @return an array of distances from every square in the maze to the
	 *         target location.
	 */
	public int[][] getDistancesBFS(Square src,
			                       boolean[][] obstacles) {
		
		int[][] distances = new int[height][width];
		Direction[][] pred = new Direction[height][width];
		boolean[][] visited = new boolean[height][width];
		for (int row = 0; row < visited.length; row++){
			Arrays.fill(visited[row], false);
			Arrays.fill(distances[row], -1);
		}
		
		int distance = 0;
		distances[src.getY()][src.getX()] = 0;
		visited[src.getY()][src.getX()]= true;
		Queue<Square> q1 = new LinkedList<>();
		Queue<Square> q2 = new LinkedList<>();
		
		q1.add(src);
		while (!(q1.isEmpty()) || !(q2.isEmpty())) {
			Square s = q1.remove();
			int y = s.getY();
			int x = s.getX();
			distances[y][x] = distance;
			
			if (obstacles[y + 1][x] == false && visited[y + 1][x] == false) {
				visited[y + 1][x] = true;
				q2.add(squares[y + 1][x]);
				pred[y + 1][x] = Direction.UP;
			}
			if (obstacles[y - 1][x] == false && visited[y - 1][x] == false) {
				visited[y - 1][x] = true;
				q2.add(squares[y - 1][x]);
				pred[y - 1][x] = Direction.DOWN;
			}
			if (obstacles[y][x - 1] == false && visited[y][x - 1] == false) {
				visited[y][x - 1] = true;
				q2.add(squares[y][x - 1]);
				pred[y][x - 1] = Direction.RIGHT;
			}
			if (obstacles[y][x + 1] == false && visited[y][x + 1] == false) {
				visited[y][x + 1] = true;
				q2.add(squares[y][x + 1]);
				pred[y][x + 1] = Direction.LEFT;
			}
			if (q1.isEmpty()) {
				distance++;
				Queue<Square> temp = q1;
				q1 = q2;
				q2 = temp;
			}
		}
		return distances;
	}

}
