package dungeon;

import java.util.ArrayList;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public abstract class Tile extends Entity implements Space {
	
	public abstract char toChar();
	
}
