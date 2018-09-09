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
	 * 'O' = boulder
	 * 'H' = hunter
	 * 'S' = strategist
	 * 'U' = hound
	 * 'C' = coward
	 * 'B' = blue door
	 * 'G' = green door
	 * 'R' = red door
	 * 'Y' = yellow door
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
	},
	
	/**
	 * Simple level with some keys, testing that the player
	 * cannot hold more than one key at once
	 */
	LEVEL03 {
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
					{' ', ' ', ' ', 'r', ' '},
					{' ', 'g', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Level with keys and doors, testing that the player can't
	 * open a door with a non-matching key, but can open a door
	 * with a matching key.
	 * Testing that the key disappears after use.
	 */
	LEVEL04 {
		public int getWidth() { return 9; }
		public int getHeight() { return 5; }
		
		public char[][] getTiles() {
			char[][] tiles = {    // [5][9]
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', 'W', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', 'R', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', 'W', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', 'r', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'g', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing boulder pushing
	 * Testing that players can't push more than one boulder at
	 * once and that players can't push boulders into walls
	 */
	LEVEL05 {
		public int getWidth() { return 9; }
		public int getHeight() { return 5; }
		
		public char[][] getTiles() {
			char[][] tiles = {    // [5][9]
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', 'O', ' ', 'O', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', 'O', ' ', 'O', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing boulder pushing
	 * Testing that pushing a boulder onto a switch triggers it,
	 * and that pushing a boulder off a switch untriggers it.
	 */
	LEVEL06 {
		public int getWidth() { return 9; }
		public int getHeight() { return 5; }
		
		public char[][] getTiles() {
			char[][] tiles = {    // [5][9]
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', 'F', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', 'O', ' ', 'O', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', 'O', ' ', 'O', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing boulders starting on switches
	 * If a boulder starts on a switch, the switch should already
	 * be triggered...
	 */
	LEVEL07 {
		public int getWidth() { return 9; }
		public int getHeight() { return 5; }
		
		public char[][] getTiles() {
			char[][] tiles = {    // [5][9]
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', 'F', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'O', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'O', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Pits - testing that the player dies when they
	 * walk onto a pit tile
	 */
	LEVEL08 {
		public int getWidth() { return 6; }
		public int getHeight() { return 5; }
		
		public char[][] getTiles() {
			char[][] tiles = {    // [5][5]
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', 'P', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'W'},
					{'W', ' ', 'O', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '}
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
