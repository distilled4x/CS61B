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
    private static final long SEED = 1000;
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

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        this.seed = new Room(r, RandomUtils.uniform(r, WIDTH / 4), RandomUtils.uniform(r, HEIGHT / 4));

        while (!draw(seed)) {
            this.seed = new Room(r, RandomUtils.uniform(r, WIDTH / 4), RandomUtils.uniform(r, HEIGHT / 4));
        }

    }

    public TETile[][] copyWorld() {
        return TETile.copyOf(world);
    }

    public boolean addRoom(Room room) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                if (world[x][y].equals(room[x][y]) && world[x][y].equals(Tileset.FLOOR)) {
                    return false;
                } else {
                    world[x][y] = room[x][y];
                }
            }
        }
        return true;
    }





    public boolean isBounded(Room room) {
        if (room.getStartX() + room.getWidth() > WIDTH || room.getStartY() + room.getHeight() > HEIGHT) {
            return false;
        }
        return true;
    }

    public boolean isBoundedReverse(Room room) {
        if (room.getStartX() - room.getWidth() < 0 || room.getStartY() - room.getHeight() < 0) {
            return false;
        }
        return true;
    }


    public boolean isCrowded(Room room) {
        if(isBounded(room)) {
            for (int y = 0; y < room.getHeight(); y++) {
                for (int x = 0; x < room.getWidth(); x++) {
                    if (world[x + room.getStartX()][y + room.getStartY()].equals(Tileset.FLOOR)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public boolean isCrowdedReverse(Room room) {
       if(isBoundedReverse(room)) {
           for (int y = room.getHeight() - 1; y >= 0; y--) {
               for (int x = room.getWidth() - 1; x >= 0; x--) {
                   if (world[room.getStartX() - x - 1][room.getStartY() - y - 1].equals(Tileset.FLOOR)) {
                       return true;
                   }
               }
           }
           return false;
       }
       return true;
    }

    public TETile[][] getWorld() {
        return world;
    }

    public boolean draw(Room next) {
        if (!isCrowded(next)) {
            for (int y = 0; y < next.getHeight(); y++) {
                for (int x = 0; x < next.getWidth(); x++) {
                    world[x + next.getStartX()][y + next.getStartY()] = Tileset.FLOOR;
                }
            }
            coverage += next.getWidth() * next.getHeight();
            ter.renderFrame(world);
            return true;
        }

        return false;
    }

    public boolean drawReverse(Room next) {
        if (!isCrowdedReverse(next)) {
            for (int y = next.getHeight() - 1; y >= 0; y--) {
                for (int x = next.getWidth() - 1; x >= 0; x--) {
                    world[next.getStartX() - x - 1][next.getStartY() - y - 1] = Tileset.FLOOR;
                }
            }
            coverage += next.getWidth() + next.getHeight();
            ter.renderFrame(world);
            return true;
        }

        return false;
    }

    public void drawWalls() {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                if (!world[x][y].equals(Tileset.FLOOR)) {
                    world[x][y] = Tileset.WALL;
                }
            }
        }
    }


    // recursively generated.  Only goes direction up and right.  Reverse method to draw other way? Will need alternate draw method.
    public void genRooms(int count) {
        while ((coverage / range) < 0.4 && count < 1) {

            int switcher = RandomUtils.uniform(r, 4);
            int switcher2 = RandomUtils.uniform(r,2);
            //switcher = 0;

            if (switcher2 == 0) {
                //room
            } else {
                //hallway
            }

            switch (switcher2) {

                case 0 :
                    switch (switcher) {

                        case 0:
                            seed.next = new Room(r, RandomUtils.uniform(r, seed.getWidth()) + seed.getStartX(),
                                    seed.getStartY() + seed.getHeight());
                            draw(seed.next);
                            break;

                        case 1:
                            seed.next = new Room(r, seed.getStartX() + seed.getWidth(),
                                    RandomUtils.uniform(r, seed.getHeight()) + seed.getStartY());
                            draw(seed.next);
                            break;

                        case 2:
                            seed.next = new Room(r, RandomUtils.uniform(r, seed.getWidth()) + seed.getStartX(),
                                    seed.getStartY() + seed.getHeight(),
                                    RandomUtils.uniform(r, 10) + 1, 1);
                            draw(seed.next);
                            break;

                        case 3:
                            seed.next = new Room(r, seed.getStartX() + seed.getWidth(),
                                    RandomUtils.uniform(r, seed.getHeight()) + seed.getStartY(), 1,
                                    RandomUtils.uniform(r, 10) + 1);
                            draw(seed.next);
                            break;
                    }

                case 1 :
                    switch (switcher) {

       // 2 cases: room or hall?
       // 4 cases per decision: which direction?



            }
            seed = seed.next;
            genRooms(count + 1);
        }

        drawWalls();

    }


    // TODO add method to fill walls
}
