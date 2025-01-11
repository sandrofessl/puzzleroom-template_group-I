package at.edu.c02.puzzleroom.fields;

import at.edu.c02.puzzleroom.Direction;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomException;

/**
 * Interface definition for a field of a puzzle room.
 */
public interface Field {
    /**
     * Return's the fields current name, used by the `show` command.
     * Already implemented in `BaseField`.
     * @return The field's current name
     */
    char getName();

    /**
     * Custom initialization code for this field.
     * Will be called _after_ all fields are loaded and allows you to access
     * e.g. `gameBoard.getFields()` which is not possible in the constructor.
     */
    void initialize() throws PuzzleRoomException;

    /**
     * The player tries to enter this field, moving in the given direction.
     * @param direction The direction in which the player is currently moving onto this field
     * @return True if the movement is possible, false otherwise
     */
    boolean enterField(Direction direction);

    /**
     * The player tries to leave this field, moving in the given direction.
     * @param direction The direction in which the player wants to leave this field
     * @return True if the movement is possible, false otherwise
     */
    boolean leaveField(Direction direction);
}
