package dungeon;

public class Path extends Tile {
	
	public Path(int y, int x) {
		super(y, x);
	}

	@Override
	public char toChar() {
		return ' ';
	}
}
