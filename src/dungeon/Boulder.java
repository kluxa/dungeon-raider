package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Boulder extends NonLivingEntity {
	
	@Override
	public void collide(SolidEntity e) {
		if (e instanceof Player) {
			Direction move = e.getDirection();
			move(move);
		}
	}
	
	@Override
	public void getBlownUp() {
		if (getLocation() != null) {
			getLocation().depart(this);
		}
		setLocation(null);
	}
	
	@Override
	public char toChar() {
		return 'O';
	}
	
}
