package hw2;

import javax.management.RuntimeErrorException;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int num_grid;
    private int num_open;
    
    private class grid {
        private boolean open;
        private grid parent;
        private int size;
        public grid() {
            open = false;
            size = 1;
            parent = this;
        }
    }
    
    //connect the grid with another grid
    private void union(grid p, grid q) {
        grid i = find(p);
        grid j = find(q);
        if(i == j) {
            return;
        }
        if(i.size < j.size) {
            i.parent = j;
            j.size += i.size;
        } else {
            j.parent = i;
            i.size += j.size;
        }
    }
    
    //find the parent of the grid
    private grid find(grid p) {
        if (p.parent == p) {
            return p;
        }
        p.parent = find(p.parent);
        return p.parent;
    }

    private boolean is_connected(grid p, grid q) {
        return find(p) == find(q);
    }
    private grid[][] site;
    private grid top;
    private grid bottom;
    
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        site = new grid[N][N];
        top = new grid();
        top.open = true;
        bottom = new grid();
        bottom.open = true;
        num_grid = N;
        num_open = 0;
    }
    
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= num_grid || col < 0 || col >=num_grid) {
            throw new IndexOutOfBoundsException();
        }

        site[row][col].open = true;

        //union the site to nearby site
        if (row == num_grid - 1) {
            union(bottom, site[row][col]);
        } else if (site[row + 1][col].open) {
            union(site[row][col], site[row + 1][col]);
        }
        if (row == 0) {
            union(top, site[row][col]);
        } else if (site[row - 1][col].open) {
            union(site[row][col], site[row - 1][col]);
        }
        if (col - 1 >= 0 && site[row][col - 1].open) {
            union(site[row][col], site[row][col - 1]);
        }
        if (col + 1 <=num_grid - 1 && site[row][col + 1].open) {
            union(site[row][col], site[row][col + 1]);
        }
        num_open += 1;
    }   
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= num_grid || col < 0 || col >=num_grid) {
            throw new IndexOutOfBoundsException();
        }
        return site[row][col].open;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= num_grid || col < 0 || col >=num_grid) {
            throw new IndexOutOfBoundsException();
        }
        return is_connected(top, site[row][col]);
    } 

    // number of open sites
    public int numberOfOpenSites() {
        return num_open;
    }  
    
    // does the system percolate?
    public boolean percolates() {
        return is_connected(top, bottom);
    }             
    public static void main(String[] args) {

    }
}
