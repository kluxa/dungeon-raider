package items;

import dungeon.*;

public abstract class Item {
	
	public abstract void pickUp(Player player);
	
	public abstract char toChar();
	
	/**
	 * @param i an item
	 * @return true if the given item is the same
	 *         type of item (i.e., same class) as
	 *         this item
	 */
	public boolean sameType(Item i) {
		if (this == i) return true;
		if (i == null) return false;
		return this.getClass() == i.getClass();
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
}
