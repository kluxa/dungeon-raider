package items;

import dungeon.*;

public class HoverPotion extends Potion {
	
	public HoverPotion() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void applyEffect(Player player) {
		player.setFlying();
	}
	
	@Override
	public char toChar() {
		return 'H';
	}
	
}
