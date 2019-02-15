package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 35;
    private static final int HEIGHT = 32;
    private static final TETile[] colorBank = {Tileset.FLOWER, Tileset.MOUNTAIN, Tileset.TREE, Tileset.SAND, Tileset.WATER};



    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        //TODO: add a method which takes this logic and allows for varying sizes.
        drawGrid(3, world, 4, 7, 3);
        drawGrid(3,world, 9, 4, 4);
        drawGrid(3, world, 14, 1, 5);
        drawGrid(3, world, 19, 4, 4);
        drawGrid(3, world, 24, 7, 3);
        ter.renderFrame(world);
    }


    /** sets initial conditions for draing one Hexagon*/
    public static void drawHexagon(int size, TETile[][] world, int startX, int startY) {
        int width = (2 * (size - 1)) + size;
        if (startX + width > WIDTH || startY + width > HEIGHT) {
            throw new IndexOutOfBoundsException("Hexagon is past boundry");
        } else {
            Random r = new Random();
            int getRandom = r.nextInt(5);
            draw(size, width, colorBank[getRandom], world, startX, startY);
        }
    }
    /** recursive helper method - draws Hexagon top and bottom.*/
    public static void draw(int size, int width, TETile terrain, TETile[][] world, int startX, int startY) {
        if (size == 0) {
            return;
        } else {
            for (int j = size - 2; j < width - size; j++) {
                Random t = new Random();
                TETile colored = TETile.colorVariant(terrain, 100, 100, 50, t);
                world[startX + j][startY] = colored;
                world[startX + j][startY + (2 * size) - 1] = colored;
            }
            draw(size - 1, width, terrain, world, startX, startY + 1);
        }
    }

    public static void drawGrid(int size, TETile[][] world, int startX, int startY, int count) {
        for (int i = 0; i < count; i++) {
            drawHexagon(size, world, startX, startY + (i * size * 2));
        }

    }





}
