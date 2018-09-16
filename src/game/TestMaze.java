package game;

public enum TestMaze {
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
	 * '*' = lit bomb - lit bombs don't actually appear in the level
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
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
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
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
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
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
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
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
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
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
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
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', 'F', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
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
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', 'F', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
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
	 * Testing pushing boulders into pits and walking onto a
	 * pit tile (and dying).
	 * Testing pushing boulders into pits while hovering and
	 * moving onto a pit tile while hovering.
	 */
	LEVEL08 {
		public int getWidth() { return 6; }
		public int getHeight() { return 5; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', 'P', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
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
					{' ', 'H', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Bombs - testing collecting and dropping bombs.
	 * Testing blowing up boulders. Testing a chain reaction explosion.
	 * Testing that walls and doors don't get blown up. Testing the
	 * player dying from a bomb.
	 */
	LEVEL09 {
		public int getWidth() { return 10; }
		public int getHeight() { return 5; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', 'O', 'O', ' ', ' ', ' ', 'R', 'W'},
					{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
					{' ', 'B', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'B', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing the player surviving blasts with an invincibility
	 * potion. Testing that the player dies when landing on a pit
	 * tile, even with an invincibility potion. Testing that the
	 * player survives with both potions.
	 */
	LEVEL10 {
		public int getWidth() { return 6; }
		public int getHeight() { return 5; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', 'P', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', 'B', 'I', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'H', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Simple testing of a hunter. Checking that a hunter
	 * moves towards the player. Checking that a player dies
	 * upon collision
	 */
	LEVEL11 {
		public int getWidth() { return 6; }
		public int getHeight() { return 6; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'W'},
					{'W', ' ', ' ', 'H', ' ', 'W'},
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
					{' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing hunter pathfinding in a spiral. Testing
	 * the player killing the hunter with a sword. Testing
	 * the player dying to the hunter even with a sword.
	 */
	LEVEL12 {
		public int getWidth() { return 8; }
		public int getHeight() { return 8; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'W', ' ', 'W'},
					{'W', ' ', 'W', 'W', 'H', 'W', ' ', 'W'},
					{'W', ' ', 'W', 'W', 'W', 'W', ' ', 'W'},
					{'W', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', 'S', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing a sword running out of uses
	 */
	LEVEL13 {
		public int getWidth() { return 8; }
		public int getHeight() { return 8; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', 'S', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W'},
					{'W', ' ', ' ', ' ', ' ', 'W', 'H', 'W'},
					{'W', ' ', 'W', 'W', ' ', 'W', ' ', 'W'},
					{'W', ' ', 'W', 'W', 'W', 'W', 'H', 'W'},
					{'W', 'H', ' ', 'H', ' ', 'H', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'I', ' ', 'S', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing hunter pathfinding in a spiral. Testing
	 * the player killing the hunter with a sword. Testing
	 * the player dying to the hunter even with a sword.
	 */
	LEVEL14 {
		public int getWidth() { return 8; }
		public int getHeight() { return 8; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
					{'W', ' ', 'W', 'W', 'W', 'W', ' ', 'W'},
					{'W', ' ', 'W', 'W', 'W', 'W', ' ', 'W'},
					{'W', ' ', 'W', 'W', 'W', 'W', 'H', 'W'},
					{'W', ' ', 'W', 'W', 'W', 'W', ' ', 'W'},
					{'W', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing a player placing a bomb and firing an arrow,
	 * and destroying enemies with these
	 */
	LEVEL15 {
		public int getWidth() { return 15; }
		public int getHeight() { return 3; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', 'H', ' ', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', 'A', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing that arrows do not go through boulders or 
	 * closed doors, but do go through open doors
	 */
	LEVEL16 {
		public int getWidth() { return 20; }
		public int getHeight() { return 3; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', 'H', ' ', 'O', ' ', ' ', ' ', ' ', ' ', 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'H', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', 'A', 'A', 'A', 'r', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing that you can push boulders through open doors
	 */
	LEVEL17 {
		public int getWidth() { return 9; }
		public int getHeight() { return 5; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', ' ', ' ', ' ', 'W', ' ', ' ', ' ', 'W'},
					{'W', ' ', 'O', ' ', 'R', ' ', ' ', ' ', 'W'},
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
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing that enemies can go through open doors
	 */
	LEVEL18 {
		public int getWidth() { return 15; }
		public int getHeight() { return 3; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'S', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', 'H', ' ', ' ', ' ', ' ', 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r', 'S', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return items;
		}
	},
	
	/**
	 * Testing that the player can't use items they
	 * don't have...
	 */
	LEVEL19 {
		public int getWidth() { return 15; }
		public int getHeight() { return 3; }
		
		public char[][] getTiles() {
			char[][] tiles = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'S', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
			};
			return tiles;
		}
		
		public char[][] getSolidEntities() {
			char[][] entities = {
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', 'H', ' ', 'H', ' ', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
			};
			return entities;
		}
		
		public char[][] getItems() {
			char[][] items = {
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', 'A', ' ', ' ', ' '},
					{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
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
	public abstract char[][] getSolidEntities();
	public abstract char[][] getItems();
}
