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
import factory.*;
import game.Level;
import items.Item;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import player.Player;

public class LevelDesignerController extends Controller {
	private DesignerHandler designerHandler;
	private PlacementMode placementMode;
	
	@FXML
	private AnchorPane selectPane;
	@FXML
	private Label helpMessage;
	@FXML
	private Canvas canvas;
	
	private Node selectedButton;
	private GraphicsContext ctx;
	private Maze maze;
	
	private int mazeCursorY;
	private int mazeCursorX;
	
	private boolean inPlacementMode;
	
	public LevelDesignerController(Stage s, Level level) {
		super(s);
		// TODO
	}
	
	public LevelDesignerController(Stage s, DesignerHandler designerHandler) {
		super(s);
		this.designerHandler = designerHandler;
		maze = new Maze(15, 15);
		
		mazeCursorY = 7;
		mazeCursorX = 7;
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	@FXML
	private void initialize() {
		ctx = canvas.getGraphicsContext2D();
		
		helpMessage.setText("Choose an entity to place by clicking on it");
		
		selectPane.setMouseTransparent(false);
		
		drawFrame();
	}
	
	@FXML
	private void handleKeyPress(KeyEvent e) {
		e.consume();
		KeyCode k = e.getCode();
		if (inPlacementMode) {
			
			switch (k) {
			case UP:     mazeCursorUp();         break;
			case DOWN:   mazeCursorDown();       break;
			case RIGHT:  mazeCursorRight();      break;
			case LEFT:   mazeCursorLeft();       break;
			
			case ENTER:  placementMode.select(); break;
			case DELETE: placementMode.delete(); break;
			case ESCAPE: placementMode.cancel(); break;
			default:                             break;
			}
			
			drawFrame();
		}
	}
	
	public void setHelpMessage(String message) {
		helpMessage.setText(message);
	}
	
	public void switchToSelectionMode() {
		inPlacementMode = false;
		selectedButton.getStyleClass().remove("selected");
		helpMessage.setText("Choose an entity to place by clicking on it");
		selectPane.setMouseTransparent(false);
	}
	
	public void switchToPlacementPlaceMode(EntityFactory factory) {
		selectPane.setMouseTransparent(true);
		helpMessage.setText("ARROW KEYS to select a square, ENTER to place, DEL to delete, ESC to cancel");
		placementMode = new PlacementModePlace(this, factory);
		inPlacementMode = true;
		drawFrame();
	}
	
	public void switchToPlacementMoveMode() {
		selectPane.setMouseTransparent(true);
		helpMessage.setText("ARROW KEYS to select a square, ENTER to move entities, ESC to cancel");
		placementMode = new PlacementModeMove(this);
		inPlacementMode = true;
		drawFrame();
	}
	
	public void switchToPlacementDeleteMode() {
		selectPane.setMouseTransparent(true);
		helpMessage.setText("ARROW KEYS to select a square, ENTER/DEL to delete, ESC to cancel");
		placementMode = new PlacementModeDelete(this);
		inPlacementMode = true;
		drawFrame();
	}
	
	private void highlightButton(Node button) {
		button.getStyleClass().add("selected");
		selectedButton = button;
	}
	
	private void mazeCursorUp() {
		if (mazeCursorY > 0) {
			mazeCursorY--;
		}
	}
	
	private void mazeCursorDown() {
		if (mazeCursorY < maze.getHeight() - 1) {
			mazeCursorY++;
		}
	}
	
	private void mazeCursorRight() {
		if (mazeCursorX < maze.getWidth()  - 1) {
			mazeCursorX++;
		}
	}
	
	private void mazeCursorLeft() {
		if (mazeCursorX > 0) {
			mazeCursorX--;
		}
	}
	
	public int getCursorY() {
		return mazeCursorY;
	}
	
	public int getCursorX() {
		return mazeCursorX;
	}
	
	
	// It's not pretty...
	// But this is a OO course, not a JavaFX course.
	// Please forgive me.
	
	@FXML
	private void handleButton00() {
		highlightButton(button00);
		switchToPlacementPlaceMode(new PathFactory());
	}
	
	@FXML
	private void handleButton01() {
		highlightButton(button01);
		switchToPlacementPlaceMode(new PitFactory());
	}
	
	@FXML
	private void handleButton02() {
		highlightButton(button02);
		switchToPlacementPlaceMode(new FloorSwitchFactory());
	}
	
	@FXML
	private void handleButton03() {
		highlightButton(button03);
		switchToPlacementPlaceMode(new ExitFactory());
	}
	
	@FXML
	private void handleButton10() {
		highlightButton(button10);
		switchToPlacementPlaceMode(new WallFactory());
	}
	
	@FXML
	private void handleButton110() {
		highlightButton(button11);
		EntityFactory f = new DoorFactory();
		f.setColor("red");
		switchToPlacementPlaceMode(f);
	}
	
	@FXML
	private void handleButton111() {
		highlightButton(button11);
		EntityFactory f = new DoorFactory();
		f.setColor("yellow");
		switchToPlacementPlaceMode(f);
	}
	
	@FXML
	private void handleButton112() {
		highlightButton(button11);
		EntityFactory f = new DoorFactory();
		f.setColor("green");
		switchToPlacementPlaceMode(f);
	}
	
	@FXML
	private void handleButton113() {
		highlightButton(button11);
		EntityFactory f = new DoorFactory();
		f.setColor("blue");
		switchToPlacementPlaceMode(f);
	}
	
	@FXML
	private void handleButton12() {
		highlightButton(button12);
		switchToPlacementPlaceMode(new BoulderFactory());
	}
	
	@FXML
	private void handleButton20() {
		highlightButton(button20);
		switchToPlacementPlaceMode(new HunterFactory());
	}
	
	@FXML
	private void handleButton21() {
		highlightButton(button21);
		switchToPlacementPlaceMode(new StrategistFactory());
	}
	
	@FXML
	private void handleButton22() {
		highlightButton(button22);
		switchToPlacementPlaceMode(new HoundFactory());
	}
	
	@FXML
	private void handleButton23() {
		highlightButton(button23);
		switchToPlacementPlaceMode(new CowardFactory());
	}
	
	@FXML
	private void handleButton30() {
		highlightButton(button30);
		switchToPlacementPlaceMode(new TreasureFactory());
	}
	
	@FXML
	private void handleButton310() {
		highlightButton(button31);
		EntityFactory f = new KeyFactory();
		f.setColor("red");
		switchToPlacementPlaceMode(f);
	}
	
	@FXML
	private void handleButton311() {
		highlightButton(button31);
		EntityFactory f = new KeyFactory();
		f.setColor("yellow");
		switchToPlacementPlaceMode(f);
	}
	
	@FXML
	private void handleButton312() {
		highlightButton(button31);
		EntityFactory f = new KeyFactory();
		f.setColor("green");
		switchToPlacementPlaceMode(f);
	}
	
	@FXML
	private void handleButton313() {
		highlightButton(button31);
		EntityFactory f = new KeyFactory();
		f.setColor("blue");
		switchToPlacementPlaceMode(f);
	}
	
	@FXML
	private void handleButton32() {
		highlightButton(button32);
		switchToPlacementPlaceMode(new UnlitBombFactory());
	}
	
	@FXML
	private void handleButton33() {
		highlightButton(button33);
		switchToPlacementPlaceMode(new SwordFactory());
	}
	
	@FXML
	private void handleButton40() {
		highlightButton(button40);
		switchToPlacementPlaceMode(new ArrowFactory());
	}
	
	@FXML
	private void handleButton41() {
		highlightButton(button41);
		switchToPlacementPlaceMode(new HoverPotionFactory());
	}
	
	@FXML
	private void handleButton42() {
		highlightButton(button42);
		switchToPlacementPlaceMode(new InvincibilityPotionFactory());
	}
	
	@FXML
	private void handleButton50() {
		highlightButton(button50);
		switchToPlacementMoveMode();
	}
	
	@FXML
	private void handleButton52() {
		highlightButton(button52);
		switchToPlacementDeleteMode();
	}
	
	@FXML
	private void handleButton70() {
		System.out.println("Testing a level");
		// TODO
	}
	
	@FXML
	private void handleButton80() {
		System.out.println("Loading a level");
		// TODO
	}
	
	@FXML
	private void handleButton82() {
		System.out.println("Saving the level");
		// TODO
	}
	
	@FXML
	private void handleButton90() {
		System.out.println("Exiting the level");
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
		
		for (int offsetY = -30; offsetY <= 30; offsetY++) {
			for (int offsetX = -30; offsetX <= 30; offsetX++) {
				int y = mazeCursorY + offsetY;
				int x = mazeCursorX + offsetX;
				drawSquare(y, x, offsetY, offsetX);
			}
		}
		
		if (inPlacementMode) {
			int posY = (int)canvas.getHeight() / 2 - 16;
			int posX = (int)canvas.getWidth()  / 2 - 16;
			
			ctx.setLineWidth(2);
			ctx.setStroke(Color.GOLD);
            ctx.strokeRect(posX, posY, 32, 32);
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
	
	private String getImagePath(Entity e) {
		return "sprites/" + e.getImageName() + ".png";
	}
}
