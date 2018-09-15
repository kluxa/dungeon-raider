package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public abstract class SolidEntity extends Entity {
	
	private Square location;
	private Direction move;
	
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
	
	public void move(Direction d) {
		move = d;
		getLocation().move(this, d);
	}
	
	public abstract void collide(SolidEntity e);
	
	public abstract void getBlownUp();
	
	public abstract void fall();
	
	public boolean isCollidable() {
		return true;
	}
	
	public abstract char toChar();
	
}
