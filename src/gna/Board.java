package gna;

import java.util.ArrayList;

public class Board {

    // construct a board from an N-by-N array of tiles
    public Board(int[][] tiles) {
        this.tiles = tiles;
    }

    // return number of blocks out of place
    public int hamming() {
        int hammingDistance = 0;
        int expectedNumber = 1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (expectedNumber != tiles[i][j]) {
                    hammingDistance++;
                }
                if (++expectedNumber == tiles.length * tiles.length) {
                    break;
                }
            }
        }
        return hammingDistance;
    }

    // return sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattanDistance = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] != 0) {
                    int actualNumber = tiles[i][j];
                    int expectedCol = (actualNumber - 1) % tiles.length;
                    int expectedRow = ((actualNumber - 1) - ((actualNumber - 1) % tiles.length)) / tiles.length;
                    int rowDiff = Math.abs(i - expectedRow);
                    int colDiff = Math.abs(j - expectedCol);
                    manhattanDistance += rowDiff + colDiff;
                }
            }
        }
        return manhattanDistance;
    }

    // does this board position equal y
    public boolean equals(Object y) {
        if (y == null || !(y instanceof Board)) {
            return false;
        }
        Board otherBoard = (Board) y;
        if (otherBoard.tiles.length * otherBoard.tiles.length != tiles.length * tiles.length) {
            return false;
        }
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] != otherBoard.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // return an Iterable of all neighboring board positions
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<Board>();
        int row = 0;
        int col = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] == 0) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        if (row != 0) {
            neighbors.add(exch(tiles[row][col], tiles[row - 1][col], row, col, row - 1, col));
        }
        if (row != tiles.length - 1) {
            neighbors.add(exch(tiles[row][col], tiles[row + 1][col], row, col, row + 1, col));
        }
        if (col != 0) {
            neighbors.add(exch(tiles[row][col], tiles[row][col - 1], row, col, row, col - 1));
        }
        if (col != tiles.length - 1) {
            neighbors.add(exch(tiles[row][col], tiles[row][col + 1], row, col, row, col + 1));
        }
        return neighbors;
    }

    // return a string representation of the board
    public String toString() {
        String board = "";
        int rowLength = tiles.length;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                String tile;
                if(tiles[i][j] != 0) {
                    tile = tiles[i][j] + "";
                } else {
                    tile = " ";
                }
                if (tile.length() < 2) {
                    tile += " ";
                }
                board += " " + tile;
                rowLength--;
                if (rowLength == 0) {
                    rowLength = tiles.length;
                    board += "\n";
                }
            }
        }
        return board;
    }

    public int[][] getTiles() {
        return tiles;
    }

    private Board exch(int firstTile, int secondTile, int firstRow, int firstCol, int secondRow, int secondCol) {
        int[][] temp = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                temp[i][j] = tiles[i][j];
            }
        }
        Board copy = new Board(temp);
        copy.tiles[firstRow][firstCol] = secondTile;
        copy.tiles[secondRow][secondCol] = firstTile;
        return copy;
    }

    private int[][] tiles;
}