package at.edu.c02.puzzleroom.fields;

import at.edu.c02.puzzleroom.Direction;
import at.edu.c02.puzzleroom.GameBoard;

/**
 * A wall field. The player can never move onto this field.
 */
public class FieldWall extends BaseField {
    public FieldWall(GameBoard gameBoard, char name, int row, int col) {
        super(gameBoard, name, row, col);
    }

    public void initialize() {
    }

    public boolean enterField(Direction direction) {
        return false;
    }

    public boolean leaveField(Direction direction) {
        return false;
    }
}
