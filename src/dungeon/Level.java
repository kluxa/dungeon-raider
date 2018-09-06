package dungeon;

public class Level {
	private Player player;
	private Maze maze;
	
	public Level(SampleMaze sampleMaze) {
		player = new Player();
		this.maze = new Maze(sampleMaze, player);
		player.setX(maze.getStartingX());
		player.setY(maze.getStartingY());		
	}
	
	/**
	 * The game is turn-based - the player
	 * first makes their move, then all
	 * enemies make their move.
	 */
	private void nextMove(Direction move) {
		player.move(maze, move);
	}
	
	/**
	 * Plays out a turn with the given player move
	 * @param the direction for the player to move
	 */
	public void move(Direction move) {
		System.out.printf("Moving %s\n",
				move.toString());
		nextMove(move);
	}
	
	/**
	 * 
	 * @return a string representation of the level
	 */
	public String showLevel() {
		return maze.showMaze();
	}
}
