package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public abstract class LivingEntity extends SolidEntity {
	
	private boolean isAlive;
	
	public LivingEntity() {
		this(null);
	}
	
	public LivingEntity(Square s) {
		super(s);
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
		getLocation().depart(this);
		isAlive = false;
	}
	
	@Override
	public abstract void hitByProjectile();
	
	@Override
	public abstract void fall();
	
}
