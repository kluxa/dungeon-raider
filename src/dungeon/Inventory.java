package dungeon;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> items;
	
	public Inventory() {
		this.items = new ArrayList<Item>();
	}
	
	public void addItem(Item i) {
		items.add(i);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append("I have:\n");
		for (Item i: items) {
			sb.append(i.getClass().getSimpleName() + "\n");
		}
		return sb.toString();
	}
}
