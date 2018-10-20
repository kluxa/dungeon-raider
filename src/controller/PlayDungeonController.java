package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

import dungeon.*;
import game.*;
import items.*;
import player.*;

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
	
	private KeyCode upKey;
	private KeyCode downKey;
	private KeyCode rightKey;
	private KeyCode leftKey;
	private KeyCode dropBombKey;
	private KeyCode fireArrowKey;
	
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
		readKeyBindings();
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
			if (k == upKey) {
				level.move(Direction.UP);
			} else if (k == downKey) {
				level.move(Direction.DOWN);
			} else if (k == rightKey) {
				level.move(Direction.RIGHT);
			} else if (k == leftKey) {
				level.move(Direction.LEFT);
			} else if (k == dropBombKey) {
				level.dropBomb();
			} else if (k == fireArrowKey) {
				if (player.hasItem(new Arrow())) {
					firingArrow = true;
				}
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
		if (k == upKey) {
			level.fireArrow(Direction.UP);
			firingArrow = false;
		} else if (k == downKey) {
			level.fireArrow(Direction.DOWN);
			firingArrow = false;
		} else if (k == rightKey) {
			level.fireArrow(Direction.RIGHT);
			firingArrow = false;
		} else if (k == leftKey) {
			level.fireArrow(Direction.LEFT);
			firingArrow = false;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Drawing the frame
	
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
		////////////////////////////////////////////////////////////////////////
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
		
		////////////////////////////////////////////////////////////////////////
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
			if (!(i instanceof Key)) {
				ctx.strokeText(quantity > 9 ? "9+" : String.valueOf(quantity),
					           posX + 38, 70);
				ctx.fillText(quantity > 9 ? "9+" : String.valueOf(quantity),
						     posX + 38, 70);
			}
			posX += 76;
		}
		
		ctx.restore();
		
		////////////////////////////////////////////////////////////////////////
		// Active Effects
		ctx.save();
		
		if (player.isFlying()) {
			ctx.drawImage(new Image("game_files/sprites/wings.png"), posX, 30);
			posX += 74;
		}
		if (player.isInvincible()) {
			ctx.drawImage(new Image("game_files/sprites/invincibilitypotion.png"), posX, 30);
		}
		
		ctx.restore();
	}
	
	private void drawObjectives() {
		ctx.save();
		ctx.setFill(Color.WHITE);
		ctx.setFont(Font.font("Power Red and Green", FontWeight.NORMAL, 22));
		
		HashMap<String, Integer> values = new HashMap<>();
		level.getProgress(values);
		ctx.fillText("Objectives: ", 600, 30);
		int posY = 60;
		if (values.size() == 0) {
			ctx.fillText("Reach the exit!", 600, posY);
		} else {
			for (String objective: values.keySet()) {
				if (objective.equals("treasure")) {
					ctx.fillText("Collect all treasure!", 600, posY);
				} else if (objective.equals("switches")) {
					ctx.fillText("Trigger all switches!", 600, posY);
				} else if (objective.equals("enemies")) {
					ctx.fillText("Destroy all enemies!", 600, posY);
				}
				ctx.fillText("(" + String.valueOf(values.get(objective)) + " left)", 800, posY);
				posY += 30;
			}
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
		return "game_files/sprites/" + e.getImageName() + ".png";
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Reading in key bindings
	
	private void readKeyBindings() {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("src/game_files/bindings.txt"));
			while (sc.hasNextLine()) {
				readKeyBinding(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} finally {
			if (sc != null) sc.close();
		}
	}
	
	private void readKeyBinding(String line) {
		String[] pair = line.split(" ");
		KeyCode k = KeyCode.valueOf(pair[1]);
		switch (pair[0]) {
		case "Up":        upKey = k;        break;
		case "Down":      downKey = k;      break;
		case "Right":     rightKey = k;     break;
		case "Left":      leftKey = k;      break;
		case "DropBomb":  dropBombKey = k;  break;
		case "FireArrow": fireArrowKey = k; break;
		}
	}
}
