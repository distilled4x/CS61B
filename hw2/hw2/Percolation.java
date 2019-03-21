package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int N;
    private int size;
    private WeightedQuickUnionUF board;
    private WeightedQuickUnionUF boardCount;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        this.N = N;
        this.size = N * N;
        this.board = new WeightedQuickUnionUF(size + 2);
        this.boardCount = new WeightedQuickUnionUF(size + 1);

    }

    public void open(int row, int col) {
        if (isBounded(row, col)) {
            int coord = xyTo1D(row, col);
            boardCount.union(coord, size);

            if (coord <= N - 1) {
                board.union(coord, N * N);

            } else if (!percolates() && coord > N * (N - 1)) {
                board.union(coord, N * N + 1);
            }

            tileConnect(row, col);

        }
    }

    public boolean isOpen(int row, int col) {
        if (isBounded(row, col)) {
            int coord = xyTo1D(row, col);
            return boardCount.connected(coord, size);

        }

        return false;

    }

    public boolean isFull(int row, int col) {
        if (isBounded(row, col)) {
            return board.connected(xyTo1D(row, col), N * N);
        }

        return false;
    }


    public int numberOfOpenSites() {

        return size - boardCount.count() + 1;

    }

    public boolean percolates() {
        return board.connected(N * N, N * N + 1);
    }

    public void tileConnect(int row, int col) {
        int coord = xyTo1D(row, col);

        if (isOpen(row + 1, col)) {
            board.union(coord, xyTo1D(row + 1, col));

        }

        if (isOpen(row - 1, col)) {
            board.union(coord, xyTo1D(row - 1, col));

        }

        if (isOpen(row, col + 1)) {
            board.union(coord, xyTo1D(row, col + 1));

        }

        if (isOpen(row, col - 1)) {
            board.union(coord, xyTo1D(row, col - 1));

        }
    }

    public boolean isBounded(int row, int col) {
        int coord = xyTo1D(row, col);
        if (coord < 0 || coord > N * N - 1) {
            return false;
            //throw new java.lang.IndexOutOfBoundsException();
        }

        return true;
    }

    public int xyTo1D(int row, int col) {
        return row * N + col;
    }

    public static void main(String args[]) throws java.lang.IndexOutOfBoundsException {
        Percolation perc = new Percolation(10);
        System.out.println(perc.isBounded(0,6));
        perc.open(0,6);
        System.out.println(perc.isOpen(0,6));
    }

}
