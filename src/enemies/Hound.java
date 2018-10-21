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
		super(s, 6);
	}
	
	@Override
	public char toChar() {
		return 'U';
	}

	@Override
	public String getImageName() {
		return "hound";
	}
	
	@Override
	public MovementPattern getDefaultAwareMovePattern (Player p) {
		return new CornerPlayer();
	}
}
