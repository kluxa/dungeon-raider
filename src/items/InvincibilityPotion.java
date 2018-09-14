package items;

import player.Player;

public class InvincibilityPotion extends Potion {
	
	@Override
	public void applyEffect(Player player) {
		player.becomeInvincible();
	}
	
	@Override
	public char toChar() {
		return 'I';
	}
	
}
