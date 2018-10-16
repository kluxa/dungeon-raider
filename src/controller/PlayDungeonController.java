package controller;

import dungeon.Maze;
import dungeon.Square;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlayDungeonController extends Controller {
	private PlayDungeon playDungeon;
	@FXML
	private AnchorPane display;
	@FXML
	private Label pressedKey;
	
	private Maze maze;
	
	public PlayDungeonController(Stage s, PlayDungeon playDungeon) {
		super(s);
		this.playDungeon = playDungeon;
	}
	
	@FXML
	private void initialize() {
		System.out.println(getStage().getScene());
	}
	
	@FXML
	private void handleKeyPress(KeyEvent e) {
		System.out.println(getStage().getScene());
		System.out.println(e.getCode().toString() + " pressed");
		
		KeyCode k = e.getCode();
		
		if (k.equals(KeyCode.ESCAPE)) {
			playDungeon.pauseGame();
		} else if (k.equals(KeyCode.L)) {
			playDungeon.gameOver();
		} else if (k.equals(KeyCode.W)) {
			playDungeon.gameWon();
		}
		
	}
	
	private void drawFrame() {
		getStage().getScene().setFill(Color.BLACK);
		Square location = maze.getPlayerLocation();
		int playerY = location.getY();
		int playerX = location.getX();
		
		for (int offsetY = -8; offsetY <= 8; offsetY++) {
			for (int offsetX = -8; offsetX <= 8; offsetX++) {
				int y = playerY + offsetY;
				int x = playerX + offsetX;
				drawSquare(y, x, offsetY, offsetX);
			}
		}
	}
	
	private void drawSquare(int y, int x, int offsetY, int offsetX) {
		
		
	}
	
}
