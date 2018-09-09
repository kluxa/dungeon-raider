package dungeon;

public abstract class LivingEntity extends Entity {
	private boolean isAlive;
	
	public LivingEntity(Tile tile) {
		super(tile);
		this.isAlive = true;
	}
	
	/**
	 * 
	 * @return true if the entity is alive
	 */
	public boolean isAlive() {
		return isAlive;
	}
	
	/**
	 * causes the entity to die
	 */
	public void die() {
		System.out.println("Someone has died, FeelsBadMan");
		isAlive = false;
	}
	
	@Override
	public void fall() {
		die();
	}
}
