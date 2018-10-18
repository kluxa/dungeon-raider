package items;

public class Sword extends LimitedCollectible {
	private static final int USES = 5;
	private static final int CARRY_LIMIT = 1;
	private int usesLeft;
	
	public Sword() {
		this(CARRY_LIMIT);
	}
	
	public Sword(int limit) {
		super(limit);
		usesLeft = USES;
	}

	@Override
	public char toChar() {
		return 'S';
	}
	
	public void use() {
		usesLeft--;
	}
	
	public int getUsesLeft() {
		return usesLeft;
	}
	
	@Override
	public String toString() {
		return String.format("Sword (uses left: %d)", usesLeft);
	}

	@Override
	public String getImageName() {
		return "sword";
	}
}
