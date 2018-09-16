package player;

import dungeon.SolidEntity;
import enemies.Enemy;
import items.Sword;

public class NormalState implements PlayerState {
	private Player player;
	
	public NormalState(Player p) {
		player = p;
	}
	
	@Override
	public void update() {
		System.out.println("Nothing to update");
	}
	
	@Override
	public void collide(SolidEntity e) {
		if (e instanceof Enemy) {
			player.die();
		}
	}
	
	@Override
	public void fight(Enemy e) {
		if (player.numItemsOfType(new Sword()) > 0) {
			player.useWeapon();
			e.die();
		} else {
			player.die();
		}
	}
	
	@Override
	public void getBlownUp() {
		player.die();
	}
	
	@Override
	public void hitByProjectile() {
		player.die();
	}
}
