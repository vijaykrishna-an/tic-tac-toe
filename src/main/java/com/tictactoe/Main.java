package com.tictactoe;

import com.tictactoe.command.MoveCommand;
import com.tictactoe.state.DrawState;
import com.tictactoe.state.WonState;
import com.tictactoe.strategy.HumanStrategy;
import com.tictactoe.strategy.RandomAIStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = null;

        // Choose game mode
        System.out.print("Choose mode (1: Human vs Human, 2: Human vs Easy AI):");
        int mode;
        while (true) {
            try {
                mode = Integer.parseInt(scanner.nextLine());
                if (mode >= 1 && mode <= 2) break;
                System.out.println("Invalid mode. Choose 1 or 2:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number (1 or 2):");
            }
        }

        // Initialize players based on mode
        Player player1 = new Player('X', new HumanStrategy());
        Player player2;
        switch (mode) {
            case 1:
                player2 = new Player('O', new HumanStrategy());
                break;
            case 2:
                player2 = new Player('O', new RandomAIStrategy());
                break;
            default:
                throw new IllegalStateException("Unexpected mode: " + mode);
        }

        // Initialize game
        game = new Game(player1, player2);

        // Game loop
        while (!(game.getState() instanceof WonState || game.getState() instanceof DrawState)) {

            // Display current player and menu
            System.out.println("\nIt's " + game.getCurrentPlayer().getSymbol() + "'s turn.");
            System.out.println("1) Undo to a specific step");
            System.out.println("2) Next move");
            System.out.print("Enter option(1 or 2): ");
            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 1 || choice == 2) break;
                    System.out.println("Invalid choice. Enter 1 or 2:");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number (1 or 2):");
                }
            }

            if (choice == 1) {
                // Undo to specific step
                if (game.getMoveHistory().isEmpty()) {
                    System.out.println("No moves to undo");
                    continue;
                }
                System.out.println("\nMove history:");
                for (int i = 0; i < game.getMoveHistory().size(); i++) {
                    MoveCommand move = game.getMoveHistory().get(i);
                    System.out.println("Move " + i + ": " + move.getPlayerSymbol() + " at (" + move.getRow() + ", " + move.getCol() + ")");
                }
                System.out.print("Enter move index to undo to (0 to " + (game.getMoveHistory().size() - 1) + ") or -1 to cancel:");
                int index;
                while (true) {
                    try {
                        index = Integer.parseInt(scanner.nextLine());
                        if (index == -1 || (index >= 0 && index < game.getMoveHistory().size())) break;
                        System.out.println("Invalid index. Enter 0 to " + (game.getMoveHistory().size() - 1) + " or -1 to cancel:");
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number:");
                    }
                }
                if (index != -1) {
                    game.undoToStep(index);
                    System.out.println("Undo successful");
                    continue;
                }
            } else {
                // Next move
                int[] move = game.getCurrentPlayer().getStrategy().getMove(game.getBoard(), game.getCurrentPlayer().getSymbol());
                try {
                    game.playMove(move[0], move[1]);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid move: " + e.getMessage());
                }
            }
        }

        // Game over
        game.getBoard().notifyObservers();
        if (game.getState() instanceof WonState) {
            System.out.println("Game over! Winner: " + game.getCurrentPlayer().getSymbol());
        } else {
            System.out.println("Game over! It's a draw!");
        }

        scanner.close();
    }
}