package at.edu.c02.puzzleroom.commands;

import at.edu.c02.puzzleroom.GameBoard;
import at.edu.c02.puzzleroom.Player;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomException;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomInvalidArgumentsException;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomInvalidMoveException;

public class CommandFastmove implements Command {
    private final String[] directions;

    public CommandFastmove(String[] arguments) throws PuzzleRoomException {
        directions = arguments;
    }

    public void execute(GameBoard gameBoard) throws PuzzleRoomException {
        // The player handles all movement logic, we just parse the input and
        // call the correct function
        Player player = gameBoard.getPlayer();
        if (player == null) {
            throw new PuzzleRoomInvalidMoveException();
        }

        boolean success = false;
        for (String direction : directions) {
            try {
                switch (direction) {
                    case "l":
                        if (player.moveLeft()) {
                            success = true;
                        } else {
                            throw new PuzzleRoomInvalidMoveException();
                        }
                        break;
                    case "r":
                        if (player.moveRight()) {
                            success = true;
                        } else {
                            throw new PuzzleRoomInvalidMoveException();
                        }
                        break;
                    case "u":
                        if (player.moveUp()) {
                            success = true;
                        } else {
                            throw new PuzzleRoomInvalidMoveException();
                        }
                        break;
                    case "d":
                        if (player.moveDown()) {
                            success = true;
                        } else {
                            throw new PuzzleRoomInvalidMoveException();
                        }
                        break;
                    default:
                        throw new PuzzleRoomInvalidArgumentsException();
                }
            } catch (PuzzleRoomInvalidMoveException e) {
                System.out.println(e.getMessage());
            }
        }

        if (success) {
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
