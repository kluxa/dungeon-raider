package enemies;

import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Strategist extends Enemy {
	
	public Strategist() {
		this(null);
	}
	
	public Strategist(Square s) {
		super(s);
		setMovementPattern(new Strategic());
	}

	@Override
	public char toChar() {
		return 'S';
	}

	@Override
	public String getImageName() {
		return "strategist";
	}
}
