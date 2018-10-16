package items;

public class Treasure extends UnlimitedCollectible {
	
	public char toChar() {
		return 'T';
	}

	@Override
	public String getImageName() {
		return "treasure";
	}
	
}
