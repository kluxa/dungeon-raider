package items;

import player.Player;

public class BlueTorch extends Torch {

	@Override
	/**
	 * sets players sightrange to 10
	 */
	public void applyEffect(Player player) {
		player.setSightRange(10);
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