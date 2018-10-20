package player;

import dungeon.SolidEntity;
import enemies.Enemy;

public interface PlayerState {
	
	public void update();
	
	public void collide(SolidEntity e);
	
	public void fight(Enemy e);
	
	public void getBlownUp();
	
	public void hitByProjectile();
	
	public boolean isInvincible();
	
	public String getImageName();
}
