package dungeon;

import items.Key;
import player.Player;

public class Door extends NonLivingEntity {
	private boolean isOpen;
	private String color;
	
	public Door(Tile tile) {
		super(tile);
		this.isOpen = false;
	}
	
	public Door(Tile tile, String color) {
		super(tile);
		this.color = color;
	}
	
	@Override
	public void collide(Entity entity) {
		if (entity instanceof Player) {
			Player p = (Player)entity;
			Key matchingKey = new Key(color);
			if (p.hasItem(matchingKey)) {
				System.out.println("Unlocked!");
				isOpen = true;
				p.consumeItem(matchingKey);
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
}
