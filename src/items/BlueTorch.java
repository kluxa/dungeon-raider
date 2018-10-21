package items;

import player.Player;

public class BlueTorch extends Torch {

	@Override
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
