package com.tictactoe;

import com.tictactoe.command.MoveCommand;
import com.tictactoe.state.GameState;
import com.tictactoe.state.PlayingState;
import com.tictactoe.state.WonState;
import com.tictactoe.state.DrawState;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameState state;
    private List<MoveCommand> moveHistory;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.state = new PlayingState();
        this.moveHistory = new ArrayList<>();
    }

    public void playMove(int row, int col) {
        if (!(state instanceof PlayingState)) {
            throw new IllegalStateException("Game is not in playing state");
        }
        MoveCommand move = new MoveCommand(board, row, col, currentPlayer.getSymbol());
        move.execute();
        moveHistory.add(move);
        if (board.checkWin(currentPlayer.getSymbol())) {
            state = new WonState();
        } else if (board.checkDraw()) {
            state = new DrawState();
        } else {
            switchPlayer();
        }
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public void undoLastMove() {
        if (!moveHistory.isEmpty()) {
            MoveCommand lastMove = moveHistory.remove(moveHistory.size() - 1);
            lastMove.undo();
            switchPlayer();
            state = new PlayingState();
        }
    }

    public void undoToStep(int index) {
        if (index < 0 || index >= moveHistory.size()) {
            throw new IllegalArgumentException("Invalid move index");
        }
        while (moveHistory.size() > index) {
            MoveCommand lastMove = moveHistory.remove(moveHistory.size() - 1);
            lastMove.undo();
            switchPlayer();
        }
        state = new PlayingState();
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public List<MoveCommand> getMoveHistory() {
        return moveHistory;
    }
}