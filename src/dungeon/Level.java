package dungeon;

public class Level {
	private Player player;
	private Maze maze;
	
	public Level(SampleMaze sampleMaze) {
		MazeReader reader = new MazeReader();
		maze = reader.readMaze(sampleMaze);
		player = new Player(maze);
	}
	
	/**
	 * The game is turn-based - the player
	 * first makes their move, then all
	 * enemies make their move.
	 */
	private void nextMove(Direction move) {
		player.setDirection(move);
		player.move();
		maze.cleanUp();
	}
	
	
	
	public Player getPlayer () {
		return this.player;
	}
	
	////////////////////////////////////////////////////////////////////
	// METHODS FOR MAKING A MOVE
	
	/**
	 * Plays out a turn with the given player move
	 * @param the direction for the player to move
	 */
	public void move(Direction move) {
		System.out.printf("Moving %s\n",
				move.toString());
		nextMove(move);
	}
	
	public void dropBomb() {
		System.out.println("Fire in the hole!");
		// TODO: This is a stub implementation
	}
	
	public void fireArrow(Direction move) {
		System.out.printf("Firing an arrow %s\n",
				move.toString());
		// TODO: This is a stub implementation
	}
	
	////////////////////////////////////////////////////////////////////
	// METHODS FOR SHOWING
	
	/**
	 * 
	 * @return an ASCII representation of the level
	 */
	public String showLevel() {
		char[][] rep = maze.showMaze();
		rep[2 * player.getY() + 1][4 * player.getX() + 2] = '@';
		StringBuilder sb = new StringBuilder();
		for (char[] row: rep) {
			sb.append(row); sb.append('\n');
		}
		return sb.toString();
	}
	
	public String showPlayer() {
		return player.toString();
	}
	
	////////////////////////////////////////////////////////////////////
	// METHODS FOR TESTING
	
	/**
	 * Checks if the player is alive
	 * @return true if the player is alive
	 */
	public boolean playerIsAlive() {
		// TODO: This is a stub implementation
		return true;
	}
	
	/**
	 * Checks if the player is at a certain coordinate
	 * NOTE: The top-left cell of the maze is (0, 0)
	 * @param y y-coordinate
	 * @param x x-coordinate
	 * @return true if the player is at (y, x)
	 */
	public boolean playerIsAt(int y, int x) {
		// TODO: This is a stub implementation
		return true;
	}
	
	/**
	 * Return the amount that the player has of a 
	 * particular item
	 * @param i an item
	 * @return the amount of i that the player has
	 */
	public int playerHas(Item i) {
		// TODO: This is a stub implementation
		return 0;
	}
	
	/**
	 * Returns how many of a particular entity there
	 * are in the maze
	 * Entities include: walls, boulders, doors,
	 *                   specific enemies
	 * @param e an entity
	 * @return the amount of a particular entity
	 */
	public int getNumOfEntity(Entity e) {
		// TODO: This is a stub implementation
		return 0;
	}
	
	/**
	 * Checks if there is a certain entity e at (y, x)
	 * @param e an entity
	 * @param y y-coordinate
	 * @param x x-coordinate
	 * @return true if there is an entity e at (y, x)
	 */
	public boolean entityIsAt(Entity e, int y, int x) {
		// TODO: This is a stub implementation
		return true;
	}
	
	/**
	 * Gets the number of triggered floor switches
	 * @return the number of triggered floor switches
	 */
	public boolean numTriggeredFloorSwitches() {
		// TODO: This is a stub implementation
		return true;
	}
	
	/**
	 * Checks if the level is complete
	 * @return true if the level is complete
	 */
	public boolean levelIsComplete() {
		// TODO: This is a stub implementation
		return false;
	}
	
	/**
	 * Returns the number of enemies in the maze
	 * @return the number of (alive) enemies
	 */
	public int numEnemies() {
		// TODO: This is a stub implementation
		return 0;
	}
}
