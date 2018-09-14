package dungeon;

import java.util.ArrayList;

import items.Item;
import player.Player;

public abstract class Tile {
	private int x;
	private int y;
	private ArrayList<Item> items;
	private ArrayList<Item> dropped;
	
	public Tile(int y, int x) {
		this.y = y;
		this.x = x;
		items = new ArrayList<Item>();
		dropped = new ArrayList<Item>();
	}
	
	public void arrive(Entity entity) {
		entity.setLocation(this);
		if (entity instanceof Player) {
			loot((Player)entity);
			items.clear();
			items.addAll(dropped);
			dropped.clear();
		}
	}
	
	public void depart(Entity entity) {
		// Do nothing
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void loot(Player player) {
		for (Item i: items) {
			i.pickUp(player);
		}
	}
	
	public void drop(Item i) {
		dropped.add(i);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (this.getClass() != o.getClass()) return false;
		Tile t = (Tile)o;
		return (x == t.x && y == t.y);
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public abstract char toChar();
}
