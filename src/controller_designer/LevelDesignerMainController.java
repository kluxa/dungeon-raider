package controller_designer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Controller;
import controller_ingame.PlayDungeon;
import dungeon.Entity;
import dungeon.Maze;
import dungeon.SolidEntity;
import dungeon.Square;
import dungeon.Tile;
import factory.*;
import fileIO.MazeToFileWriter;
import game.Level;
import game.SimpleLevel;
import items.Item;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import player.Player;

public class LevelDesignerMainController extends Controller {
	private DesignerHandler designerHandler;
	private PlacementMode placementMode;
	
	@FXML
	private AnchorPane selectPane;
	@FXML
	private Label helpMessage;
	@FXML
	private Canvas canvas;
	@FXML
	private HBox display;
	@FXML
	private Button nothing;
	
	@FXML
	private CheckBox collectTreasureCheckBox;
	@FXML
	private CheckBox triggerSwitchesCheckBox;
	@FXML
	private CheckBox defeatEnemiesCheckBox;
	
	private boolean collectTreasure;
	private boolean triggerSwitches;
	private boolean defeatEnemies;
	
	private Level level;
	private String levelName;
	private Node selectedButton;
	private GraphicsContext ctx;
	private Maze maze;
	
	private int mazeCursorY;
	private int mazeCursorX;
	
	private boolean inPlacementMode;
	
	public LevelDesignerMainController(Stage s, DesignerHandler designerHandler,
			                           String levelName, Level level) {
		super(s);
		this.designerHandler = designerHandler;
		this.levelName = levelName;
		maze = level.getMaze();
		
		mazeCursorY = maze.getHeight() / 2;
		mazeCursorX = maze.getWidth()  / 2;
		
		ArrayList<String> objectives = new ArrayList<>();
		level.getObjective(objectives);
		collectTreasure = (objectives.contains("treasure"));
		triggerSwitches = (objectives.contains("switches"));
		defeatEnemies =   (objectives.contains("enemies"));
	}
	
