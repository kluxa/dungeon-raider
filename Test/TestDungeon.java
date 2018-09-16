package dungeon;

import static org.junit.Assert.*;

public class TestDungeon {


	@Test 
	void TestTile() {
		Tile t = new Tile(1,1);
		Maze maze = new Maze(9,9);

		//Test basic functions
		assertEquals(t.getX(), 1);
		assertEquals(t.getY(), 1);

		//Test item list 
		UnlitBomb bomb = new UnlitBomb();
		t.addItem(bomb);
		assertTrue(t.getItems().size() > 0);

		//Test tile when enemy arrives and ignores items
		Strategist s = new Strategist(t);
		t.arrive(s);
		assertTrue(t.getItems().size() > 0);

		//Test tile when player arrives to it and picks up the items
		Player player = new Player(maze);
		t.arrive(player);
		assertTrue(t.getItems().size() == 0);
		assertTrue(player.numItemOfType(bomb) > 0);

		//Test item drop function
		t.drop(bomb);
		assertTrue(t.getItems().size() > 0);

		//Test tile clearance
		t.clearTile();
		assertTrue(t.getItems().size() == 0);

	}

	@Test
	void TestSquare() {
		Square sq = new Square(0,1);

		//Test basic functions
		assertTrue(sq.getX() == 0);
		assertTrue(sq.getY() == 1);
		assertNotNull(sq.getTile());

		sq.setTile(new Tile(1,1));
		assertNotNull(sq.getTile());

		//Test array functions
		Arrow arrow = new Arrow();
		sq.addItem(arrow);
		assertTrue(sq.getItems().size() > 0);

		sq.drop(arrow);
		assertTrue(sq.getDroppedItems().size() > 0);

		sq.placeSolidEntity(new SolidEntity());
		assertTrue(sq.getOccupants().size() > 0);
	}

	/**
	*JUnit tests for class Entity
	*/
	@Test 
	void TestEntity() {
		Entity e = new Entity(new Tile(1,1));

		//Test getters and setters
		assertEquals(e.getX(),1);
		assertEquals(e.getY(),1);

		e.setLocation(new Tile(0,1));
		assertEquals(e.getX(),0);
		assertEquals(e.getY(),1);

		//Test entity is collidable
		assertTrue(e.isCollidable());
	}

	@Test 
	void TestNonLivingEntity() {
		NonLivingEntity nle = new NonLivingEntity(new Tile(1,0));

		//Test fall function -> set location to null
		nle.fall();
		assertNull(nle.getLocation());

		//Test type is 0 for non living entity
		assertEquals(nle.getType(), 0);
	}

	@Test 
	void TestLivingEntity() {
		LivingEntity le = new LivingEntity(new Tile(2,1));

		//Test for living entity
		assertTrue(le.isAlive());

		//Test that entity can die
		le.die();
		assertFalse(le.isAlive());

		//Test entity dies after falling
		LivingEntity le2 = new LivingEntity(new Tile(2,1));
		le.fall();
		assertFalse(le2.isAlive());
	}

	/**
	*Test Boulder class
	*/
	@Test
	void TestBoulder() {
		Boulder b = new Boulder(new Tile(1,1));
		Maze maze = new Maze(9,9);
		Player p = new Player(maze);

		//Test type of boulder is non living
		assertTrue(b.getType() == 0);

		//Test boulder toChar
		assertEquals(b.toChar(), 'O');
	}

	@Test
	void TestFloorSwitch() {
		FloorSwitch fs = new FloorSwitch(1,1);

		//test for untriggered floor switch
		assertFalse(fs.isTriggered());

		//Test for triggered switch after boulder arrives & untrigger after it leaves
		Boulder b = new Boulder(new Tile(1,1));

		fs.arrive(b);
		assertTrue(fs.isTriggered());

		fs.depart(b);
		assertFalse(fs.isTriggered());

		//Test toChar
		assertEquals(fs.toChar(), 'F');
	}

	@Test
	void TestDoor() {
		Door door = new Door(new Square(1,1), "red");
		Player player = new Player(new Maze(5,5));
		Key key = new Key("red");

		//assertion statements
		assertTrue(door.isCollidable());

		player.pickUp(key);
		door.collide(player);
		assertTrue("The Door is open after player uses key", door.isOpen());

		assertEquals('r', door.toChar());
	}

	@Test
	void TestExit() {
		Exit exit = new Exit();

		assertEquals('E', exit.toChar());
	}

	@Test
	void TestLitBomb() {
		LitBomb lb = new LitBomb(new Square(0,1));

		//assertion statements
		assertFalse(lb.isCollidable());

		int cd = lb.getCountDown();
		lb.countdown();
		assertTrue(lb.getCountDown() != cd);

		assertEquals('*', lb.toChar());
	}

	@Test
	void TestWall() {
		Wall wall = new Wall(new Square(1,0));

		assertEquals('W', wall.toChar());
	}
}
