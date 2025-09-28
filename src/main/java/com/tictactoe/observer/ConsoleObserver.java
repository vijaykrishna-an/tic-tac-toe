package com.tictactoe.observer;

import com.tictactoe.Board;

public class ConsoleObserver implements BoardObserver {
    @Override
    public void update(Board board) {
        char[][] grid = board.getGridCopy();
        System.out.println("\nUpdate:");
        for (int i = 0; i < 3; i++) {
            System.out.println(grid[i][0] + " | " + grid[i][1] + " | " + grid[i][2]);
        }
        System.out.println();
    }
}