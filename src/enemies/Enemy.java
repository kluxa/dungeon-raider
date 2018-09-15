package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

public abstract class Enemy extends LivingEntity {
	private int awareDistance;
	private boolean awareOfPlayer;
	private MovementPattern pattern;
	public Direction move;
	
	public Enemy(Square s) {
		super(s);
		awareOfPlayer = false;
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
		move = pattern.chooseMove(getLocation(), maze);
	}
}
