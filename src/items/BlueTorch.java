package items;

import player.Player;

public class BlueTorch extends Torch {
	
	/**
	 * sets players sightrange to 11
	 */
	@Override
	public void applyEffect(Player player) {
		player.setSightRange(11);
	}
	
	@Override
	public char toChar() {
		return '3';
	}
	
	@Override
	public String getImageName() {
		return "torch_blue";
	}
	
}