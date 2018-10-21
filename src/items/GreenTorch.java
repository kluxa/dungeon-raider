package items;

import player.Player;

public class GreenTorch extends Torch {

	@Override
	public void applyEffect(Player player) {
		player.setSightRange(9);
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