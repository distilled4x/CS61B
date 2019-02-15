package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class Hallway {
    private int width;
    private int height;
    private int startX;
    private int startY;
    private int direction;
    private TETile[][] world;


    // TODO add write into TETile
    public Hallway(Random r, int startX, int startY, TETile[][] world) {
        this.startX = startX;
        this.startY = startY;
        this.direction = RandomUtils.uniform(r, 4) + 1;
        this.world = world;


        switch (direction) {

            case 1 : //up
                this.width = 1;
                this.height = RandomUtils.uniform(r, 12) + 1;
                this.hall = new TETile[width][height];
                break;

            case 4 : //right
                this.width = RandomUtils.uniform(r, 12) + 1;
                this.height = 1;
                this.hall = new TETile[width][height];

                break;
        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    // may not need this
    public int getDirection() {
        return direction;
    }
}
