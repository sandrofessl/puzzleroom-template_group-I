package at.edu.c02.puzzleroom.fields;

import at.edu.c02.puzzleroom.GameBoard;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomException;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomInvalidFileException;

/**
 * This class is responsible for converting a character from a puzzle room file
 * into a specific `Field`.
 */
public class FieldFactory {
    private final GameBoard gameBoard;

    public FieldFactory(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Create a new field based on the given fieldName.
     * This will be called by the `load` command.
     * Extend it to add new field types
     *
     * @param fieldName The field name, e.g. `#` for a wall
     * @param row       The field's position: row
     * @param col       The field's position: column
     * @return A new field of the given type on the given position
     * @throws PuzzleRoomException When the fieldName is not valid
     */
    public Field createField(char fieldName, int row, int col) throws PuzzleRoomException {
        return switch (fieldName) {
            case ' ' -> new FieldPath(gameBoard, fieldName, row, col);
            case '#' -> new FieldWall(gameBoard, fieldName, row, col);
            case 'o' -> new FieldStart(gameBoard, fieldName, row, col);
            case 'x' -> new FieldFinish(gameBoard, fieldName, row, col);
            case '<', '>', '^', 'v' -> new FieldOneWay(gameBoard, fieldName, row, col);
            case '@' -> new FieldIce(gameBoard, fieldName, row, col);
            default -> throw new PuzzleRoomInvalidFileException("Field " + fieldName + " is not a valid field");
        };
    }
}
