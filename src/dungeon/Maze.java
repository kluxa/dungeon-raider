package dungeon;

public class Maze {
	private char[][] grid;
	private Player player;
	private int startX;
	private int startY;
	
	public Maze(SampleMaze sampleMaze, Player p) {
		readMaze(sampleMaze);
		player = p;
	}
	
	/**
	 * 
	 * @return the length of the maze
	 */
	public int getLength() {
		return grid[0].length;
	}
	
	/**
	 * 
	 * @return the height of the maze
	 */
	public int getHeight() {
		return grid.length;
	}
	
	/**
	 * 
	 * @return the x-coordinate of the starting square
	 */
	public int getStartingX() {
		return startX;
	}
	
	/**
	 * 
	 * @return the y-coordinate of the starting square
	 */
	public int getStartingY() {
		return startY;
	}
	
	/**
	 * We need to add more functionality to this
	 * Currently, it only checks whether the square
	 * that the player is moving to is within the maze
	 * @param p the player
	 * @param move the player's move
	 * @return true if the move is legal
	 */
	public boolean isLegalMove(Player p, Direction move) {
		int x = p.getX() + move.getDX();
		int y = p.getY() + move.getDY();
		return (x >= 0 && x < getLength() &&
				y >= 0 && y < getHeight() );
	}
	
	/**
	 * Reads the maze representation into the Maze
	 * @param maze the maze object
	 */
	private void readMaze(SampleMaze maze) {
		grid = maze.getMaze();
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[0].length; x++) {
				switch (grid[y][x]) {
				// Starting tile is indicated by an 'S'
				case 'S':
					// Set the maze's starting location
					// and clear the 'S' from the grid
					startX = x; startY = y;
					grid[y][x] = ' ';
					break;
				
				// Add cases for other entities here...
				
				}
			}
		}
	}
	
	/**
	 * 
	 * @param p the player
	 * @return an string representation of the
	 *         maze for printing to the terminal
	 */
	public String showMaze() {
		char[][] maze = cloneArray(grid);

		// Draw the player onto the maze
		maze[player.getY()][player.getX()] = '@';
		
		// ...
		
		// Finally convert the maze into
		// a string
		StringBuilder sb = new StringBuilder();
		for (char[] row: maze) {
			sb.append(row); sb.append('\n');
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param the character array to be copied
	 * @return a copy of the array
	 */
	private char[][] cloneArray(char[][] array) {
		int height = array.length;
		int length = array[0].length;
		char[][] copy = new char[height][length];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				copy[i][j] = array[i][j];
			}
		}
		return copy;
	}
}
