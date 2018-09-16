import dungeon.Direction;

import org.junit.jupiter.api.Test;
import enemies.*;
import dungeon.*;
import player.*;
import items.*;
import game.*;

public class TestMove {
    @Test
    public void test_legal_moves () {
        Level level01 = new Level(SampleMaze.LEVEL01);
        System.out.println(level01.showLevel());
        level01.move(Direction.DOWN);
        assert (level01.getPlayer().getY() == 1);
        level01.move(Direction.RIGHT);
        assert (level01.getPlayer().getX() == 1);
        level01.move(Direction.UP);
        assert (level01.getPlayer().getY() == 0);
        level01.move(Direction.LEFT);
        assert (level01.getPlayer().getX() == 0);
    }

    @Test
    public void test_illegal_moves () {
        Level level01 = new Level(SampleMaze.LEVEL01);
        System.out.println(level01.showLevel());
        level01.move(Direction.UP);
        assert (level01.getPlayer().getY() == 0);
        level01.move(Direction.LEFT);
        assert (level01.getPlayer().getX() == 0);

        //Set to bottom right corner
        level01.getPlayer().setX(4);
        level01.getPlayer().setY(4);

        level01.move(Direction.DOWN);
        assert (level01.getPlayer().getY() == 4);
        level01.move(Direction.RIGHT);
        assert (level01.getPlayer().getX() == 4);
    }

}
