package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class worldGen {

    TERenderer ter;
    TETile[][] world;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private static final long SEED = 13;
    private Random r;
    private double coverage;
    private double range;
    private Room seed;



    public worldGen() {
        ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        r = new Random(SEED);
        coverage = 0;
        range = WIDTH * HEIGHT;

        world = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        this.seed = new Room(r, world, WIDTH / 2, HEIGHT / 2, RandomUtils.uniform(r, 4), false);
        addRoom(seed);

        ter.renderFrame(world);
    }


    public void fillWalls() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (world[x][y].equals(Tileset.NOTHING)) {
                    world[x][y] = Tileset.WALL;
                }
            }
        }

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (isWall(x, y)) {
                    world[x][y] = Tileset.NOTHING;
                }
            }
        }


        ter.renderFrame(world);
    }

    public boolean isWall(int x, int y) {
        int count = 0;
        for (int i = x - 1; i < x + 2; i++) {
            if (isBounded(i, y + 1)) {
                if (world[i][y + 1].equals(Tileset.FLOOR)) {
                    return false;
                }
            }

            if (isBounded(i, y -1)) {
                if (world[i][y - 1].equals(Tileset.FLOOR)) {
                    return false;
                }
            }
        }
        if (isBounded(x - 1, y)) {
            if (world[x - 1][y].equals(Tileset.FLOOR)) {
                return false;
            }
        }

        if (isBounded(x + 1, y)) {
            if (world[x + 1][y].equals(Tileset.FLOOR)) {
                return false;
            }
        }

        return true;

    }

    public boolean isBounded(int x, int y) {
        if (x >=0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            return true;
        }

        return false;
    }

    // for testing
    public Room getNewRoom() {
        return new Room(r, world, 20, 13, 3, false);
    }

    public boolean addRoom(Room room) {
        if (room.isBounded()) {
            coverage += room.getWidth() * room.getHeight();
            ter.renderFrame(world);
            return true;

        } else {
            return false;
        }
    }

    public void drawMap() {
        if (coverage / range < .7) {
            int hallway = RandomUtils.uniform(r, 2);
            int face = RandomUtils.uniform(r, 4);
            int startX = 0;
            int startY = 0;

            switch (face) {

                case 0 :
                    startY = seed.getStartY() + seed.getHeight() - 1;
                    startX = seed.getStartX() + RandomUtils.uniform(r, seed.getWidth());
                    break;

                case 1 :
                    startY = seed.getStartY() + 1;
                    startX = seed.getStartX() + RandomUtils.uniform(r, seed.getWidth());
                    break;

                case 2 :
                    startX = seed.getStartX() + 1;
                    startY = seed.getStartY() + RandomUtils.uniform(r, seed.getHeight());
                    break;

                case 3 :
                    startX = seed.getStartX() + seed.getWidth() - 1;
                    startY = seed.getStartY() + RandomUtils.uniform(r, seed.getHeight());
                    break;


            }

            boolean isHallway = false;

            if (hallway == 0) {
                isHallway = true;
            }

            seed.next = new Room(r, world, startX, startY, face, isHallway);

            if (addRoom(seed.next)) {
                addRoom(seed.next);
                seed = seed.next;
            }

            drawMap();
        }
        fillWalls();
    }


    public TETile[][] getWorld() {
        return world;
    }

}
