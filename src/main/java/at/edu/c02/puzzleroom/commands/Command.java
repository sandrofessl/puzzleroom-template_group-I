package at.edu.c02.puzzleroom.commands;

import at.edu.c02.puzzleroom.GameBoard;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomException;

/**
 * A Command represents an action from the user.
 * When a user inputs something, a new command is created by a `CommandFactory`
 * and will then be executed using the `execute` function.
 * <p>
 * When you create your own command, create a new `CommandSomething` class
 * that implements this interface.
 */
public interface Command {
    /**
     * Execute this command and change the GameBoard's state accordingly
     * @param gameBoard The GameBoard that is affected by this command
     * @throws PuzzleRoomException When this command is not possible
     */
    void execute(GameBoard gameBoard) throws PuzzleRoomException;
}
