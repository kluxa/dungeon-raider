package items;

import dungeon.*;

public class InvincibilityPotion extends Potion {
	
	public InvincibilityPotion() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void applyEffect(Player player) {
		player.becomeInvincible();
	}
	
	@Override
	public char toChar() {
		return 'I';
	}
	
	@Override
	public void pickUp(Player player) {
		// TODO Auto-generated method stub
		
	}
	
}
