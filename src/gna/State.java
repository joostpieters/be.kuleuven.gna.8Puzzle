package gna;

/**
 * @author : Kasper Vervaecke
 *         Date: 27/03/13
 *         Time: 15:26
 */
public class State implements Comparable<State> {

    public State(Board board, int moves, State previous) {
        this.board = board;
        this.moves = moves;
        this.previous = previous;
    }

    @Override
    public int compareTo(State state) {
        int priority = board.manhattan();
        int otherPriority = state.getBoard().manhattan();
        if (priority + moves > otherPriority + state.getMoves()) {
            return 1;
        }
        if (priority + moves == otherPriority + state.getMoves()) {
            return 0;
        } else {
            return -1;
        }
    }

    public Board getBoard() {
        return board;
    }

    public int getMoves() {
        return moves;
    }

    public State getPrevious() {
        return previous;
    }

    private Board board;
    private int moves;
    private State previous;

}
