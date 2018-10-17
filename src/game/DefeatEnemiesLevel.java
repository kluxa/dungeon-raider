package game;

public class DefeatEnemiesLevel extends LevelDecorator {
	
	public DefeatEnemiesLevel(Level level) {
		super(level);
	}
	
	@Override
	public boolean isComplete() {
		return getMaze().allEnemiesDefeated() &&
				level.isComplete();
	}
}
