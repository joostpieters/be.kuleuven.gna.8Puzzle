package gna;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * A number of JUnit tests for Solver.
 * <p/>
 * Feel free to modify these test to check additional properties.
 */
public class SolverTest {

    private Board board, otherBoard;

    @Before
    public void setUp() {
        int[][] tiles = new int[3][3];
        tiles[0][0] = 1;
        tiles[0][1] = 4;
        tiles[0][2] = 3;
        tiles[1][0] = 5;
        tiles[1][1] = 8;
        tiles[1][2] = 6;
        tiles[2][0] = 7;
        tiles[2][1] = 2;
        tiles[2][2] = 0;

        int[][] otherTiles = new int[3][3];
        otherTiles[0][0] = 1;
        otherTiles[0][1] = 4;
        otherTiles[0][2] = 3;
        otherTiles[1][0] = 5;
        otherTiles[1][1] = 8;
        otherTiles[1][2] = 6;
        otherTiles[2][0] = 7;
        otherTiles[2][1] = 0;
        otherTiles[2][2] = 2;

        board = new Board(tiles);
        otherBoard = new Board(otherTiles);
    }


    @Test
    public void testHamming() {
        assertEquals(4, board.hamming());
        assertEquals(4, otherBoard.hamming());
    }

    @Test
    public void testManhattan() {
        assertEquals(6, board.manhattan());
        assertEquals(7, otherBoard.manhattan());
    }

    @Test
    public void testEquals() {
        assertFalse(board.equals(otherBoard));
    }

    @Test
    public void testNeighbors() {
        for (Board neigbor:otherBoard.neighbors()) {
            System.out.println(neigbor.toString());
        }
    }
}
