package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class Room {
    private int height;
    private int width;
    private int startX;
    private int startY;
    private TETile[][] room;
    public Room next;


    public Room(Random r, int startX, int startY,) {
        this(r, startX, startY,
                RandomUtils.uniform(r, 8) + 4, RandomUtils.uniform(r, 8) + 4);
    }

    public Room(Random r, TETile[][] room) {
        this.height = height;
        this.width = width;
        this.startX = startX;
        this.startY= startY;
        this.room = new TETile[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                room[j][i] = Tileset.FLOOR;
            }
        }

        this.next = null;
    }

    // For testing only
    // TODO remove when finished testing
    public Room(int height, int width, int startX, int startY) {
        this.height = height;
        this.width = width;
        this.startX = startX;
        this.startY = startY;
        this.room = new TETile[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                room[j][i] = Tileset.FLOOR;
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    // do I need this?
    public TETile[][] getRoom() {
        return room;
    }
}
