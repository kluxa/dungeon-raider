package dungeon;

public enum SampleMaze {
	// The top-left cell of a maze is (0, 0).
	// Character codes:
	// S = starting square
	// E = exit square
	// # = wall
	
	/**
	 * Simple level with no walls.
	 */
	LEVEL01 {
		@Override
		public char[][] getMaze() {
			char[][] maze = {
					{'S', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
			};
			return maze;
		}
	};
	
	// Add more sample levels to test...
	
	
	public abstract char[][] getMaze();
}
