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
		super(s, 8);
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
	
	@Override
	public MovementPattern getDefaultAwareMovePattern (Player p) {
		return new Strategic();
	}
}
