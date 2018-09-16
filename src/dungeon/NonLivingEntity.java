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
	
	@Override
	public void fall() {
		System.out.println(this.getClass().getSimpleName() + " fell into a pit");
		getLocation().depart(this);
	}
	
	@Override
	public void hitByProjectile() {
		// Do nothing
	}
	
}
