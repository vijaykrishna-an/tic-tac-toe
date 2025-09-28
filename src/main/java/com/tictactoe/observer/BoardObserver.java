package com.tictactoe.observer;

import com.tictactoe.Board;

public interface BoardObserver {
    void update(Board board);
}