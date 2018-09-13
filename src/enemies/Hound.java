package enemies;

import dungeon.Tile;

public class Hound extends Enemy {

	public Hound(Tile tile) {
		super(tile);
	}

	@Override
	public char toChar() {
		return 'U';
	}
}
