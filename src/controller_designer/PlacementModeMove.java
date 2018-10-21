package controller_designer;

import java.util.ArrayList;

import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

/**
 * 
 * @author Kevin
 * Moving entities occurs in two steps. The user first selects
 * a tile to move entities from (mark stage), and then selects
 * the tile to move these entities to (move stage). Cancelling
 * while in the move stage returns the user to the mark stage
 * without moving the marked entities.
 */
public class PlacementModeMove extends PlacementMode {
	private static final int MARK = 0;
	private static final int MOVE = 1;
	private int stage = MARK;
	
	private int selectedY;
	private int selectedX;
	
	public PlacementModeMove(LevelDesignerMainController controller) {
		super(controller);
	}
	
	@Override
	public void select() {
		if (stage == MARK) {
			selectedY = controller.getCursorY();
			selectedX = controller.getCursorX();
			stage = MOVE;
			controller.setHelpMessage("Select where you want these entities to be moved (ESC to cancel)");
		} else {
			if (controller.getCursorY() != selectedY ||
					controller.getCursorX() != selectedX) {
				for (Entity e: getMaze().getEntities(selectedY, selectedX)) {
					getMaze().placeEntity(controller.getCursorY(),
							              controller.getCursorX(),
							              e);
				}
				getMaze().clearTile(selectedY, selectedX);
				controller.setHelpMessage("ARROW KEYS to select a square, ENTER to move entities, ESC to cancel");
			}
			stage = MARK;
		}
	}
	
	@Override
	public void cancel() {
		if (stage == MOVE) {
			stage = MARK;
		} else {
			quit();
		}
	}
}
