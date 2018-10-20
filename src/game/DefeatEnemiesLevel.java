package game;

import java.util.HashMap;

public class DefeatEnemiesLevel extends LevelDecorator {
	
	public DefeatEnemiesLevel(Level level) {
		super(level);
	}
	
	@Override
	public void getProgress(HashMap<String, Integer> values) {
		level.getProgress(values);
		values.put("enemies", getMaze().getNumEnemies());
	}
	
	@Override
	public boolean isComplete() {
		return getMaze().allEnemiesDefeated() &&
				level.isComplete();
	}
}