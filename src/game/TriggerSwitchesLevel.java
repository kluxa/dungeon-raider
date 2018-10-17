package game;

public class TriggerSwitchesLevel extends LevelDecorator {
	
	public TriggerSwitchesLevel(Level level) {
		super(level);
	}
	
	@Override
	public boolean isComplete() {
		return getMaze().allSwitchesTriggered() &&
				level.isComplete();
	}
}
