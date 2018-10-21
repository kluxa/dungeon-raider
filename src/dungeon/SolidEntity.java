package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public abstract class SolidEntity extends Entity {
	
	private Square location;
	private Direction move;
	
	/**
	 * Calls to this no-arg constructor
	 * should be closely followed up by a
	 * call to setLocation on the entity.
	 * This constructor is used by the
	 * factories.
	 */
	public SolidEntity() {
		this(null);
	}
	
	public SolidEntity(Square s) {
		this.location = s;
	}
	
	public int getX() {
		return location.getX();
	}
	
	public int getY() {
		return location.getY();
	}
	
	public Square getLocation() {
		return location;
	}
	
	public void setLocation(Square s) {
		location = s;
	}
	
	public Direction getDirection() {
		return move;
	}
	
	public void setDirection(Direction d) {
		move = d;
	}
	
	public void move(Direction d) {
		move = d;

		if(d != null){
			getLocation().move(this, d);
		}

	}
	
	public abstract void hitByProjectile();
	
	public abstract void collide(SolidEntity e);
	
	public abstract void getBlownUp();
	
	public abstract void fall();
	
	public boolean isCollidable() {
		return true;
	}
	
}
