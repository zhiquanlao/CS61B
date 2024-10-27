package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int num_grid;
    private int num_open;
    private WeightedQuickUnionUF sets;
    private boolean [][] site;
    
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        //initialize all sites to block
        site = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; ++j) {
                site[i][j] = false;
            }
        }
        //site[i][j] is denoted by i * N + j in sets,
        //the N*Nth is top
        sets = new WeightedQuickUnionUF(N * N + 1);
        num_grid = N;
        num_open = 0;
    }
    //convert [row][col] to the index in sets
    private int row_col_to_index(int row, int col) {
        return row * num_grid + col;
    }
    //get the col of the index in sets. ind<N^2
    private int col_of_index(int ind) {
        return ind % num_grid;
    }
    //get rol from the index in sets. ind<N^2
    private int row_of_index(int ind) {
        return ind / num_grid;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= num_grid || col < 0 || col >= num_grid) {
            throw new IndexOutOfBoundsException();
        }
        //do nothing if it is already open
        if (site[row][col]) {
            return;
        }
        site[row][col] = true;

        //union the site to nearby site
        if (row == num_grid - 1) {
            //if it is the last row, union it to the bottom
           //sets.union(row_col_to_index(row, col), num_grid * num_grid + 1);
        } else if (site[row + 1][col]) {
            sets.union(row_col_to_index(row, col), row_col_to_index(row + 1, col));
        }
        if (row == 0) {
            //if at top row, union it to top
            sets.union(row_col_to_index(row, col), num_grid * num_grid);
        } else if (site[row - 1][col]) {
            sets.union(row_col_to_index(row, col), row_col_to_index(row - 1, col));
        }
        if (col - 1 >= 0 && site[row][col - 1]) {
            sets.union(row_col_to_index(row, col), row_col_to_index(row, col - 1));
        }
        if (col + 1 <= num_grid - 1 && site[row][col + 1]) {
            sets.union(row_col_to_index(row, col), row_col_to_index(row, col + 1));
        }
        num_open += 1;
    }   
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= num_grid || col < 0 || col >= num_grid) {
            throw new IndexOutOfBoundsException();
        }
        return site[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= num_grid || col < 0 || col >= num_grid) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            return false;
        }
        return sets.find(num_grid * num_grid) == sets.find(row_col_to_index(row, col));
    } 

    // number of open sites
    public int numberOfOpenSites() {
        return num_open;
    }  
    
    // does the system percolate?
    public boolean percolates() {
        //return sets.find(num_grid * num_grid + 1) == sets.find(num_grid * num_grid);
        for (int i = 0; i < num_grid; ++i) {
            if(isFull(num_grid, i)) {
                return true;
            }
            return false;
        }
    }             
    public static void main(String[] args) {

    }
}
