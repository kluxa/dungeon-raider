package dungeon;

import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class Door extends NonLivingEntity {
	private boolean isOpen;
	private String color;
	
	public Door() {
	}
	
	public Door(String color) {
		this(null, color);
	}
	
	public Door(Square s, String color) {
		super(s);
		isOpen = false;
		this.color = color;
	}
	
	@Override
	public void collide(SolidEntity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			Key matchingKey = new Key(color);
			if (p.hasItem(matchingKey)) {
				System.out.println("Unlocked!");
				isOpen = true;
				p.removeItem(matchingKey);
			}
		}
	}
	
	@Override
	public boolean isCollidable() {
		return !isOpen;
	}
	
	@Override
	public void getBlownUp() {
		System.out.println("Doors don't get blown up.");
	}

	@Override
	public char toChar() {
		return Character.toUpperCase(color.charAt(0));
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (this.getClass() != o.getClass()) return false;
		Door d = (Door) o;
		return (this.color.equalsIgnoreCase(d.color));
	}
	
}
