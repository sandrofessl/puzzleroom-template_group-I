package at.edu.c02.puzzleroom.commands;

import at.edu.c02.puzzleroom.GameBoard;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomException;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomFileNotFoundException;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomInvalidArgumentsException;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomInvalidFileException;
import at.edu.c02.puzzleroom.fields.Field;
import at.edu.c02.puzzleroom.fields.FieldFactory;
import at.edu.c02.puzzleroom.fields.FieldFinish;
import at.edu.c02.puzzleroom.fields.FieldStart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This command loads a new maze from a file.
 * You probably shouldn't have to change anything here, but you might want to look
 * at the code to understand what happens after a file was loaded.
 * <p>
 * Example usage: `load test.maze`
 */
public class CommandLoad implements Command {
    private final String filePath;

    public CommandLoad(String[] arguments) throws PuzzleRoomException {
        if (arguments.length != 1) {
            throw new PuzzleRoomInvalidArgumentsException();
        }
        filePath = arguments[0];
    }

    public void execute(GameBoard gameBoard) throws PuzzleRoomException {
        // Create a FieldFactory to match characters to fields, and save those fields
        // into a 2D list
        FieldFactory factory = new FieldFactory(gameBoard);
        List<List<Field>> fields = new ArrayList<>();
        boolean hasStartField = false;
        boolean hasFinishField = false;
        try {
            // Read/Parse the file - you shouldn't have to change anything here
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                List<Field> fieldRow = new ArrayList<>();
                int col = 0;
                for (char c : line.toCharArray()) {
                    // The FieldFactory decides which character matches which field
                    Field field = factory.createField(c, row, col);
                    // Check that we only have 1 start and 1 finish field
                    if (field instanceof FieldStart) {
                        if (hasStartField) {
                            throw new PuzzleRoomInvalidFileException("Level has too many start fields");
                        }
                        hasStartField = true;
                    } else if (field instanceof FieldFinish) {
                        if (hasFinishField) {
                            throw new PuzzleRoomInvalidFileException("Level has too many finishes");
                        }
                        hasFinishField = true;
                    }
                    fieldRow.add(field);
                    col++;
                }
                fields.add(fieldRow);
                row++;
            }
        } catch (IOException e) {
            throw new PuzzleRoomFileNotFoundException();
        } catch (Exception e) {
            throw new PuzzleRoomInvalidFileException(e.getMessage());
        }

        // Ensure that we have a start and a finish field
        if (!hasStartField || !hasFinishField) {
            throw new PuzzleRoomInvalidFileException("Level has no start/finish field");
        }

        // Initialize the game board with the loaded fields
        gameBoard.initialize(fields);

        // If we loaded the file successfuly, automatically execute a show
        // command to show the player the new field
        CommandShow showCommand = new CommandShow();
        showCommand.execute(gameBoard);
    }
}
