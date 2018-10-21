package items;

import player.Player;

public class YellowTorch extends Torch {
	
	/**
	 * sets players sightrange to 9
	 */
	@Override
	public void applyEffect(Player player) {
		player.setSightRange(9);
	}
	
	@Override
	public char toChar() {
		return '2';
	}
	
	@Override
	public String getImageName() {
		return "torch_yellow";
	}
	
}