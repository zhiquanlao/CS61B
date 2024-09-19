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
    private class Position {
        int x;
        int y;
        public Position(int xpos, int ypos) {
            x = xpos;
            y = ypos;
        }
    }
    private static int calcWidth(int row, int size) {
        return 0;
    }
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        for (int i = 0; i < 2 * s; ++i) {
            int width = calcWidth(i, s);
            for (int j = 0; j < width; ++j) {
                
            }
        }
    }
}


