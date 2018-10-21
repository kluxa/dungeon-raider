package items;

import player.Player;

public class RedTorch extends Torch {

	@Override
	/**
	 * sets players sightrange to 8
	 */
	public void applyEffect(Player player) {
		player.setSightRange(8);
	}

	@Override
	public char toChar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getImageName() {
		// TODO Auto-generated method stub
		return null;
	}

}
