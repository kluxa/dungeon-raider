package dungeon;

public class Boulder extends NonLivingEntity {

	public Boulder(Tile tile) {
		super(tile);
	}

	@Override
	public void collide(Entity entity) {
		if (entity instanceof Player) {
			// TODO
		}
	}

	@Override
	public void getBlownUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public char toChar() {
		return 'B';
	}
}
