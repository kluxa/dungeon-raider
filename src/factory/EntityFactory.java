package factory;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public abstract class EntityFactory {
	
	// For door/key
	private String color;
	
	public abstract Entity createEntity();
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
}
