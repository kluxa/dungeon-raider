package designer;

import java.util.Scanner;

public class LevelDesignerController {
	// private Game game;
	private LevelDesigner model;
	
	public LevelDesignerController(LevelDesigner model) {
		this.model = model;
	}
	
	public void run() {
		Scanner reader = new Scanner(System.in);
		String input;
		while (model.keepRunning()) {
			System.out.printf("> ");
			input = reader.nextLine();
			
			switch (input) {
			case "W": case "w": model.moveCursorUp();       break;
			case "A": case "a": model.moveCursorLeft();     break;
			case "S": case "s": model.moveCursorDown();     break;
			case "D": case "d": model.moveCursorRight();    break;
			case "J": case "j": model.select();             break;
			case "K": case "k": model.cancel();             break;
			case "L": case "l": model.delete();             break;
			default:  System.out.println("Invalid action"); break;
			}
			
			System.out.println(model.getMaze());
		}
		reader.close();
	}
	
}
