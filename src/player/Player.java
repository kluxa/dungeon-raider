package player;

import java.util.ArrayList;
import java.util.Iterator;

import enemies.*;
import dungeon.*;
import items.*;
import game.*;

public class Player extends LivingEntity {
	private Maze maze;
	private Inventory inventory;
	private PlayerState state;
	private ArrayList<LitBomb> bombs;
	private boolean isFlying;
	
	public Player(Maze maze) {
		super(maze.getStartSquare());
		inventory = new Inventory();
		state = new NormalState(this);
		bombs = new ArrayList<>();
		this.isFlying = false;
		this.maze = maze;
	}
	
	////////////////////////////////////////////////////////////////////
	// Getters/Setters
	
	public Maze getMaze() {
		return maze;
	}
	
	public void setState(PlayerState state) {
		this.state = state;
	}
	
	public void setFlying() {
		System.out.println("You can fly now!");
		isFlying = true;
	}
	
	public boolean isInvincible() {
		return state.isInvincible();
	}
	
	////////////////////////////////////////////////////////////////////
	// Game Events
	
	@Override
	public void collide(SolidEntity e) {
		state.collide(e);
	}
	
	@Override
	public void getBlownUp() {
		state.getBlownUp();
	}
	
	@Override
	public void fall() {
		if (!this.isFlying) {
			die();
		}
	}
	
	@Override
	public void hitByProjectile() {
		state.hitByProjectile();
	}
	
	public void fight(Enemy e) {
		state.fight(e);
	}
	
	public void becomeInvincible() {
		setState(new InvincibleState(this));
	}
	
	public void updateState() {
		state.update();
	}
	
	public void dropBomb() {
		inventory.removeItem(new UnlitBomb());
		bombs.add(new LitBomb(getLocation()));
	}
	
	public void fireArrow(Direction move) {
		inventory.removeItem(new Arrow());
		getLocation().launchProjectile(move);
	}
	
	public void updateBombs() {
		Iterator<LitBomb> it = bombs.iterator();
		while (it.hasNext()) {
			LitBomb bomb = it.next();
			if (bomb.getLocation() == null) {
				it.remove();
			} else if (bomb.countdown() == 0) {
				it.remove();
				bomb.explode();
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////
	// Inventory
	
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
		getLocation().dropItem(i);
	}
	
	public void removeItem(Item i) {
		inventory.removeItem(i);
	}
	
	public void useWeapon() {
		inventory.useWeapon();
	}
	
	@Override
	public String toString() {
		return inventory.toString();
	}
	
	////////////////////////////////////////////////////////////////////
	// Random
	
	@Override
	public char toChar() {
		return '@';
	}
	
}
