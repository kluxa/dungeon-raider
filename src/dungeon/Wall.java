package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Wall extends NonLivingEntity {
	
	public Wall() {
		this(null);
	}
	
	public Wall(Square s) {
		super(s);
	}
	
	@Override
	public void collide(SolidEntity entity) {
		// Do nothing
	}
	
	@Override
	public void getBlownUp() {
		System.out.println("Walls don't get blown up.");
	}
	
	@Override
	public char toChar() {
		return 'W';
	}
	
}