	public LevelDesignerMainController(Stage s, DesignerHandler designerHandler,
			                           int height, int width) {
		super(s);
		this.designerHandler = designerHandler;
		this.levelName = null;
		maze = new Maze(height, width);
		maze.setStart(height / 2, width / 2);
		
		maze.setPlayer(new Player());
		
		mazeCursorY = height / 2;
		mazeCursorX = width  / 2;
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	@FXML
	private void initialize() {
		ctx = canvas.getGraphicsContext2D();
		
		helpMessage.setText("Choose an entity to place by clicking on it");
		
		selectPane.setMouseTransparent(false);
		
		if (collectTreasure) collectTreasureCheckBox.setSelected(true);
		if (triggerSwitches) triggerSwitchesCheckBox.setSelected(true);
		if (defeatEnemies)   defeatEnemiesCheckBox.setSelected(true);
		
		drawFrame();
		
		display.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.SPACE) {
				handleKeyPress(e);
			}
		});
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
			
			case SPACE:  placementMode.select(); break;
			case DELETE: placementMode.delete(); break;
			case ESCAPE: placementMode.cancel(); break;
			default:                             break;
			}
			
			drawFrame();
		} else {
			if (k == KeyCode.ESCAPE) {
				quit();
			}
		}
	}
	
	public void setHelpMessage(String message) {
		helpMessage.setText(message);
	}
	
	public void switchToSelectionMode() {
		inPlacementMode = false;
		selectedButton.getStyleClass().remove("selected");
		helpMessage.setText("Choose an entity to place by clicking it");
		selectPane.setMouseTransparent(false);
	}
	
	public void switchToPlacementPlaceMode(EntityFactory factory) {
		nothing.requestFocus();
		selectPane.setMouseTransparent(true);
		helpMessage.setText("ARROW KEYS to select a square, SPACE to place, DEL to delete, ESC to cancel");
		placementMode = new PlacementModePlace(this, factory);
		inPlacementMode = true;
		drawFrame();
	}
	
	public void switchToPlacementMoveMode() {
		selectPane.setMouseTransparent(true);
		helpMessage.setText("ARROW KEYS to select a square, SPACE to move entities, ESC to cancel");
		placementMode = new PlacementModeMove(this);
		inPlacementMode = true;
		drawFrame();
	}
	
	public void switchToPlacementDeleteMode() {
		selectPane.setMouseTransparent(true);
		helpMessage.setText("ARROW KEYS to select a square, SPACE to delete, ESC to cancel");
		placementMode = new PlacementModeDelete(this);
		inPlacementMode = true;
		drawFrame();
	}
	
	public void quit() {
		nothing.setVisible(false);
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), display);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.play();
		fadeOut.setOnFinished(e -> {
			designerHandler.quit();
			display.setOpacity(1.0);
		});
	}
	
	private void highlightButton(Node button) {
		button.getStyleClass().add("selected");
		selectedButton = button;
	}
	
	private void mazeCursorUp() {
		if (mazeCursorY > 1) {
			mazeCursorY--;
		}
	}
	
	private void mazeCursorDown() {
		if (mazeCursorY < maze.getHeight() - 2) {
			mazeCursorY++;
		}
	}
	
	private void mazeCursorRight() {
		if (mazeCursorX < maze.getWidth()  - 2) {
			mazeCursorX++;
		}
	}
	
	private void mazeCursorLeft() {
		if (mazeCursorX > 1) {
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
	private void handleButton430() {
		highlightButton(button43);
		EntityFactory f = new RedTorchFactory();
		switchToPlacementPlaceMode(f);
	}
	
	@FXML
	private void handleButton431() {
		highlightButton(button43);
		EntityFactory f = new YellowTorchFactory();
		switchToPlacementPlaceMode(f);
	}
	
	@FXML
	private void handleButton432() {
		highlightButton(button43);
		EntityFactory f = new BlueTorchFactory();
		switchToPlacementPlaceMode(f);
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
		testLevel();
		System.out.println("Testing the level");
		designerHandler.switchToTestingDungeon();
	}
	
	@FXML
	private void handleButton80() {
		saveLevel();
	}
	
	private void testLevel() {
		Level level = buildLevel();
		MazeToFileWriter.writeMazeToFile("./src/game_files/levels/custom/" +
		                                 ".temp.txt", level);
		designerHandler.switchToTestingDungeon();
	}
	
	private void saveLevel() {
		Level level = buildLevel();
		
		if (levelName != null) {
			saveExistingLevel(level);
		} else {
			designerHandler.switchToNameScreen();
		}
	}
	
	private Level buildLevel() {
		level = new SimpleLevel.LevelBuilder(maze)
		        .collectTreasure(collectTreasureCheckBox.isSelected())
		        .triggerSwitches(triggerSwitchesCheckBox.isSelected())
		        .defeatEnemies(defeatEnemiesCheckBox.isSelected())
		        .build();
		return level;
	}
	
	private void saveExistingLevel(Level level) {
		MazeToFileWriter.writeMazeToFile("./src/game_files/levels/custom/" +
				                         levelName + ".txt", level);
		showSavedMessage();
	}
	
	public void saveNewLevel(String levelName) {
		this.levelName = levelName;
		saveExistingLevel(level);
		showSavedMessage();
	}
	
	private void showSavedMessage() {
		helpMessage.setText("Level saved.");
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), helpMessage);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.play();
		fadeOut.setOnFinished(e -> {
			helpMessage.setText("Choose an entity to place by clicking on it");
			helpMessage.setOpacity(1.0);
		});
	}
	
	@FXML
	private void handleButton90() {
		designerHandler.switchToExitScreen();
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
	private MenuButton button43;
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
		return "game_files/sprites/" + e.getImageName() + ".png";
	}
}
