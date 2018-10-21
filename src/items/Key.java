package items;

public class Key extends LimitedCollectible {
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (this.getClass() != o.getClass()) return false;
		Key k = (Key)o;
		return this.color.equalsIgnoreCase(k.color);
	}
	
	@Override
	public String toString() {
		return String.format("Key (%s)", color);
	}

	@Override
	public String getImageName() {
		return "key_" + color.toLowerCase();
	}
	
	public String getColor () {
		return this.color;
	}
}
