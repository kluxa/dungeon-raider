package player;

import dungeon.SolidEntity;
import enemies.Enemy;

public interface PlayerState {
	
	public void update();
	
	public void collide(SolidEntity e);
	
	public void fight(Enemy e);
	
	public void hitByBlast();
	
}
