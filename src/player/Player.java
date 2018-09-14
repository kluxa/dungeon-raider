package player;

import items.Item;
import dungeon.Direction;
import dungeon.Entity;
import dungeon.LivingEntity;
import dungeon.Maze;
import enemies.Enemy;

public class Player extends LivingEntity {
	private Maze maze;
	private Inventory inventory;
	private Direction move;
	private boolean isFlying;
	private PlayerState state;
	
	public Player(Maze maze) {
		super(maze.getStartTile());
		inventory = new Inventory();
		state = new NormalState(this);
		this.isFlying = false;
		this.maze = maze;
	}
	
	@Override
	public void collide(Entity e) {
		state.collide(e);
	}
	
	@Override
	public void getBlownUp() {
		state.hitByBlast();
	}
	
	@Override
	public void fall() {
		if (!this.isFlying) {
			die();
		}
	}
	
	public void updateState() {
		state.update();
	}
	
	@Override
	public char toChar() {
		return '@';
	}
	
	public void fight(Enemy e) {
		state.fight(e);
	}
	
	public void becomeInvincible() {
		System.out.println("This... is to go... even further beyond!");
		this.setState(new InvincibleState(this));
	}
	
	public void setState(PlayerState state) {
		this.state = state;
	}
	
	public void setFlying() {
		System.out.println("I believe I can fly...");
		isFlying = true;
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	public Direction getDirection() {
		return move;
	}
	
	public void setDirection(Direction move) {
		this.move = move;
	}
	
	public void move() {
		maze.moveEntity(this, move);
	}
	
	public boolean hasItem(Item i) {
		return inventory.hasItem(i);
	}
	
	public void pickUp(Item i) {
		inventory.addItem(i);
	}
	
	public int numItemsOfType(Item i) {
		return inventory.numItemsOfType(i);
	}
	
	public Item getItemOfType(Item i) {
		return inventory.getItemOfType(i);
	}
	
	public void dropItem(Item i) {
		inventory.removeItem(i);
		getLocation().drop(i);
	}
	
	public void consumeItem(Item i) {
		inventory.removeItem(i);
	}
	
	public void useWeapon() {
		inventory.useWeapon();
	}
	
	@Override
	public String toString() {
		return inventory.toString();
	}
}
