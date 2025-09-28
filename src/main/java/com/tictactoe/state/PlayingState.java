package com.tictactoe.state;

import com.tictactoe.Game;

public class PlayingState implements GameState {
    @Override
    public void handle(Game game) {
        // Game continues, no state change needed
    }
}