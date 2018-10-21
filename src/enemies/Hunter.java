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
		super(s, 6);
		setMovementPattern(new SimpleChase());
	}
	
	@Override
	public char toChar() {
		return 'H';
	}

	@Override
	public String getImageName() {
		return "hunter";
	}
	
	@Override
	public MovementPattern getDefaultAwareMovePattern (Player p) {
		return new SimpleChase();
	}
}
