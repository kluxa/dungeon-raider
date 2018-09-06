package dungeon;

public class Player {
	
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void move(Maze maze, Direction move) {
		if (maze.isLegalMove(this, move)) {
			x += move.getDX();
			y += move.getDY();
		}
	}
}
