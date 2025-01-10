package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import hw4.puzzle.WorldState;
import java.util.ArrayList; 

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        WorldState cur;
        int numMoves;
        SearchNode prev;
        int distToGoal;
        public SearchNode(WorldState state, int nMoves, SearchNode p) {
            cur = state;
            numMoves = nMoves;
            prev = p;
            distToGoal = cur.estimatedDistanceToGoal();
        }
        public int compareTo(SearchNode s) {
            //return cur.estimatedDistanceToGoal() - s.cur.estimatedDistanceToGoal();
            return   distToGoal - s.distToGoal;
        }
    }
    private ArrayList<WorldState> solution;
    private int moves;
    private MinPQ<SearchNode> Nodes;
    /*
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        int initCapacity = 10;
        Nodes = new MinPQ<SearchNode>(initCapacity);
        SearchNode init = new SearchNode(initial, 0, null);
        Nodes.insert(init);

        int count = 0;

        //begin searching
        while (true) { 
            SearchNode X = Nodes.delMin();

            //System.out.println(X.cur);

            if (X.cur.isGoal()) {
                moves = X.numMoves;

                //System.out.println(moves);

                solution = new ArrayList<WorldState>(moves);
                SearchNode temp = X;
                while(temp != null){
                    solution.add(0, temp.cur);
                    temp = temp.prev;
                }

                // System.out.println("solution size:"+solution.size());
                // System.out.println("move:"+moves);
                System.out.print("count:"+count);

                break;
            }
            for (WorldState s: X.cur.neighbors()) {

                //System.out.println(s+" neighbors");

                SearchNode temp = new SearchNode(s, X.numMoves + 1 , X);

                // System.out.print(temp.numMoves+", ");
                
                if (X.prev == null || (X.prev != null && !temp.cur.equals(X.prev.cur))) {
                    Nodes.insert(temp);
                    count += 1;
                }
            }
        }
    }
    /* 
        Returns the minimum number of moves to solve the puzzle starting
        at the initial WorldState.
     */
    public int moves() {
        return moves;
    }
    /*
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     */
    public Iterable<WorldState> solution() {
        return solution;
    }
}