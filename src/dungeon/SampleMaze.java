package dungeon;

public enum SampleMaze {
	/**
	 * MAZE CREATION CODES
	 * 
	 * Tile character codes:
	 * 'S' = starting tile
	 * 'E' = exit tile
	 * 'P' = pit tile
	 * 'F' = floor switch tile
	 * ' ' = normal path tile
	 * NOTE: Most of our test mazes won't need an exit tile,
	 *       as in most cases, that won't be what we are
	 *       testing.
	 * 
	 * Entity character codes:
	 * 'W' = wall - All levels should be surrounded by walls
	 * 'D' = door
	 * 'B' = boulder
	 * 'H' = hunter
	 * 'S' = strategist
	 * 'U' = hound
	 * 'C' = coward
	 * ' ' = no entity
	 * 
	 * Item character codes:
	 * 'H' = hover potion
	 * 'I' = invincibility potion
	 * 'S' = sword
	 * 'T' = treasure
	 * 'B' = unlit bomb
	 * 'A' = arrow
	 * 'b' = blue key
	 * 'g' = green key
	 * 'r' = red key
	 * 'y' = yellow key
	 * ' ' = no item
	 * NOTE: This assumes we will have only one item in each
	 *       tile, which might not be the case later, but it
	 *       will suffice for now.
	 */
	
	/**
	 * Simple level with walls around the outside.
	 * No items.
	 */
	LEVEL01 {
		public int getWidth() { return 5; }
		public int getHeight() { return 5; }
		
		public char[][] getTiles() {
			char[][] tiles = {    // [5][5]
					{' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Simple level with some treasure
	 */
	LEVEL02 {
		public int getWidth() { return 5; }
		public int getHeight() { return 5; }
		
		public char[][] getTiles() {
			char[][] tiles = {    // [5][5]
					{' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', 'T', ' '},
					{' ', 'T', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	};
	
	// Add more sample levels to test...
	// Add a comment to the top of each level to
	// describe what that level is testing
	
	public abstract int getHeight();
	public abstract int getWidth();
	public abstract char[][] getTiles();
	public abstract char[][] getEntities();
	public abstract char[][] getItems();
}
