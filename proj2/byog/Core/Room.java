package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class Room {
    private int height;
    private int width;
    private int startX;
    private int startY;
    private int direction;
    private TETile[][] room;
    private boolean bounded;
    public Room next;
    private boolean hallway;


    public Room(Random r, TETile[][] room, int startX, int startY, int face, boolean hallway) {
        this.bounded = true;
        this.room = room;
        this.height = RandomUtils.uniform(r, 12) + 1;
        this.width = RandomUtils.uniform(r, 12) + 1;
        this.startX = startX;
        this.startY = startY;
        this.direction = RandomUtils.uniform(r, 2);
        this.next = null;
        this.hallway = hallway;

        if (hallway) {
            int hallDirection = RandomUtils.uniform(r, 2);

            if (hallDirection == 0) {
                this.width = 1;
            } else {
                this.height = 1;
            }
        }

        switch (face) {

            case 0 : //top
                if (direction == 0) { //left
                    fill(startX - width, startX, startY, startY + height);
                    this.startX -= width;
                } else {
                    fill(startX, startX + width, startY, startY + height);
                }
                break;

            case 1 : //bottom
                if (direction == 0) { //left
                    fill(startX - width, startX, startY - height, startY);
                    this.startX -= width;
                    this.startY -= height;
                } else {
                    fill(startX, startX + width, startY - height, startY);
                    this.startY -= height;
                }
                break;

            case 2 : //left
                if (direction == 0) { //up
                    fill(startX - width, startX, startY, startY + height);
                    this.startX -= width;
                } else {
                    fill(startX - width, startX, startY - height, startY);
                    this.startX -= width;
                    this.startY -= height;
                }
                break;

            case 3 : //right
                if (direction == 0) { //up
                    fill(startX, startX + width, startY, startY + height);
                } else {
                    fill(startX, startX + width, startY - height, startY);
                    this.startY -= height;
                }
                break;
        }
    }

    public void fill(int startX, int endX, int startY, int endY) {
        int width = room.length;
        int height = room[0].length;

        if (startX < 0 || endX > width || startY < 0 || endY > height) {
            bounded = false;
        } else {
            for (int y = startY; y < endY; y++) {
                for (int x = startX; x < endX; x++) {
                    if (room[x][y].equals(Tileset.FLOOR) && !hallway) {
                        bounded = false;
                        unFill(startX, x, startY, y);
                        return;
                    } else if (!isBorder(x, y, width, height)){
                        room[x][y] = Tileset.FLOOR;
                    }
                }
            }
        }
    }

    public boolean isBorder(int x, int y, int width, int height) {
        if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
            return true;
        }

        return false;
    }

    public void unFill(int startX, int endX, int startY, int endY) {
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                room[x][y] = Tileset.NOTHING;
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

    public int getDirection() {
        return direction;
    }

    public boolean isBounded() {
        return bounded;
    }
}
