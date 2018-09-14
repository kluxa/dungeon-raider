package items;

import player.Player;

public abstract class Item {
	
	public abstract void pickUp(Player player);
	
	public abstract char toChar();
	
	/**
	 * @param i an item
	 * @return true if the given item is the same
	 *         type of item (i.e., same class) as
	 *         this item
	 */
	public boolean sameType(Object i) {
		if (this == i) return true;
		if (i == null) return false;
		return this.getClass() == i.getClass();
	}
	
	@Override
	public boolean equals(Object o) {
		return this.sameType(o);
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
}
