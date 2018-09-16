package dungeon;

public enum Direction {
	UP {
		public int toInt() { return  0; }
		public int getDX() { return  0; }
		public int getDY() { return -1; }
		public String toString() { return "up"; }
	},
	DOWN {
		public int toInt() { return  1; }
		public int getDX() { return  0; }
		public int getDY() { return  1; }
		public String toString() { return "down"; }
	},
	RIGHT {
		public int toInt() { return  2; }
		public int getDX() { return  1; }
		public int getDY() { return  0; }
		public String toString() { return "right"; }
	},
	LEFT {
		public int toInt() { return  3; }
		public int getDX() { return -1; }
		public int getDY() { return  0; }
		public String toString() { return "left"; }
	};
	
	public abstract int toInt();
	public abstract int getDX();
	public abstract int getDY();
	@Override
	public abstract String toString();
	
	public static Direction intToDirection(int i) {
		switch (i) {
		case 0:  return UP;
		case 1:  return DOWN;
		case 2:  return RIGHT;
		case 3:  return LEFT;
		default: return null;
		}
	}
}
