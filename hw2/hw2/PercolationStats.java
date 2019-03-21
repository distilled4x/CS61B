package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private PercolationFactory pf;
    private double[] permutations;


    // run T trials of N size percolation sims, store amount of openings until percolation
    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.N = N;
        this.T = T;
        this.pf = pf;
        permutations = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);

            while(!perc.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);

                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                }

            }

            permutations[i] = (double) perc.numberOfOpenSites();
        }

    }

    public double mean() {
        return StdStats.mean(permutations);
    }

    public double stddev() {
        return StdStats.stddev(permutations);
    }

    public double confidenceLow() {
        return mean() - confidence();
    }

    public double confidenceHigh() {
        return mean() + confidence();
    }

    public double confidence() {
        return (1.96 * stddev()) / Math.sqrt((double) T);
    }

}
