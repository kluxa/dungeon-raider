package game;

import java.util.HashMap;

import items.Treasure;

public class CollectTreasureLevel extends LevelDecorator {

	public CollectTreasureLevel(Level level) {
		super(level);
	}
	
	@Override
	public void getProgress(HashMap<String, Integer> values) {
		level.getProgress(values);
		values.put("treasure", getMaze().getNumOfItem(new Treasure()));
	}
	
	@Override
	public boolean isComplete() {
		return getMaze().allTreasuresCollected() &&
				level.isComplete();
	}
}