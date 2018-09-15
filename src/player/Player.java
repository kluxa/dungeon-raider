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
	private Direction move;
	
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
	
	////////////////////////////////////////////////////////////////////
	// Game Events
	
	@Override
	public void collide(SolidEntity e) {
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
	
	public void fight(Enemy e) {
		state.fight(e);
	}
	
	public void becomeInvincible() {
		setState(new InvincibleState(this));
	}
	
	public void updateState() {
		state.update();
	}
	
	public void updateBombs() {
		Iterator<LitBomb> it = bombs.iterator();
		while (it.hasNext()) {
			LitBomb bomb = it.next();
			bomb.countdown();
			if (bomb.getCountdown() == 0) {
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
		getLocation().drop(i);
	}
	
	public void consumeItem(Item i) {
		if (i instanceof UnlitBomb) {
			bombs.add(new LitBomb(getLocation()));
		}
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
	// Random Shit
	
	@Override
	public char toChar() {
		return '@';
	}
	
}
