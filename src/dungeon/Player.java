package dungeon;

public class Player extends LivingEntity {
	private Maze maze;
	private Inventory inventory;
	private Direction move;
	
	public Player(Maze maze) {
		super(maze.getStartTile());
		inventory = new Inventory();
		this.maze = maze;
	}
	
	@Override
	public void collide(Entity entity) {
		// If anything collides with the
		// player, the player dies.
		this.die();
	}

	@Override
	public void getBlownUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public char toChar() {
		return '@';
	}

	public void setDirection(Direction move) {
		this.move = move;
	}
	
	public void move() {
		maze.moveEntity(this, move);
	}
	
	public void pickUp(Item i) {
		inventory.addItem(i);
	}
	
	@Override
	public String toString() {
		return inventory.toString();
	}
}
