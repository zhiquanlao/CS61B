package lab11.graphs;
import java.util.ArrayDeque;
/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int sX;
    private int sY;
    private int tX;
    private int tY;
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        sX = sourceX;
        sY = sourceY;
        tX = targetX;
        tY = targetY;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        ArrayDeque<Integer> vertex = new ArrayDeque<Integer>();
        vertex.add(maze.xyTo1D(sX, sY));
        marked[maze.xyTo1D(sX,sY)] = true;
        distTo[maze.xyTo1D(sX, sY)] = 0;
        announce();
        while (true) {
            if (vertex.peek() == null) {
                break;
            }
            int vert = vertex.poll();
            //System.out.println("x:"+maze.toX(vert)+", y:"+maze.toY(vert));
            if (maze.toX(vert) == tX && maze.toY(vert) == tY) {
                //find the target
                break;
            }
            for (int adj : maze.adj(vert)) {
                
                if (!marked[adj]) {
                    distTo[adj] = Math.min(distTo[vert] + 1, distTo[adj]);
                    //TODO: what is edgeTo?
                    marked[adj] = true;
                    announce();
                    vertex.add(adj);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

