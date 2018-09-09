package dungeon;

public abstract class Entity {
	private Tile location;
	
	public Entity(Tile tile) {
		this.location = tile;
	}
	
	public abstract void collide(Entity entity);
	
	public abstract void getBlownUp();
	
	public abstract void fall();
	
	public int getX() {
		return location.getX();
	}
	
	public int getY() {
		return location.getY();
	}
	
	public Tile getLocation() {
		return location;
	}
	
	public void setLocation(Tile t) {
		location = t;
	}
	
	public boolean isCollidable() {
		return true;
	}
	
	public abstract char toChar();
}
