package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import org.junit.Test;
import static org.junit.Assert.*;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF sitesWithoutVirtualBottom;
    private Boolean[] openSites;
    private int numberOfOpenSites;
    private int length;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        length = N;
        sites = new WeightedQuickUnionUF(N*N + 2);
        sitesWithoutVirtualBottom = new WeightedQuickUnionUF(N*N + 1);
        openSites = new Boolean[N*N];

        //the first row union to virtual top
        for (int col = 0; col < length; col ++) {
            sites.union(twoDTo1D(0, col), N * N );
            sitesWithoutVirtualBottom.union(twoDTo1D(0, col), N * N );
        }
        //the last row union to virtual top
        for (int col = 0; col < length; col ++) {
            sites.union(twoDTo1D(length - 1, col), N * N + 1);
        }

        for (int i = 0; i < openSites.length; i++) {
            openSites[i] = false;
        }
        numberOfOpenSites = 0;
    }

    private int topCoordinate() {
        return length * length;
    }

    private int bottomCoordinate() {
        return length * length + 1;
    }

    private int twoDTo1D (int row, int col) {
        return (row * length  + col );
    }

    private void validate (int row, int col) {
        if (row >= length || row < 0) {
            throw new IndexOutOfBoundsException("row is out of bounds.");
        }
        if (col >= length || row < 0) {
            throw new IndexOutOfBoundsException("col is out of bounds.");
        }

    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col)  {
        validate(row, col);
        int site = twoDTo1D(row, col);
        if (!openSites[site]) {
            openSites[site] = true;
        } else {
            return;
        }
        numberOfOpenSites ++;
        if (row > 0) {
            int topCoordinate = twoDTo1D(row - 1, col);
            if (openSites[topCoordinate]) {
                sites.union(site, topCoordinate);
                sitesWithoutVirtualBottom.union(site, topCoordinate);
            }
        }
        if (row < length - 1) {
            int bottomCoordinate = twoDTo1D(row + 1, col);
            if (openSites[bottomCoordinate]) {
                sites.union(site, bottomCoordinate);
                sitesWithoutVirtualBottom.union(site, bottomCoordinate);
            }
        }
        if (col > 0) {
            int leftCoordinate = twoDTo1D(row , col - 1);
            if (openSites[leftCoordinate]) {
                sites.union(site, leftCoordinate);
                sitesWithoutVirtualBottom.union(site, leftCoordinate);
            }
        }
        if (col < length - 1) {
            int rightCoordinate = twoDTo1D(row , col + 1);
            if (openSites[rightCoordinate]) {
                sites.union(site, rightCoordinate);
                sitesWithoutVirtualBottom.union(site, rightCoordinate);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        int site = twoDTo1D(row, col);
        if (openSites[site]) {
            return true;
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) return false;

        int site = twoDTo1D(row, col);
        return sitesWithoutVirtualBottom.connected(site, topCoordinate());
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return sites.connected(topCoordinate(), bottomCoordinate());
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }
}
