package player;

import java.util.ArrayList;

import items.*;

public class Inventory {
	private ArrayList<Item> items;
	
	public Inventory() {
		this.items = new ArrayList<Item>();
	}
	
	public void addItem(Item i) {
		items.add(i);
	}
	
	public void removeItem(Item i) {
		items.remove(i);
	}
	
	public boolean hasItem(Item i) {
		return items.contains(i);
	}
	
	public int numItemsOfType(Item i) {
		int count = 0;
		for (Item heldItem: items) {
			if (heldItem.sameType(i)) {
				count++;
			}
		}
		return count;
	}
	
	public Item getItemOfType(Item i) {
		for (Item heldItem: items) {
			if (heldItem.sameType(i)) {
				return heldItem;
			}
		}
		return null;
	}
	
	public boolean hasWeapon() {
		for (Item heldItem: items) {
			if (heldItem instanceof Sword) {
				return true;
			}
		}
		return false;
	}
	
	public void useWeapon() {
		Sword s = null;
		for (Item heldItem: items) {
			if (heldItem instanceof Sword) {
				s = (Sword)heldItem;
			}
		}
		if (s != null) {
			s.use();
			if (s.getUsesLeft() == 0) {
				items.remove(s);
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append("I have:\n");
		for (Item i: items) {
			sb.append(i.toString() + "\n");
		}
		return sb.toString();
	}
}
