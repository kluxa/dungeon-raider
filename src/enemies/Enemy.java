package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

public abstract class Enemy extends LivingEntity {
	private int awareDistance;
	private boolean awareOfPlayer;
	private MovementPattern pattern;
	private int tick = 0;
	
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
	
	public void selectMove(Maze maze) {
		setDirection(pattern.chooseMove(getLocation(),
				maze, getDirection()));
	}
	
	public void makeMove(Maze maze) {
		selectMove(maze);
		move(getDirection());
	}
	
	public void update (Maze maze) {
		if (awareDistance == -1) {
			awareOfPlayer = false;
			tick = 0;
		} else {
			awareOfPlayer = true;
			tick++;
			if (tick % 2 == 0) {
				makeMove(maze);
			}
		}
	}
	
	public boolean isAwareOfPlayer () {
		/*if (//player in aware distance) {
				do stuff
		}*/
		
		return null;
	}
	
}
