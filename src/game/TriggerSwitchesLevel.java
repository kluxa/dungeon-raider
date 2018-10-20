package game;

import java.util.ArrayList;
import java.util.HashMap;

import items.Treasure;

public class TriggerSwitchesLevel extends LevelDecorator {
	
	public TriggerSwitchesLevel(Level level) {
		super(level);
	}
	
	@Override
	public void getObjective(ArrayList<String> objs) {
		level.getObjective(objs);
		objs.add("switches");
	}
	
	@Override
	public void getProgress(HashMap<String, Integer> values) {
		level.getProgress(values);
		values.put("switches", getMaze().getNumUntriggeredSwitches());
	}
	
	@Override
	public boolean isComplete() {
		return getMaze().allSwitchesTriggered() &&
				level.isComplete();
	}
}