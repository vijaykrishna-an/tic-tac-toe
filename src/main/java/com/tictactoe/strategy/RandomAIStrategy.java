package com.tictactoe.strategy;

import com.tictactoe.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAIStrategy implements PlayerStrategy {
    private Random random = new Random();

    @Override
    public int[] getMove(Board board, char symbol) {
        List<int[]> validMoves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isValidMove(i, j)) {
                    validMoves.add(new int[]{i, j});
                }
            }
        }
        if (validMoves.isEmpty()) {
            throw new IllegalStateException("No valid moves available");
        }
        int[] move = validMoves.get(random.nextInt(validMoves.size()));
        System.out.println("AI (" + symbol + ") selects move: (" + move[0] + ", " + move[1] + ")");
        return move;
    }
}