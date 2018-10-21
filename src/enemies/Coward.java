package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Coward extends Enemy {
	
	public Coward() {
		this(null);
	}
	
	public Coward(Square s) {
		super(s, 6);
		setMovementPattern(new NoMovement());
	}
	
	@Override
	public char toChar() {
		return 'C';
	}
	
	@Override
	public String getImageName() {
		return "coward";
	}
	
	@Override
	public MovementPattern getDefaultAwareMovePattern (Player p) {
		double dist = getDistance(p.getLocation());
		MovementPattern pattern = null;
		if (dist <= 4) { //arbitrarily chosen
			pattern = new RunAway();
		} else {
			pattern = new SimpleChase();
		}
		return pattern;
	}
}
