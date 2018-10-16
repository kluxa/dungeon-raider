package items;

public class Arrow extends UnlimitedCollectible {
	
	@Override
	public char toChar() {
		return 'A';
	}

	@Override
	public String getImageName() {
		return "arrow";
	}
	
}
