package enemies;

import dungeon.Tile;

public class Coward extends Enemy {

	public Coward(Tile tile) {
		super(tile);
	}

	@Override
	public char toChar() {
		return 'C';
	}
}
