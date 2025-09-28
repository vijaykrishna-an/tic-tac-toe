package com.tictactoe.state;

import com.tictactoe.Game;

public interface GameState {
    void handle(Game game);
}