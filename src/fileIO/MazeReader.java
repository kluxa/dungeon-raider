package fileIO;

import dungeon.Maze;

import java.io.*;
import java.util.Scanner;

public class MazeReader implements FileIOStrat {

    FileReader reader = null;

    @Override
    public Maze loadMaze(String fileLoc) {
        Scanner sc = null;
        Maze maze = null;
        try {
            sc = new Scanner(new File(fileLoc));
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] ls = line.split(" ");
                if (ls[0].equals("Directions:")) {

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (sc != null) sc.close();
        }
    }

    @Override
    public void saveMaze(String location, Maze maze) {

    }
}
