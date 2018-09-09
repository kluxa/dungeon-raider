package dungeon;

public class Key extends Limited {
	private static final int USES = 5;
	private static final int CARRY_LIMIT = 1;
	private String color;
	
	public Key(String color) {
		this(CARRY_LIMIT, color);
	}
	
	public Key(int limit, String color) {
		super(limit);
		this.color = color;
	}

	@Override
	public char toChar() {
		return color.charAt(0);
	}
}
