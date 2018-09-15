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
	}
	
	@Override
	public char toChar() {
		return 'H';
	}
	
}
