package com.tictactoe.state;

import com.tictactoe.Game;

public class WonState implements GameState {
    @Override
    public void handle(Game game) {
        // Game is over, no further moves
    }
}