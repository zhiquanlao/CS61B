package hw2;

import javax.net.ssl.StandardConstants;

import edu.princeton.cs.introcs.StdRandom;

import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] stat;
    private int time_sim;

    //simulate the percolation
    private double simulate(int N, Percolation perc) {
        while (!perc.percolates()) {
            int row = StdRandom.uniform(N);
            int col = StdRandom.uniform(N);
            if (perc.isOpen(row, col)) {
                continue;
            }
            perc.open(row, col);
        }
        return ((double) perc.numberOfOpenSites()) / (N * N);
    }

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        stat = new double[N];
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        time_sim = T;
        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);
            stat[i] = simulate(N, perc);
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stat, 0, time_sim);
    }   
    // sample standard deviation of percolation threshold                                       
    public double stddev() {
        return StdStats.stddev(stat, 0, time_sim);
    }  
    // low endpoint of 95% confidence interval                                       
    public double confidenceLow() {
        return mean() - 1.96*stddev()/Math.sqrt(time_sim);
    }
    // high endpoint of 95% confidence interval                                 
    public double confidenceHigh() {
        return mean() + 1.96*stddev()/Math.sqrt(time_sim);
    }
}
