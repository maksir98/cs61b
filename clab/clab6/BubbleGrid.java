import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.List;

public class BubbleGrid {
    private int[][] grid;


    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    private int twoDTo1D(int row, int col) {
        return row * (grid[0].length) + col;
    }

    private WeightedQuickUnionUF analysisGrid(int[][] grid){
        int R = grid.length;
        int C = grid[0].length;
        WeightedQuickUnionUF newDjs = new WeightedQuickUnionUF(R * C + 1);
        for(int row = 0; row < R; row ++) {
            for (int col = 0; col < C; col++) {
                if (grid[row][col] == 1) {
                    //将顶部的1与虚拟顶点连起来
                    if (row == 0) {
                        newDjs.union(col, R * C);
                    }
                    //将1与下面的1连起来
                    if (row != (R - 1) && grid[row + 1][col] == 1) {
                        int thisGrid = twoDTo1D(row, col);
                        int underGrid = twoDTo1D(row + 1, col);
                        newDjs.union(thisGrid, underGrid);
                    }
                    //将1与右边的1连起来
                    if (col != (C - 1) && grid[row][col + 1] == 1) {
                        int thisGrid = twoDTo1D(row, col);
                        int nextGrid = twoDTo1D(row , col + 1);
                        newDjs.union(thisGrid, nextGrid);
                    }
                }
            }
        }
        return newDjs;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int R = grid.length;
        int C = grid[0].length;
        int[] result = new int[darts.length];
        for (int i = 0; i < darts.length; i++) {
            int[] dart = darts[i];
            grid[dart[0]][dart[1]] = 0;
            WeightedQuickUnionUF newDjs = analysisGrid(grid);
            for (int row = 0; row < R; row++) {
                for (int col = 0; col < C; col++) {
                    if (grid[row][col] == 1) {
                        int index = twoDTo1D(row, col);
                        if (!newDjs.connected(index, R * C)){
                            grid[row][col] = 0;
                            result[i] ++;
                        }
                    }
                }
            }
        }

        return result;
    }
}
