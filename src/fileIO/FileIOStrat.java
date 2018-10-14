package fileIO;

import dungeon.Maze;

public interface FileIOStrat {
    Maze loadMaze(String fileName);
    void saveMaze(String location, Maze maze);
}
