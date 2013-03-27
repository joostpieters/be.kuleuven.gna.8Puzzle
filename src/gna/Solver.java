package gna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solver {

    // find a solution to the initial board
    public Solver(Board initial) {
        this.board = initial;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        int[][] tiles = board.getTiles();
        int counter = 0;
        int inversions = 0;
        int row = 0;
        int[] tilesRow = new int[tiles.length * tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                tilesRow[counter++] = tiles[i][j];
                if (tiles[i][j] == 0) {
                    row = i;
                }
            }
        }
        for (int i = 0; i < tiles.length * tiles.length; i++) {
            for (int j = i; j < tiles.length * tiles.length; j++) {
                if (tilesRow[i] > tilesRow[j] && tilesRow[j] != 0) {
                    inversions++;
                }
            }
        }
        if (((tiles.length%2 == 1) && (inversions%2 == 0)) || ((tiles.length%2 == 0) &&
                (((tiles.length - row)%2 == 1) == (inversions%2 == 0)))) {
            return true;
        } else {
            return false;
        }
    }

    // return min number of moves to solve initial board;
    // -1 if no solution
    public int moves() {
        if (isSolvable()) {
            if (solution != null) {
                return solution.size() - 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    // return an Iterable of board positions in solution
    public Iterable<Board> solution() {
        solution = new ArrayList<Board>();
        if (isSolvable()) {
            int moves;
            PriorityQueue<State> priorityQueue = new PriorityQueue<State>();
            State firstState = new State(board, 0, null);
            priorityQueue.add(firstState);
            State minState;
            while (true) {
                minState = priorityQueue.poll();
                if (minState.getBoard().hamming() != 0) {
                    moves = minState.getMoves() + 1;
                    for (Board board:minState.getBoard().neighbors()) {
                        if (minState.getPrevious() != null) {
                            if (!board.equals(minState.getPrevious().getBoard())) {
                                priorityQueue.add(new State(board, moves, minState));
                            }
                        } else {
                            priorityQueue.add(new State(board, moves, minState));
                        }
                    }
                } else {
                    break;
                }
            }
            while (minState.getPrevious() != null) {
                solution.add(minState.getBoard());
                minState = minState.getPrevious();
            }
            solution.add(minState.getBoard());
            Collections.reverse(solution);
        }
        return solution;
    }

    private Board board;
    private ArrayList<Board> solution;

}


