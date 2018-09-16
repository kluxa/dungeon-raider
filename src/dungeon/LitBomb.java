package dungeon;

import java.util.ArrayList;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class LitBomb extends NonLivingEntity {
	private final static int DURATION = 4;
	int countdown;
	
	public LitBomb(Square s) {
		super(s);
		s.placeSolidEntity(this);
		this.countdown = DURATION;
	}
	
	public int getCountDown() {
		return this.countdown;
	}
	
	@Override
	public boolean isCollidable() {
		return false;
	}
	
	@Override
	public void collide(SolidEntity entity) {
		// Do nothing
	}
	
	public int countdown() {
		return --countdown;
	}
	
	@Override
	public void getBlownUp() {
		explode();
	}
	
	public void explode() {
		System.out.println("Kaboom!");
		Square s = getLocation();
		setLocation(null);
		s.depart(this);
		s.explosion();
	}
	
	@Override
	public char toChar() {
		return '*';
	}
	
}
