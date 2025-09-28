package com.tictactoe.strategy;

import com.tictactoe.Board;

/**
 * Strategy interface for player moves.
 */
public interface PlayerStrategy {
    int[] getMove(Board board, char symbol);
}
