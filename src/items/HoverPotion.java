package items;

import player.Player;

public class HoverPotion extends Potion {
	
	@Override
	public void applyEffect(Player player) {
		player.setFlying();
	}
	
	@Override
	public char toChar() {
		return 'H';
	}
	
}
