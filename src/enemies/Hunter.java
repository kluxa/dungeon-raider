package enemies;

import dungeon.Tile;

public class Hunter extends Enemy {

	public Hunter(Tile tile) {
		super(tile);
	}

	@Override
	public char toChar() {
		return 'H';
	}
}
