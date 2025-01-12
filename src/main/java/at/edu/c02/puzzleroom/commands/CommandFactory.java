package at.edu.c02.puzzleroom.commands;

import at.edu.c02.puzzleroom.exceptions.PuzzleRoomCommandNotFoundException;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomException;

/**
 * This class is responsible for converting a user input into
 * a specific Command.
 */
public class CommandFactory {
    /**
     * Create a Command for the given user input.
     * Extend this function if you want to add new commands
     * Some commands take arguments - simply pass them to the created command
     * All arguments are separated by space, e.g. if a user enters
     * `load a b c`
     * then `command = "load"` and `arguments = ["a", "b", "c"]
     *
     * @param command First word of the user input
     * @param arguments Arguments of the command
     * @return An instance of the corresponding command
     * @throws PuzzleRoomException When the command was not found
     */
    public Command createCommand(String command, String[] arguments) throws PuzzleRoomException {
        return switch (command.toLowerCase()) {
            case "quit" -> new CommandQuit();
            case "load" -> new CommandLoad(arguments);
            case "show" -> new CommandShow();
            case "move" -> new CommandMove(arguments);
            case "fastmove" -> new CommandFastmove(arguments);
            default -> throw new PuzzleRoomCommandNotFoundException();
        };
    }
}
