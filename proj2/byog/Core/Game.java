package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Game {
    TERenderer ter = new TERenderer();
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    TETile[][] world = new TETile[WIDTH][HEIGHT];

    public Game() {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public void drawRoom(int height, int width, int startX, int startY) {
        for (int i = startY; i < startY + height; i++) {
            for (int j = startX; j < startX + width; j++) {
                if (i == startY || i == startY + height - 1 || j == startX || j == startX + width - 1) {
                    world[j][i] = Tileset.WALL;
                } else {
                    world[j][i] = Tileset.FLOOR;
                }
            }
        }
    }

    public void drawHallway(int direction, int length, int startX, int startY) {

        switch (direction) {
            case 1 : //up
                for (int i = startY; i < startY + length; i++) {
                    world[startX][i] = Tileset.WALL;
                    world[startX + 1][i] = Tileset.FLOOR;
                    world[startX + 2][i] = Tileset.FLOOR;
                    world[startX + 3][i] = Tileset.WALL;
                }
                break;

            case 2 : //down
                for (int i = startY; i < startY - length; i--) {
                    world[startX][i] = Tileset.WALL;
                    world[startX + 1][i] = Tileset.FLOOR;
                    world[startX + 2][i] = Tileset.FLOOR;
                    world[startX + 3][i] = Tileset.WALL;
                }
                break;

            case 3 : //left
                for (int i = startX; i < startX  - length; i--) {
                    world[i][startY] = Tileset.WALL;
                    world[i][startY + 1] = Tileset.FLOOR;
                    world[i][startY + 2] = Tileset.FLOOR;
                    world[i][startY + 3] = Tileset.WALL;
                }
                break;

            case 4 : //right
                for (int i = startX; i < startX + length; i++) {
                    world[i][startY] = Tileset.WALL;
                    world[i][startY + 1] = Tileset.FLOOR;
                    world[i][startY + 2] = Tileset.FLOOR;
                    world[i][startY + 3] = Tileset.WALL;
                }
                break;
        }
    }



    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
}
