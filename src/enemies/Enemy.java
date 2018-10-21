package enemies;

import dungeon.*;
import player.*;

public abstract class Enemy extends LivingEntity {
	private int cowardsDistanceThreshold;
	private boolean awareOfPlayer;
	private MovementPattern pattern;
	
	public Enemy(Square s) {
		super(s);
		awareOfPlayer = false;
		cowardsDistanceThreshold = 5;
		pattern = new NoMovement();
	}
	
	@Override
	public void collide(SolidEntity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
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
		setDirection(pattern.chooseMove(getLocation(),
				maze, getDirection()));
	}
	
	public void makeMove(Maze maze) {
		selectMove(maze);
		move(getDirection());
	}
	
}
