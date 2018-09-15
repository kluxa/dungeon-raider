package dungeon;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public interface Space {
	
	public void arrive(SolidEntity e);
	
	public void depart(SolidEntity e);
}
