package enemies;

import dungeon.*;

public abstract class Enemy extends LivingEntity {
	private int awareDistance;
	private boolean awareOfPlayer;
	private MovementPattern pattern;
	public Direction move;
	
	public Enemy(Tile tile) {
		super(tile);
		awareOfPlayer = false;
		pattern = new NoMovement();
	}

	@Override
	public void collide(Entity entity) {
		if (entity instanceof Player) {
			Player p = (Player)entity;
			p.fight(this);
		}
	}
	
	@Override
	public void getBlownUp() {
		die();
	}
	
	public void setMovementPattern(MovementPattern pattern) {
		this.pattern = pattern;
	}
	
	public void selectMove(Maze maze) {
		move = pattern.chooseMove(getLocation(), maze);
	}
}
