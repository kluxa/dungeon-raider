package dungeon;

public class FloorSwitch extends Tile {
	private boolean isTriggered;
	
	public FloorSwitch(int y, int x) {
		super(y, x);
		this.isTriggered = false;
	}
	
	@Override
	public void arrive(Entity entity) {
		super.arrive(entity);
		if (entity instanceof Boulder) {
			// System.out.println("Triggered!");
			isTriggered = true;
		}
	}
	
	@Override
	public void depart(Entity entity) {
		if (entity instanceof Boulder) {
			// System.out.println("Untriggered!");
			isTriggered = false;
		}
	}
	
	public boolean isTriggered() {
		return this.isTriggered;
	}

	@Override
	public char toChar() {
		return 'F';
	}
}
