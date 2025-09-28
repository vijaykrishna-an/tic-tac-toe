package com.tictactoe.strategy;

import com.tictactoe.Board;

import java.util.Scanner;

public class HumanStrategy implements PlayerStrategy {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int[] getMove(Board board, char symbol) {
        System.out.print("\nEnter row (0-2) and column (0-2) for " + symbol + " (e.g., '0 1'):");
        while (true) {
            try {
                String[] input = scanner.nextLine().trim().split("\\s+");
                if (input.length != 2) {
                    throw new IllegalArgumentException("Please enter two numbers separated by a space");
                }
                int row = Integer.parseInt(input[0]);
                int col = Integer.parseInt(input[1]);
                if (board.isValidMove(row, col)) {
                    return new int[]{row, col};
                } else {
                    System.out.println("Invalid move. Try again (row col, e.g., '0 1'):");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Enter row and column (0-2, e.g., '0 1'):");
            }
        }
    }
}
