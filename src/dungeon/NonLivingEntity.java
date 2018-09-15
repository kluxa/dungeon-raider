package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public abstract class NonLivingEntity extends SolidEntity {
	
	public NonLivingEntity() {
		this(null);
	}
	
	public NonLivingEntity(Square s) {
		super(s);
	}
	
	public void fall() {
		System.out.println("Something fell into a pit");
		getLocation().depart(this);
	}
	
}
