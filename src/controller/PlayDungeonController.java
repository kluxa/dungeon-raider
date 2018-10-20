package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import dungeon.Direction;
import dungeon.Entity;
import dungeon.Maze;
import dungeon.SolidEntity;
import dungeon.Square;
import dungeon.Tile;
import game.Level;
import game.SimpleLevel;
import items.Arrow;
import items.Item;
import items.Sword;
import javafx.animation.FadeTransition;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import player.Inventory;
import player.Player;

public class PlayDungeonController extends Controller {
	private PlayDungeon playDungeon;
	private Player player;
	private Level level;
	private Maze maze;
	
	private boolean firingArrow;
	
	@FXML
	private AnchorPane display;
	@FXML
	private Label helpMessage;
	@FXML
	private Canvas canvas;
	
	private GraphicsContext ctx;
	
	public PlayDungeonController(Stage s, PlayDungeon playDungeon) {
		super(s);
		this.playDungeon = playDungeon;
		this.level = playDungeon.getLevel();
		this.maze = playDungeon.getMaze();
		this.player = level.getPlayer();
	}
	
	@FXML
	private void initialize() {
		System.out.println(getStage().getScene());
		ctx = canvas.getGraphicsContext2D();
		
		ctx.setFont(Font.font("Power Red and Green", FontWeight.NORMAL, 17));
		drawFrame();
	}
	
	@FXML
	private void handleKeyPress(KeyEvent e) {
		System.out.println(e.getCode().toString() + " pressed");
		KeyCode k = e.getCode();
		
		if (k == KeyCode.ESCAPE) {
			playDungeon.pauseGame();
		}
		
		if (firingArrow) {
			fireArrow(k);
		} else {
			switch (k) {
			case UP:    level.move(Direction.UP);          break;
			case DOWN:  level.move(Direction.DOWN);        break;
			case RIGHT: level.move(Direction.RIGHT);       break;
			case LEFT:  level.move(Direction.LEFT);        break;
			case D:     level.dropBomb();                  break;
			case A:     if (player.hasItem(new Arrow())) {
							firingArrow = true;
						}                                  break;
			default:                                       break;
			}
		}
		
		if (level.hasEnded()) {
			if (level.isComplete()) {
				playDungeon.gameWon();

			} else {
				playDungeon.gameOver(player.getCauseOfDeath());
			}
		}
		
		drawFrame();
	}
	
	private void fireArrow(KeyCode k) {
		switch (k) {
		case UP:    level.fireArrow(Direction.UP);    firingArrow = false; break;
		case DOWN:  level.fireArrow(Direction.DOWN);  firingArrow = false; break;
		case RIGHT: level.fireArrow(Direction.RIGHT); firingArrow = false; break;
		case LEFT:  level.fireArrow(Direction.LEFT);  firingArrow = false; break;
		default:                                                           break;
		}
	}
	
	private void drawFrame() {
		clearFrame();
		drawSquares();
		drawFog();
		drawInventory();
		drawObjectives();
		showHelpMessage();
	}
	
	private void clearFrame() {
		ctx.save();
		ctx.setFill(Color.BLACK);
		ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		ctx.restore();
	}
	
