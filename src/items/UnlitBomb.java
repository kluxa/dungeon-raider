package items;

public class UnlitBomb extends UnlimitedCollectible {
	
	@Override
	public char toChar() {
		return 'B';
	}

	@Override
	public String getImageName() {
		return "unlitbomb";
	}
	
}
