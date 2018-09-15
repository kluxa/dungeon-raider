package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Hound extends Enemy {
	
	public Hound() {
		this(null);
	}
	
	public Hound(Square s) {
		super(s);
	}
	
	@Override
	public char toChar() {
		return 'U';
	}
	
}
