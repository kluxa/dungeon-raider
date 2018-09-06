package dungeon;

public enum Direction {
	UP {
		public int getDX() { return  0; }
		public int getDY() { return -1; }
		public String toString() { return "up"; }
	},
	DOWN {
		public int getDX() { return  0; }
		public int getDY() { return  1; }
		public String toString() { return "down"; }
	},
	RIGHT {
		public int getDX() { return  1; }
		public int getDY() { return  0; }
		public String toString() { return "right"; }
	},
	LEFT {
		public int getDX() { return -1; }
		public int getDY() { return  0; }
		public String toString() { return "left"; }
	};
	
	public abstract int getDX();
	public abstract int getDY();
	@Override
	public abstract String toString();
}
