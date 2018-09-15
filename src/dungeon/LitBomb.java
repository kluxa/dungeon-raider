package dungeon;

import java.util.ArrayList;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class LitBomb extends NonLivingEntity {
	int countdown;
	
	public LitBomb(Square s) {
		super(s);
		s.placeSolidEntity(this);
		this.countdown = 4;
	}
	
	@Override
	public boolean isCollidable() {
		return false;
	}
	
	@Override
	public void collide(SolidEntity entity) {
		// Do nothing
	}
	
	public void countdown() {
		countdown--;
	}
	
	public int getCountdown() {
		return countdown;
	}
	
	@Override
	public void getBlownUp() {
		explode();
	}
	
	public void explode() {
		System.out.println("Kaboom!");
		getLocation().depart(this);
		getLocation().explosion();
	}
	
	@Override
	public char toChar() {
		return '*';
	}
	
}
