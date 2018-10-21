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
		super(s);
		setMovementPattern(new SimpleChase());
	}
	
	@Override
	public char toChar() {
		return 'C';
	}

	@Override
	public String getImageName() {
		return "coward";
	}
	
}
