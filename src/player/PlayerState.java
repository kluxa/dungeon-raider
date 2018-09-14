package player;

import dungeon.Entity;
import enemies.Enemy;

public interface PlayerState {
	
	public void update();
	
	public void collide(Entity e);
	
	public void fight(Enemy e);
	
	public void hitByBlast();
	
}
