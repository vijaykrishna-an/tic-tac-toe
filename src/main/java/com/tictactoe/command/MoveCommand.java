package com.tictactoe.command;

import com.tictactoe.Board;

public class MoveCommand {
    private Board board;
    private int row;
    private int col;
    private char playerSymbol;

    public MoveCommand(Board board, int row, int col, char playerSymbol) {
        this.board = board;
        this.row = row;
        this.col = col;
        this.playerSymbol = playerSymbol;
    }

    public void execute() {
        board.makeMove(row, col, playerSymbol);
    }

    public void undo() {
        board.undoMove(row, col);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }
}