package dungeon;

import java.util.ArrayList;
import java.util.Iterator;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class Square {
	private Tile tile;
	private ArrayList<Item> items;
	private ArrayList<Item> droppedItems;
	private ArrayList<SolidEntity> occupants;
	private int y;
	private int x;
	private Square up;
	private Square down;
	private Square right;
	private Square left;
	private boolean exploding;
	
	public Square(int y, int x) {
		items = new ArrayList<Item>();
		droppedItems = new ArrayList<Item>();
		occupants = new ArrayList<SolidEntity>();
		this.y = y;
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public boolean getExploding () {
		return this.exploding;
	}
	
	public void setExploding (boolean input) {
		this.exploding = input;
	}
	
	public void setUp(Square s) { up = s; }
	public void setDown(Square s) { down = s; }
	public void setRight(Square s) { right = s; }
	public void setLeft(Square s) { left = s; }

	public void clear() {
		items.clear();
		occupants.clear();
	}
	
	////////////////////////////////////////////////////////////////////
	// Tiles
	
	public Tile getTile() {
		return tile;
	}
	
	public void setTile(Tile t) {
		this.tile = t;
	}
	
	////////////////////////////////////////////////////////////////////
	// Items
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void loot(Player player) {
		for (Item i: items) {
			i.pickUp(player);
		}
	}
	
	public void dropItem(Item item) {
		droppedItems.add(item);
	}
	
	public int getNumOfItem(Item i) {
		int count = 0;
		for (Item item: items) {
			if (item.sameType(i)) {
				count++;
			}
		}
		return count;
	}
	
	////////////////////////////////////////////////////////////////////
	// Occupants
	
	public ArrayList<SolidEntity> getOccupants() {
		return occupants;
	}
	
	public void placeSolidEntity(SolidEntity e) {
		occupants.add(e);
	}
	
	/**
	 * 
	 * @return the occupant of the square that is
	 *         collidable, or null otherwise
	 */
	public SolidEntity getCollidableOccupant() {
		for (SolidEntity entity: occupants)
			if (entity.isCollidable())
				return entity;
		return null;
	}
	
	public boolean containsEntity(Entity e) {
		for (SolidEntity s: occupants) {
			if (s.equals(e)) {
				return true;
			}
		}
		for (Item i: items) {
			if (i.equals(e)) {
				return true;
			}
		}
		return false;
	}
	
	public void arrive(SolidEntity e) {
		e.setLocation(this);
		occupants.add(e);
		if (e instanceof Player) {
			Player p = (Player) e;
			loot(p);
			items.clear();
			items.addAll(droppedItems);
			droppedItems.clear();
		}
		tile.arrive(e);
	}
	
	public void depart(SolidEntity e) {
		occupants.remove(e);
		tile.depart(e);
	}
	
	public void explosion() {
		exploding = true;
		ArrayList<SolidEntity> affected = new ArrayList<>();
		affected.addAll(getOccupants());
		affected.addAll(up.getOccupants());
		affected.addAll(down.getOccupants());
		affected.addAll(right.getOccupants());
		affected.addAll(left.getOccupants());
		for (SolidEntity e: affected) {
			e.getBlownUp();
		}
	}
	
	/**
	 * 
	 * @param e the entity to be moved
	 * @param d the direction to move the entity in
	 */
	public void move(SolidEntity e, Direction d) {
		Square dest = getAdjacentSquare(d);
		SolidEntity o = dest.getCollidableOccupant();
		if (o != null) {
			o.collide(e);
		} else {
			depart(e);
			dest.arrive(e);
		}
	}
	
	private Square getAdjacentSquare(Direction d) {
		switch (d) {
		case UP:    return up;
		case DOWN:  return down;
		case RIGHT: return right;
		case LEFT:  return left;
		case NO_MOVE: return this;
		default:    return null;
		}
	}
	
	public void launchProjectile(Direction move) {
		SolidEntity target;
		Square nextSquare = getAdjacentSquare(move);
		if (nextSquare != null) {
			target = nextSquare.getCollidableOccupant();
			if (target == null) {
				nextSquare.launchProjectile(move);
			} else {
				target.hitByProjectile();
			}
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (this.getClass() != o.getClass()) return false;
		Square s = (Square)o;
		return (x == s.x && y == s.y);
	}
	
	@Override
	public String toString() {
		return String.format("(%d, %d)", y, x);
	}
}
