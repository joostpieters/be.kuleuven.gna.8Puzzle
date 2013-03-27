package gna;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A number of JUnit tests for Solver.
 * <p/>
 * Feel free to modify these test to check additional properties.
 */
public class SolverTest {

    private Board board, impossibleBoard;
    private Solver solver, impossibleSolver;

    @Before
    public void setUp() {
        int[][] tiles = new int[3][3];
        tiles[0][0] = 0;
        tiles[0][1] = 1;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = 2;
        tiles[1][2] = 5;
        tiles[2][0] = 7;
        tiles[2][1] = 8;
        tiles[2][2] = 6;

        int[][] otherTiles = new int[3][3];
        otherTiles[0][0] = 1;
        otherTiles[0][1] = 2;
        otherTiles[0][2] = 3;
        otherTiles[1][0] = 4;
        otherTiles[1][1] = 6;
        otherTiles[1][2] = 5;
        otherTiles[2][0] = 7;
        otherTiles[2][1] = 8;
        otherTiles[2][2] = 0;

        board = new Board(tiles);
        impossibleBoard = new Board(otherTiles);
        solver = new Solver(board);
        impossibleSolver = new Solver(impossibleBoard);
    }


    @Test
    public void testHamming() {
        assertEquals(4, board.hamming());
        assertEquals(2, impossibleBoard.hamming());
    }

    @Test
    public void testManhattan() {
        assertEquals(4, board.manhattan());
        assertEquals(2, impossibleBoard.manhattan());
    }

    @Test
    public void testEquals() {
        assertFalse(board.equals(impossibleBoard));
    }

    @Test
    public void testIsSolvable() {
        assertTrue(solver.isSolvable());
        assertFalse(impossibleSolver.isSolvable());
    }

    @Test
    public void testSolution() {
        for (Board board : solver.solution()) {
            System.out.println(board);
        }
        assertEquals(4, solver.moves());
        assertEquals(-1, impossibleSolver.moves());
    }
}
