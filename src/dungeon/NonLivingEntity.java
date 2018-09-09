package dungeon;

public abstract class NonLivingEntity extends Entity {
	
	public void fall() {
		System.out.println("Something fell");
		this.setLocation(null);
	}
	
	public NonLivingEntity(Tile tile) {
		super(tile);
	}
}
