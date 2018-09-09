package dungeon;

import java.util.ArrayList;

public abstract class Tile {
	private int x;
	private int y;
	private ArrayList<Item> items;
	
	public Tile(int y, int x) {
		this.y = y;
		this.x = x;
		items = new ArrayList<Item>();
	}
	
	public void arrive(Entity entity) {
		entity.setLocation(this);
		if (entity instanceof Player) {
			for (Item i: items) {
				i.pickUp((Player)entity);
			}
			items.clear();
		}
	}
	
	public abstract void depart(Entity entity);
	
	public void deposit(Item item) {
		items.add(item);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
}
