package designer;

import java.util.Scanner;

import factory.*;
import dungeon.*;
import enemies.*;
import player.*;
import items.*;
import game.*;

public class SelectionMode implements DesignerMode {
	private final static int PANEL_HEIGHT = 10;
	private final static int PANEL_WIDTH  = 4;
	
	/**
	 * This is the layout of the side panel
	 * 
	 * [Path        ][Pit         ][Floor Switch][Exit        ] <-- Tiles
	 * [Wall        ][Door        ][Boulder     ][            ] <-- Solid Entities
	 * [Hunter      ][Strategist  ][Hound       ][Coward      ] <-- Solid Entities
	 * [Treasure    ][Key         ][Unlit Bomb  ][Sword       ] <-- Items
	 * [Arrow       ][Hover Potion][Super Potion][            ] <-- Items
	 * [           MOVE           ][          DELETE          ]
	 * [Collect Treasure          ][Trigger Switches          ] <-- Completion Objectives
	 * [Defeat Enemies            ][                          ] <-- Completion Objectives
	 * [                         TEST                         ]
	 * [                         QUIT                         ]
	 * 
	 */
	private static final EntityFactory[][] FACTORIES = {
			{new PathFactory(),     new PitFactory(),         new FloorSwitchFactory(),         new ExitFactory()  }, // <-- Tiles
			{new WallFactory(),     new DoorFactory(),        new BoulderFactory(),             null               }, // <-- Solid Entities
			{new HunterFactory(),   new StrategistFactory(),  new HoundFactory(),               new CowardFactory()}, // <-- Solid Entities
			{new TreasureFactory(), new KeyFactory(),         new UnlitBombFactory(),           new SwordFactory() }, // <-- Items
			{new ArrowFactory(),    new HoverPotionFactory(), new InvincibilityPotionFactory(), null               }  // <-- Items
	};
	
	private LevelDesigner levelDesigner;
	private Maze maze;
	
	private int cursorY;
	private int cursorX;
	
	private boolean treasuresObjective;
	private boolean floorSwitchObjective;
	private boolean defeatEnemiesObjective;
	
	public SelectionMode(LevelDesigner levelDesigner) {
		this.levelDesigner = levelDesigner;
		maze = levelDesigner.getMaze();
		
		cursorY = 0;
		cursorX = 0;
		
		treasuresObjective = false;
		floorSwitchObjective = false;
		defeatEnemiesObjective = false;
	}
	
	@Override
	public void moveCursorUp() {
		cursorY--;
		if (cursorY < 0) {
			cursorY += PANEL_HEIGHT;
		}
	}
	
	@Override
	public void moveCursorLeft() {
		cursorX--;
		if (cursorX < 0) {
			cursorX += PANEL_WIDTH;
		}
	}
	
	@Override
	public void moveCursorDown() {
		cursorY = (cursorY + 1) % PANEL_HEIGHT;
	}
	
	@Override
	public void moveCursorRight() {
		cursorX = (cursorX + 1) % PANEL_WIDTH;
	}
	
	/**
	 * The user selects an option from the panel
	 */
	@Override
	public void select() {
		if (cursorY < 5 && FACTORIES[cursorY][cursorX] != null) {
			// Factories, factories, more factories!
			EntityFactory factory = FACTORIES[cursorY][cursorX];
			if (factory instanceof DoorFactory || factory instanceof KeyFactory) {
				String color = chooseColor();
				if (color == null) return;
				factory.setColor(color);
			}
			if (maze.allowedToPlace(factory.createEntity())) {
				levelDesigner.setMode(new PlacementModePlace(levelDesigner, factory));				
			} else {
				System.out.println("You can't place this entity.");
			}
		} else {
			switch (cursorY) {
			case 5: moveOrDelete();  break;
			case 6: setObjective1(); break;
			case 7: setObjective2(); break;
			case 8: testLevel();     break;
			case 9: quit();          break;
			}
		}
	}
	
	/**
	 * 
	 * @return a color
	 */
	private String chooseColor() {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.printf("Enter a color: ");
		String input = reader.nextLine();
		
		if (input.equalsIgnoreCase("red") || input.equalsIgnoreCase("yellow") ||
			input.equalsIgnoreCase("green") || input.equalsIgnoreCase("blue")) {
			return input;
		} else {
			System.out.println("Invalid color");
			return null;
		}
	}
	
	private void moveOrDelete() {
		switch (cursorX) {
		case 0: case 1: levelDesigner.setMode(new PlacementModeMove(levelDesigner));   break;
		case 2: case 3: levelDesigner.setMode(new PlacementModeDelete(levelDesigner)); break;
		}
	}
	
	private void setObjective1() {
		switch (cursorX) {
		case 0: case 1: treasuresObjective = !treasuresObjective;
		case 2: case 3: floorSwitchObjective = !floorSwitchObjective;
		}
	}
	
	private void setObjective2() {
		switch (cursorX) {
		case 0: case 1: defeatEnemiesObjective = !defeatEnemiesObjective;
		}
	}
	
	private void testLevel() {
		// TODO
	}
	
	private void quit() {
		levelDesigner.quit();
	}
	
	@Override
	public void cancel() {
		// Do nothing
	}
	
	@Override
	public void delete() {
		// Do nothing
	}
}
