
# Tic-Tac-Toe Application

This is a console-based Tic-Tac-Toe game built in Java, supporting two modes: Human vs Human and Human vs Easy AI (random moves). It’s designed with clean code and uses several behavioral design patterns to keep things modular and extensible. Below, you’ll find details on the patterns used, what each class does, and how to compile and run the game on your machine.

Behavioral Patterns Implemented

The app uses these behavioral design patterns to manage interactions and make the code flexible:

- Strategy Pattern: Handles different ways players make moves (human input or random AI). It lets you swap move strategies without changing the player logic.
- State Pattern: Manages game states like playing, won, or draw. Each state has its own behavior, making it easy to switch between them.
- Observer Pattern: Keeps the console updated when the board changes (e.g., after moves or undos). The board notifies its observers to display the latest state.
- Command Pattern: Wraps moves in objects that can be executed or undone. This powers the undo feature by storing move history.

These patterns make the app easy to maintain and extend (e.g., adding new AI strategies later).

Class Descriptions

All classes live in the `com.tictactoe` package. Here’s what each one does:

- Board.java: Manages the 3x3 game board. It tracks the grid, validates moves, checks for wins (rows, columns, diagonals) or draws, handles undos, and notifies observers (like the console) when the board updates.
- Game.java: Runs the game’s core logic. It tracks players, manages turns, updates game state (playing, won, draw), stores move history, and handles undoing to specific steps.
- Player.java: Represents a player (X or O) with a move strategy (human or AI). It delegates move selection to the strategy.
- Main.java: The starting point. It prompts for game mode (1: Human vs Human, 2: Human vs Easy AI), handles user input, runs the game loop, and offers undo/next move options.
- command/MoveCommand.java: Stores a move (row, column, symbol) with methods to execute or undo it. This supports reversible actions for the undo feature.
- observer/BoardObserver.java: An interface for objects that need board updates. It defines how observers react to changes.
- observer/ConsoleObserver.java: Prints the board to the console whenever it changes, making the game state visible.
- state/DrawState.java: Handles the game when it ends in a draw (board full, no winner).
- state/GameState.java: An interface for game states, defining how each state behaves.
- state/PlayingState.java: Manages the game while it’s ongoing, allowing moves and showing whose turn it is.
- state/WonState.java: Handles the game when a player wins (three in a row, column, or diagonal).
- strategy/HumanStrategy.java: Reads row/column input from the user for human player moves.
- strategy/PlayerStrategy.java: An interface for move strategies, defining how moves are chosen.
- strategy/RandomAIStrategy.java: Picks random valid moves for the Easy AI in mode 2.

Prerequisites for Compilation and Execution

To get the game running, you’ll need:

- Java Development Kit (JDK): Version 11 or higher. Check with `java -version`.
- Apache Maven: Version 3.6.0 or higher. Check with `mvn -version`. Download from the Apache Maven website if needed.
- Command Prompt/Terminal: Use Command Prompt on Windows (since your path is `C:\Users\ANVK\Desktop\tic-tac-toe`).
- Project Files: Ensure all source files are in `src/main/java/com/tictactoe` as per the structure below.
- No Test Directory: Tests have been removed, so no test-related setup is needed.

The project structure should look like this:

```
tic-tac-toe/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── tictactoe/
│   │               ├── Board.java
│   │               ├── Game.java
│   │               ├── Player.java
│   │               ├── Main.java
│   │               ├── command/
│   │               │   └── MoveCommand.java
│   │               ├── observer/
│   │               │   ├── BoardObserver.java
│   │               │   └── ConsoleObserver.java
│   │               ├── state/
│   │               │   ├── DrawState.java
│   │               │   ├── GameState.java
│   │               │   ├── PlayingState.java
│   │               │   └── WonState.java
│   │               ├── strategy/
│   │               │   ├── HumanStrategy.java
│   │               │   ├── PlayerStrategy.java
│   │               │   └── RandomAIStrategy.java
├── pom.xml
└── README.md
```

Compilation and Execution Illustration

Here’s how to compile and run the game step-by-step. These commands are for Windows Command Prompt (using backslashes `\`). Replace `YourUsername` with your actual username (e.g., `ANVK`).

1. Open Command Prompt:
   - Press `Win + R`, type `cmd`, and hit Enter to open Command Prompt.

2. Navigate to Project Root:
   - Move to the project folder:
     ```
     cd C:\Users\YourUsername\Desktop\tic-tac-toe
     ```

3. Clean Previous Build Artifacts:
   - Clear out old compiled files to avoid issues:
     ```
     mvn clean
     ```
   - You’ll see:
     ```
     [INFO] Deleting C:\Users\YourUsername\Desktop\tic-tac-toe\target
     [INFO] BUILD SUCCESS
     ```

4. Compile the Application:
   - Compile all source files:
     ```
     mvn compile
     ```
   - Expected output:
     ```
     [INFO] Compiling X source files to C:\Users\YourUsername\Desktop\tic-tac-toe\target\classes
     [INFO] BUILD SUCCESS
     ```
   - This creates `.class` files in `target\classes\com\tictactoe`.

5. Run the Application:
   - Start the game:
     ```
     mvn exec:java -Dexec.mainClass="com.tictactoe.Main"
     ```
   - The game begins with:
     ```
     Choose mode (1: Human vs Human, 2: Human vs Easy AI):
     ```
   - Enter `1` or `2` to pick a mode, then follow prompts to play (e.g., `0 0` for a move at row 0, column 0).

6. Automate with a Batch File (Optional):
   - Create a file called `run.bat` in the project root using Notepad:
     ```
     @echo off
     mvn clean
     mvn compile
     mvn exec:java -Dexec.mainClass="com.tictactoe.Main"
     ```
   - Save and run it:
     ```
     run.bat
     ```
   - This runs all steps (clean, compile, execute) in one go.

7. What to Expect When Running:
   - After starting, choose a mode (`1` or `2`). Invalid inputs (e.g., `3`, `abc`) show: `Invalid mode. Choose 1 or 2:`.
   - The board displays (e.g., ` | | ` for empty cells). For each turn:
     ```
     It's X's turn.
     1) Undo to a specific step
     2) Next move
     ```
   - Choose `2` to make a move (e.g., `0 0` for top-left) or `1` to undo (select a move index from history).
   - Invalid moves (e.g., `-1 0`, occupied cells) show: `Invalid move: ...`.
   - Invalid undo indexes (e.g., `10`, `abc`) show: `Invalid index...`.
   - Game ends with: `Game over! Winner: X` or `Game over! It's a draw!`, followed by an option to undo (`y/n`).

Troubleshooting

- Compilation Fails:
  - Check that all source files are in `src/main/java/com/tictactoe`.
  - Ensure `pom.xml` has the Maven Compiler and Exec plugins for Java 11.
  - Run with debug to see details:
    ```
    mvn compile -e -X
    ```
- Maven or Java Missing:
  - Install JDK 11+ and set JAVA_HOME (e.g., `set JAVA_HOME=C:\Program Files\Java\jdk-11`).
  - Install Maven and add its `bin` folder to PATH (e.g., `C:\Program Files\Maven\bin`).
- Runtime Issues:
  - If the game loops or crashes, verify `Board.java`, `Game.java`, and `Main.java` match the latest versions (fixes prevent move/undo looping).
  - Run with debug:
    ```
    mvn exec:java -Dexec.mainClass="com.tictactoe.Main" -X
    ```
- File Issues:
  - Use a text editor (e.g., Notepad++, VS Code) to check for invisible characters (use UTF-8 encoding, no BOM).

That’s it! You’re ready to compile and play Tic-Tac-Toe. Enjoy!