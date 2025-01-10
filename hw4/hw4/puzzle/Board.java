package hw4.puzzle;

public class Board implements WorldState {
    private int tile[][];
    private int size;
    /*
     * Constructs a board from an N-by-N array of tiles where
              tiles[i][j] = tile at row i, column j
     */
    public Board(int[][] tiles) {
        size = tiles[0].length;
        tile = new int[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j){
                tile[i][j] = tiles[i][j];
            }
        }
    }
    /*
     * Returns value of tile at row i, column j (or 0 if blank)
     */
    public int tileAt(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return tile[i][j];
    }
    /*
     * Returns the board size N
     */
    public int size() {
        return size;
    }
    /*
     * Returns the neighbors of the current board
     */
    public Iterable<WorldState> neighbors() {
        return null;
    }
    
    public int hamming() {
        int count = 0;
        int expect = 1;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (tile[i][j] != expect) {
                    count += 1;
                }
            }
        }
        return count - 1;
    }
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (i == size - 1 && j == size - 1) {
                    return count;
                }
                count += Math.abs(i + j - (tile[i][j] / size +tile[i][j] % size + 1));
            }
        }
        return count;
    }
    /*
     * Estimated distance to goal. This method should
              simply return the results of manhattan() when submitted to
              Gradescope
     */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    /*
     * Returns true if this board's tile values are the same
              position as y's   
     */
    public boolean equals(Object y) {
        Board goal = (Board) y;
        if (goal.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (tile[i][j] != goal.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    /*
    * Returns the string representation of the board. 
    */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