	private void drawSquares() {
		Square location = maze.getPlayerLocation();
		int playerY = location.getY();
		int playerX = location.getX();
		
		for (int offsetY = -6; offsetY <= 6; offsetY++) {
			for (int offsetX = -6; offsetX <= 6; offsetX++) {
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
		
		int posY = (int)canvas.getHeight() / 2 + offsetY * 32 - 16;
		int posX = (int)canvas.getWidth()  / 2 + offsetX * 32 - 16;
		
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
	
	private void drawFog() {
		ctx.save();
		ctx.setFill(Color.BLACK);
		int posY = (int)(canvas.getHeight() / 2);
		int posX = (int)(canvas.getWidth()  / 2);
		for (int y = posY - 210; y <= posY + 210; y += 5) {
			for (int x = posX - 210; x <= posX + 210; x += 5) {
				double distance = Math.sqrt((y - posY) * (y - posY) + (x - posX) * (x - posX));
				double opacity = Math.min(0.01 * distance - 0.5, 1);
				ctx.setGlobalAlpha(opacity);
				ctx.fillRect(x, y, 5, 5);
			}
		}
		ctx.restore();
	}
	
	private void drawInventory() {
		////////////////////////////////////////////////////////////////
		// Weapon
		ctx.save();
		
		int posX = 20;
		Inventory inv = player.getInventory();
		ArrayList<Item> items = new ArrayList<>(inv.getItems());
		
		if (inv.hasWeapon()) {
			Sword s = inv.getWeapon();
			items.remove(s);
			
			ctx.setLineWidth(3);
			ctx.setStroke(Color.WHITE);
			ctx.strokeRoundRect(posX, 20, 56, 56, 3, 3);
			
			ctx.setLineWidth(0.5);
			ctx.setFill(Color.YELLOW);
			ctx.setStroke(Color.YELLOW);
			ctx.drawImage(new Image(getImagePath(s)), posX + 10, 30);
			ctx.strokeText(String.valueOf(s.getUsesLeft()), posX + 38, 70);
			ctx.fillText(String.valueOf(s.getUsesLeft()), posX + 38, 70);
			posX += 76;
		}
		
		ctx.restore();
		
		////////////////////////////////////////////////////////////////
		// Other Items
		ctx.save();
		
		ctx.setFill(Color.WHITE);
		while (items.size() > 0) {
			
			Item i = items.get(0);
			int quantity = inv.numItemsOfType(i);
			items.removeIf(thing -> thing.sameType(i));
			ctx.setLineWidth(3);
			if (firingArrow && i instanceof Arrow) {
				ctx.setStroke(Color.LAWNGREEN);
			} else {
				ctx.setStroke(Color.WHITE);
			}
			ctx.strokeRoundRect(posX, 20, 56, 56, 3, 3);
			
			ctx.setLineWidth(0.5);
			ctx.setFill(Color.WHITE);
			ctx.setStroke(Color.WHITE);
			ctx.drawImage(new Image(getImagePath(i)), posX + 10, 30);
			ctx.strokeText(quantity > 9 ? "9+" : String.valueOf(quantity),
				           posX + 38, 70);
			ctx.fillText(quantity > 9 ? "9+" : String.valueOf(quantity),
					     posX + 38, 70);
			posX += 76;
		}
		
		ctx.restore();
		
		////////////////////////////////////////////////////////////////
		// Active Effects
		ctx.save();
		
		if (player.isFlying()) {
			ctx.drawImage(new Image("sprites/wings.png"), posX + 10, 30);
			posX += 84;
		}
		if (player.isInvincible()) {
			ctx.drawImage(new Image("sprites/invincibilitypotion.png"), posX + 10, 30);
		}
		
		ctx.restore();
	}
	
	private void drawObjectives() {
		ctx.save();
		ctx.setFill(Color.WHITE);
		ctx.setFont(Font.font("Power Red and Green", FontWeight.NORMAL, 22));
		
		HashMap<String, Integer> values = new HashMap<>();
		level.getProgress(values);
		int posY = 30;
		for (String objective: values.keySet()) {
			if (objective.equals("treasure")) {
				ctx.fillText("Treasures remaining: ", 640, posY);
			} else if (objective.equals("switches")) {
				ctx.fillText("Switches remaining: ", 640, posY);
			} else if (objective.equals("enemies")) {
				ctx.fillText("Enemies remaining: ", 640, posY);
			}
			ctx.fillText(String.valueOf(values.get(objective)), 850, posY);
			posY += 30;
		}
		
		ctx.restore();
	}
	
	private void showHelpMessage() {
		if (firingArrow) {
			helpMessage.setText("Move in a direction to fire an arrow.");
		} else {
			helpMessage.setText("");
		}
	}
	
	private String getImagePath(Entity e) {
		return "sprites/" + e.getImageName() + ".png";
	}
	
}
