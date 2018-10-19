package game;

public class CollectTreasureLevel extends LevelDecorator {

	public CollectTreasureLevel(Level level) {
		super(level);
	}

	@Override
	public boolean isComplete() {
		return getMaze().allTreasuresCollected() &&
				level.isComplete();
	}
}