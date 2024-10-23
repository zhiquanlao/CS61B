package hw2;

import javax.management.RuntimeErrorException;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int num_grid;
    private int num_open;
    private WeightedQuickUnionUF sets;
    private boolean [][] site;
    // private class grid {
    //     private boolean open;
    //     private grid parent;
    //     private int size;
    //     public grid() {
    //         open = false;
    //         size = 1;
    //     }
    // }
    
    // //connect the grid with another grid
    // private void union(grid p, grid q) {
    //     grid i = find(p);
    //     grid j = find(q);
    //     if(i == j) {
    //         return;
    //     }
    //     if(i.size < j.size) {
    //         i.parent = j;
    //         j.size += i.size;
    //     } else {
    //         j.parent = i;
    //         i.size += j.size;
    //     }
    // }
    
    // //find the parent of the grid
    // private grid find(grid p) {
    //     if (p.parent == p) {
    //         return p;
    //     }
    //     p.parent = find(p.parent);
    //     return p.parent;
    // }

    // private boolean is_connected(grid p, grid q) {
    //     return find(p) == find(q);
    // }
    // private grid[][] site;
    // private grid top;
    // private grid bottom;
    
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        // site = new grid[N][N];
        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; ++j){
        //         site[i][j].parent = site[i][j];
        //     }
        // }
        // top = new grid();
        // top.parent = top;
        // top.open = true;
        // bottom = new grid();
        // bottom.parent = bottom;
        // bottom.open = true;

        //initialize all sites to block
        site = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; ++j){
                site[i][j] = false;
            }
        }
        //site[i][j] is denoted by i * N + j in sets,
        //the Nth and N+1th are bottom and top
        sets = new WeightedQuickUnionUF(N * N + 2);
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
        if (row < 0 || row >= num_grid || col < 0 || col >=num_grid) {
            throw new IndexOutOfBoundsException();
        }

        site[row][col] = true;

        //union the site to nearby site
        if (row == num_grid - 1) {
            //if it is the last row, union it to the bottom
            sets.union(row_col_to_index(row, col), num_grid * num_grid);
        } else if (site[row + 1][col]) {
            sets.union(row_col_to_index(row, col), row_col_to_index(row + 1, col));
            //union(site[row][col], site[row + 1][col]);
        }
        if (row == 0) {
            //if at top row, union it to top
            sets.union(row_col_to_index(row, col), num_grid * num_grid + 1);
            //union(top, site[row][col]);
        } else if (site[row - 1][col]) {
            sets.union(row_col_to_index(row, col), row_col_to_index(row - 1, col));
            //union(site[row][col], site[row - 1][col]);
        }
        if (col - 1 >= 0 && site[row][col - 1]) {
            sets.union(row_col_to_index(row, col), row_col_to_index(row, col - 1));
            //union(site[row][col], site[row][col - 1]);
        }
        if (col + 1 <=num_grid - 1 && site[row][col + 1]) {
            sets.union(row_col_to_index(row, col), row_col_to_index(row, col + 1));
            //union(site[row][col], site[row][col + 1]);
        }
        num_open += 1;
    }   
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= num_grid || col < 0 || col >=num_grid) {
            throw new IndexOutOfBoundsException();
        }
        return site[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= num_grid || col < 0 || col >=num_grid) {
            throw new IndexOutOfBoundsException();
        }
        return sets.find(num_grid * num_grid + 1) == sets.find(row_col_to_index(row, col));
    } 

    // number of open sites
    public int numberOfOpenSites() {
        return num_open;
    }  
    
    // does the system percolate?
    public boolean percolates() {
        //return is_connected(top, bottom);
        return sets.find(num_grid * num_grid + 1) == sets.find(num_grid * num_grid);
    }             
    public static void main(String[] args) {

    }
}
