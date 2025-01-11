package at.edu.c02.puzzleroom.commands;

import at.edu.c02.puzzleroom.GameBoard;
import at.edu.c02.puzzleroom.Player;
import at.edu.c02.puzzleroom.exceptions.*;

/**
 * This command allows the player to move up/down/left/right one step
 * Example usage: `move left`
 */
public class CommandMove implements Command {
    private final String direction;

    public CommandMove(String[] arguments) throws PuzzleRoomException {
        if(arguments.length != 1) {
            throw new PuzzleRoomInvalidArgumentsException();
        }
        direction = arguments[0];
    }

    public void execute(GameBoard gameBoard) throws PuzzleRoomException {
        // The player handles all movement logic, we just parse the input and
        // call the correct function
        Player player = gameBoard.getPlayer();
        if(player == null) {
            throw new PuzzleRoomInvalidMoveException();
        }
        boolean success = switch(direction) {
            case "left" -> player.moveLeft();
            case "right" -> player.moveRight();
            case "up" -> player.moveUp();
            case "down" -> player.moveDown();
            default -> throw new PuzzleRoomInvalidArgumentsException();
        };

        if(success) {
            // If the move was successful, automatically execute a show command
            // to show the player the new state
            CommandShow showCommand = new CommandShow();
            showCommand.execute(gameBoard);
        } else {
            // If the move was not successful - e.g. because the player moved
            // into a wall, or already finished the maze, then throw an Exception
            throw new PuzzleRoomInvalidMoveException();
        }
    }
}
