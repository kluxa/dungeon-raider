package items;

import player.Player;

public class RedTorch extends Torch {
	
	/**
	 * sets players sightrange to 7
	 */
	@Override
	public void applyEffect(Player player) {
		player.setSightRange(7);
	}
	
	@Override
	public char toChar() {
		return '1';
	}
	
	@Override
	public String getImageName() {
		return "torch_red";
	}
	
}
