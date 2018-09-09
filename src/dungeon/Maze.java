package dungeon;

import java.util.ArrayList;

public class Maze {
	private Tile[][] grid;
	private int height;
	private int width;
	private Tile start;
	
	private ArrayList<LivingEntity> enemies;
	private ArrayList<NonLivingEntity> things;
	
	/**
	 * Create a maze of given height and width.
	 * Sets the border of the maze to walls.
	 * @param height the height of the maze
	 * @param width the width of the maze
	 */
	public Maze(int height, int width) {
		this.height = height;
		this.width = width;
		grid = new Tile[height][width];
		this.resetMaze();
	}
	
	private void resetMaze() {
		enemies = new ArrayList<LivingEntity>();
		things = new ArrayList<NonLivingEntity>();
		
		// Sets all tiles to normal paths
		for (int i = 0; i < this.height; i++)
			for (int j = 0; j < this.width; j++)
				grid[i][j] = new Path(j, i);
	}
	
	public Tile getStartTile() {
		return start;
	}
	
	public void setStart(int row, int col) {
		grid[row][col] = new Path(row, col);
		start = grid[row][col];
	}
	
	public void setTile(int row, int col, Tile t) {
		grid[row][col] = t;
	}
	
	public void addEntity(Entity e) {
		Tile t = e.getLocation();
		if (e instanceof LivingEntity) {
			enemies.add((Enemy) e);
		} else {
			things.add((NonLivingEntity) e);
		}
		t.arrive(e);
	}
	
	public Tile getTile(int row, int col) {
		return grid[row][col];
	}
	
	public void addItem(int row, int col, Item i) {
		grid[row][col].deposit(i);
	}
	
	/**
	 * 
	 * @param p the player
	 * @return an string representation of the
	 *         maze for printing to the terminal
	 */
	
	public char[][] showMaze() {
		char[][] maze = new char[2 * height + 1][4 * width + 1];
		for (Entity e: things) {
			maze[2 * e.getY() + 1][4 * e.getX() + 2] = e.toChar();
		}
		for (Entity e: enemies) {
			maze[2 * e.getY() + 1][4 * e.getX() + 2] = e.toChar();
		}
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				maze[2 * row + 1][4 * col + 1] = grid[row][col].toChar();
				for (Item i: grid[row][col].getItems()) {
					maze[2 * row + 1][4 * col + 3] = i.toChar();
				}
			}
		}
		
		return maze;
	}
	
	public void moveEntity(Entity entity, Direction move) {
		Tile oldTile = entity.getLocation();
		Tile newTile = this.getTile(entity.getY() + move.getDY(),
				                    entity.getX() + move.getDX());
		Entity e = getOccupant(newTile);
		if (e != null) {
			e.collide(entity);
		} else {
			oldTile.depart(entity);
			newTile.arrive(entity);
		}
	}
	
	public Entity getOccupant(Tile t) {
		for (Entity e: enemies) {
			if (t.equals(e.getLocation()) && e.isCollidable()) {
				return e;
			}
		}
		for (Entity e: things) {
			if (t.equals(e.getLocation()) && e.isCollidable()) {
				return e;
			}
		}
		return null;
	}
	
	public void cleanUp() {
		enemies.removeIf(e-> e.isAlive() == false);
		things.removeIf(e-> e.getLocation() == null);
	}
}
