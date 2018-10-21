package enemies;

import dungeon.*;

import java.lang.Math.*;

import player.*;

public abstract class Enemy extends LivingEntity {
	private boolean awareOfPlayer;
	private MovementPattern pattern;
	private int awareDistance;
	private int tick = 0;
	
	public Enemy(Square s, int awareDist_) {
		super(s);
		awareOfPlayer = false;
		pattern = new NoMovement();
		awareDistance = awareDist_;
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
	
	@Override
	public void hitByProjectile() {
		die();
	}
	
	@Override
	public void fall() {
		die();
	}
	
	public void setMovementPattern(MovementPattern pattern) {
		this.pattern = pattern;
	}
	
	public MovementPattern getDefaultAwareMovePattern (Player p) {
		return new NoMovement();
	}
	
	public void selectMove(Maze maze) {
		setDirection(pattern.chooseMove(getLocation(),
				maze, getDirection()));
	}
	
	public void makeMove(Maze maze) {
		selectMove(maze);
		move(getDirection());
	}
	
	/**
	 * Causes enemies to make a move based on player location and their awareness of the player
	 * Enemies make moves once every 2 turns
	 * @param p
	 * @param maze
	 */
	public void update (Player p, Maze maze) {
		if (!awareOfPlayer) {
			Square pSquare = p.getLocation();
			double hypot = getDistance(pSquare);
			if (hypot <= awareDistance) {
				awareOfPlayer = true;
				tick = 0;
			}
		}
		if (awareOfPlayer) {
			tick++;
			if (tick % 2 == 0) {
				if (p.isInvincible()) {
					setMovementPattern (new RunAway());
				} else {
					setMovementPattern (getDefaultAwareMovePattern(p));
				}
				makeMove(maze);
			}
		}
	}
	
	/**
	 * Gets the distance from the player to the enemy
	 * @param player
	 * @param diffY
	 * @return
	 */
	public double getDistance (Square player) {
		int diffX = Math.abs(player.getX() - this.getX());
		int diffY = Math.abs(player.getY() - this.getY());
		double xSq = (double) (diffX * diffX);
		double ySq = (double) (diffY * diffY);
		return Math.round(Math.sqrt(xSq + ySq));
	}
}
