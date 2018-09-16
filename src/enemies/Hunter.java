package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Hunter extends Enemy {
	
	public Hunter() {
		this(null);
	}
	
	public Hunter(Square s) {
		super(s);
		setMovementPattern(new SimpleChase());
	}
	
	@Override
	public char toChar() {
		return 'H';
	}
	
}
