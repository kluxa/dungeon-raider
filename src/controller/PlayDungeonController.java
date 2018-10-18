package controller;

import java.util.ArrayList;

import dungeon.Direction;
import dungeon.Entity;
import dungeon.Maze;
import dungeon.SolidEntity;
import dungeon.Square;
import dungeon.Tile;
import game.Level;
import game.SimpleLevel;
import items.Item;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlayDungeonController extends Controller {
	private PlayDungeon playDungeon;
	private Level level;
	private Maze maze;
	
	@FXML
	private AnchorPane display;
	@FXML
	private Label pressedKey;
	@FXML
	private Canvas canvas;
	
	private GraphicsContext ctx;
	
	public PlayDungeonController(Stage s, PlayDungeon playDungeon) {
		super(s);
		this.playDungeon = playDungeon;
		this.level = playDungeon.getLevel();
		this.maze = playDungeon.getMaze();
	}
	
	@FXML
	private void initialize() {
		System.out.println(getStage().getScene());
		ctx = canvas.getGraphicsContext2D();
		drawFrame();
	}
	
	@FXML
	private void handleKeyPress(KeyEvent e) {
		System.out.println(e.getCode().toString() + " pressed");
		
		KeyCode k = e.getCode();
		
		if (k.equals(KeyCode.ESCAPE)) {
			playDungeon.pauseGame();
		} else if (k.equals(KeyCode.UP)) {
			level.move(Direction.UP);
		} else if (k.equals(KeyCode.DOWN)) {
			level.move(Direction.DOWN);
		} else if (k.equals(KeyCode.RIGHT)) {
			level.move(Direction.RIGHT);
		} else if (k.equals(KeyCode.LEFT)) {
			level.move(Direction.LEFT);
		} else if (k.equals(KeyCode.W)) {
			playDungeon.gameWon();
		} else if (k.equals(KeyCode.L)) {
			playDungeon.gameOver();
		}
		
		if (level.isComplete()) {
			System.out.println("Complete");
		} else {
			System.out.println("Not yet complete");
		}
		drawFrame();
	}
	
	private void drawFrame() {
		ctx.setFill(Color.BLACK);
		ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
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
		if (y < 0 || y >= maze.getHeight()) return;
		if (x < 0 || x >= maze.getWidth())  return;
		Square s = maze.getSquare(y, x);
		
		int posY = 300 + offsetY * 32;
		int posX = 450 + offsetX * 32;
		
		Tile t = s.getTile();
		ArrayList<Item> items = s.getItems();
		ArrayList<SolidEntity> entities = s.getOccupants();
		ctx.drawImage(new Image(getImagePath(t)), posX, posY);
		
		for (Entity e: items) {
			ctx.drawImage(new Image(getImagePath(e)), posX, posY);
		}
		
		for (Entity e: entities) {
			ctx.drawImage(new Image(getImagePath(e)), posX, posY);
		}
	}
	
	private String getImagePath(Entity e) {
		return "sprites/" + e.getImageName() + ".png";
	}
	
}
