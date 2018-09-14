package player;

import dungeon.Entity;
import enemies.Enemy;

public class InvincibleState implements PlayerState {
	private Player player;
	private static final int DURATION = 15;
	private int countdown;
	
	public InvincibleState(Player p) {
		countdown = DURATION + 1;
		player = p;
	}
	
	@Override
	public void update() {
		countdown--;
		System.out.printf("Invincibility - Counting down... " +
	                      "(countdown = %d)\n", countdown);
		if (countdown == 0) {
			System.out.println("Your time is up!");
			player.setState(new NormalState(player));
		}
	}
	
	@Override
	public void collide(Entity e) {
		if (e instanceof Enemy) {
			((Enemy) e).die();
		}
	}
	
	@Override
	public void fight(Enemy e) {
		System.out.println("You won, not even close!");
		e.die();
	}
	
	@Override
	public void hitByBlast() {
		System.out.println("You survived the blast!");
	}
	
}
