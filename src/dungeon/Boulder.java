package dungeon;

import player.Player;

public class Boulder extends NonLivingEntity {

	public Boulder(Tile tile) {
		super(tile);
	}

	@Override
	public void collide(Entity entity) {
		if (entity instanceof Player) {
			Player p = (Player)entity;
			Direction move = p.getDirection();
			p.getMaze().moveEntity(this, move);
		}
	}

	@Override
	public void getBlownUp() {
		getLocation().depart(this);
		setLocation(null);
	}

	@Override
	public char toChar() {
		return 'O';
	}
}
