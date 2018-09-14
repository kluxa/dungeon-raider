package dungeon;

import java.util.ArrayList;

public class LitBomb extends NonLivingEntity {
	Maze maze;
	int countdown;
	
	public LitBomb(Tile tile, Maze maze) {
		super(tile);
		this.maze = maze;
		this.countdown = 4;
	}

	@Override
	public boolean isCollidable() {
		return false;
	}
	
	@Override
	public void collide(Entity entity) {
		// Nothing happens...
	}
	
	public void countdown() {
		countdown--;
		if (countdown == 0) {
			explode();
		}
	}

	@Override
	public void getBlownUp() {
		explode();
	}
	
	public void explode() {
		System.out.println("Kaboom!");
		ArrayList<Entity> affected = new ArrayList<>();
		Tile t = getLocation();
		setLocation(null);
		affected.addAll(maze.getOccupants(maze.getTile(t.getY() + 0, t.getX() + 0)));
		affected.addAll(maze.getOccupants(maze.getTile(t.getY() - 1, t.getX() + 0)));
		affected.addAll(maze.getOccupants(maze.getTile(t.getY() + 1, t.getX() + 0)));
		affected.addAll(maze.getOccupants(maze.getTile(t.getY() + 0, t.getX() - 1)));
		affected.addAll(maze.getOccupants(maze.getTile(t.getY() + 0, t.getX() + 1)));
		for (Entity e: affected) {
			e.getBlownUp();
		}
	}

	@Override
	public char toChar() {
		return '*';
	}

}
