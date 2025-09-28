package com.tictactoe;

import com.tictactoe.strategy.PlayerStrategy;

public class Player {
    private char symbol;
    private PlayerStrategy strategy;

    public Player(char symbol, PlayerStrategy strategy) {
        this.symbol = symbol;
        this.strategy = strategy;
    }

    public char getSymbol() {
        return symbol;
    }

    public PlayerStrategy getStrategy() {
        return strategy;
    }
}