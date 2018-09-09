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
	
	public boolean hasItem(Item i) {
		return inventory.hasItem(i);
	}
	
	public void pickUp(Item i) {
		inventory.addItem(i);
	}
	
	public int numItemsOfType(Item i) {
		return inventory.numItemsOfType(i);
	}
	
	public Item getItemOfType(Item i) {
		return inventory.getItemOfType(i);
	}
	
	public void dropItem(Item i) {
		inventory.removeItem(i);
		getLocation().drop(i);
	}
	
	public void consumeItem(Item i) {
		inventory.removeItem(i);
	}
	
	@Override
	public String toString() {
		return inventory.toString();
	}
}
