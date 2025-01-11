package at.edu.c02.puzzleroom.commands;

import at.edu.c02.puzzleroom.GameBoard;

/**
 * This command quits the program.
 * Example usage: `quit`
 */
public class CommandQuit implements Command {
    public void execute(GameBoard gameBoard) {
        gameBoard.quit();
    }
}
