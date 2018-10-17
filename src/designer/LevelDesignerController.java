package designer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Controller;
import controller.PlayDungeon;
import dungeon.Entity;
import dungeon.Maze;
import dungeon.SolidEntity;
import dungeon.Square;
import dungeon.Tile;
import items.Item;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import player.Player;

public class LevelDesignerController extends Controller {
	private DesignerHandler designerHandler;
	
	@FXML
	private Label helpMessage;
	@FXML
	private Canvas canvas;
	
	private GraphicsContext ctx;
	private Object[][] buttons;
	private Maze maze;
	
	private int mazeCursorY;
	private int mazeCursorX;
	
	public LevelDesignerController(Stage s, DesignerHandler designerHandler) {
		super(s);
		this.designerHandler = designerHandler;
		maze = new Maze(15, 15);
		
		mazeCursorY = 8;
		mazeCursorX = 8;
	}
	
	private boolean button11focused = false;
	private boolean button11selected = false;
	
	@FXML
	private void initialize() {
		Object[][] buttons = {
				{ button00, button01, button02, button03 },
				{ button10, button11, button02, button03 },
				{ button20, button21, button02, button03 },
				{ button30, button31, button02, button03 },
				{ button40, button41, button02, button03 },
				{ button50, button50, button52, button52 },
				{ button60, button60, button60, button60 },
				{ button70, button70, button70, button70 },
				{ button80, button80, button82, button82 },
				{ button90, button90, button90, button90 },
		};
		this.buttons = buttons;
		
		ctx = canvas.getGraphicsContext2D();
		
		button11.addEventHandler(KeyEvent.ANY, e -> handleDoorButton(e));
		
		drawFrame();
	}
	
	private void handleDoorButton(KeyEvent e) {
		if (button11selected) {
			if (e.getCode() == KeyCode.DOWN) {
				button11focused = false;
			}
		}
		else if (button11focused) {
			if (e.getCode() == KeyCode.ENTER) {
				button11selected = true;
			}
			if (e.getCode() == KeyCode.DOWN) {
				e.consume();
				button21.requestFocus();
				button11focused = false;
			}
		} else {
			button11focused = true;
		}
	}
	
	@FXML
	private void handleKeyPress(KeyEvent e) {
		if (!button11.isFocused()) {
			button11focused = false;
			button11selected = false;
		}
		System.out.println("You pressed " + e.getCode().toString());
		
		if (getStage().getScene().getFocusOwner() instanceof MenuButton) {
			System.out.println("You suck!");
		}
	}
	
	
	
	@FXML
	private Button button00;
	@FXML
	private Button button01;
	@FXML
	private Button button02;
	@FXML
	private Button button03;
	@FXML
	private Button button10;
	@FXML
	private MenuButton button11;
	@FXML
	private Button button12;
	@FXML
	private Button button13;
	@FXML
	private Button button20;
	@FXML
	private Button button21;
	@FXML
	private Button button22;
	@FXML
	private Button button23;
	@FXML
	private Button button30;
	@FXML
	private MenuButton button31;
	@FXML
	private Button button32;
	@FXML
	private Button button33;
	@FXML
	private Button button40;
	@FXML
	private Button button41;
	@FXML
	private Button button42;
	@FXML
	private Button button43;
	@FXML
	private Button button50;
	@FXML
	private Button button52;
	@FXML
	private MenuButton button60;
	@FXML
	private Button button70;
	@FXML
	private Button button80;
	@FXML
	private Button button82;
	@FXML
	private Button button90;
	
	private void drawFrame() {
		ctx.setFill(Color.BLACK);
		ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		for (int offsetY = -15; offsetY <= 15; offsetY++) {
			for (int offsetX = -15; offsetX <= 15; offsetX++) {
				int y = mazeCursorY + offsetY;
				int x = mazeCursorX + offsetX;
				drawSquare(y, x, offsetY, offsetX);
			}
		}
	}
	
	private void drawSquare(int y, int x, int offsetY, int offsetX) {
		if (y < 0 || y >= maze.getHeight()) return;
		if (x < 0 || x >= maze.getWidth())  return;
		Square s = maze.getSquare(y, x);
		
		int posY = (int)canvas.getHeight() / 2 + offsetY * 32 + 16;
		int posX = (int)canvas.getWidth()  / 2 + offsetX * 32 + 16;
		
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
