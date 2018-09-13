package enemies;

import dungeon.Tile;

public class Strategist extends Enemy {

	public Strategist(Tile tile) {
		super(tile);
	}

	@Override
	public char toChar() {
		return 'S';
	}
}
