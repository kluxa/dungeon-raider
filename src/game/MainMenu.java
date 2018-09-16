package game;

import designer.LevelDesigner;
import designer.LevelDesignerController;

public class MainMenu {
	private static final int PANEL_HEIGHT = 3;
	private static final int PANEL_WIDTH  = 1;
	
	private int cursorY;
	private int cursorX;
	
	private boolean hasExited;
	
	public MainMenu() {
		cursorY = 0;
	}
	
	public void moveCursorUp() {
		cursorY--;
		if (cursorY < 0) {
			cursorY += PANEL_HEIGHT;
		}
	}
	
	public void moveCursorDown() {
		cursorY = (cursorY + 1) % PANEL_HEIGHT;
	}
	
	public void select() {
		switch (cursorY) {
		
		// Play Mode
		case 0:
			System.out.println("Entering play mode...");
			
		
		// Design Mode
		case 1:
			System.out.println("Entering the level designer...");
			LevelDesigner designer = new LevelDesigner();
			LevelDesignerController controller = new LevelDesignerController(designer);
			controller.run();
			break;
		
		// Quit
		case 2:
			break;
		}
	}
	
}
