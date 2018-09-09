package dungeon;

public abstract class Potion extends Item {

	public Potion() {
		// TODO Auto-generated constructor stub
	}
	
	public void pickUp(Player player) {
		applyEffect(player);
	}
	
	public abstract void applyEffect(Player player);
	
}
