package com.tictactoe;

import com.tictactoe.observer.BoardObserver;
import com.tictactoe.observer.ConsoleObserver;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private char[][] grid;
    private List<BoardObserver> observers;

    public Board() {
        grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
        observers = new ArrayList<>();
        observers.add(new ConsoleObserver());
    }

    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }
        return grid[row][col] == ' ';
    }

    public void makeMove(int row, int col, char symbol) {
        if (!isValidMove(row, col)) {
            throw new IllegalArgumentException("Invalid move at (" + row + ", " + col + ")");
        }
        grid[row][col] = symbol;
        notifyObservers();
    }

    public void undoMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            throw new IllegalArgumentException("Invalid undo coordinates");
        }
        if (grid[row][col] == ' ') {
            throw new IllegalArgumentException("Cannot undo empty cell at (" + row + ", " + col + ")");
        }
        grid[row][col] = ' ';
        notifyObservers();
    }

    public boolean checkWin(char symbol) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (grid[0][j] == symbol && grid[1][j] == symbol && grid[2][j] == symbol) {
                return true;
            }
        }
        // Check diagonals
        if (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) {
            return true;
        }
        if (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol) {
            return true;
        }
        return false;
    }

    public boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return !checkWin('X') && !checkWin('O');
    }

    public char[][] getGridCopy() {
        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, 3);
        }
        return copy;
    }

    public void addObserver(BoardObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (BoardObserver observer : observers) {
            observer.update(this);
        }
    }
}
