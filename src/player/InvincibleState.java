package player;

import dungeon.SolidEntity;
import enemies.Enemy;

public class InvincibleState implements PlayerState {
	private Player player;
	private static final int DURATION = 15;
	private int countdown;
	
	public InvincibleState(Player p) {
		System.out.println("You are invincible!");
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
	public void collide(SolidEntity e) {
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
	public void getBlownUp() {
		System.out.println("You survived the blast!");
	}

	@Override
	public void hitByProjectile() {
		System.out.println("The projectile was harmless!");
	}
	
	@Override
	public boolean isInvincible() {
		return true;
	}
	
	@Override
	public String getImageName() {
		return "player_invincible";
	}
}
