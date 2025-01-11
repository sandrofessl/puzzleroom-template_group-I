package at.edu.c02.puzzleroom.fields;

import at.edu.c02.puzzleroom.Direction;
import at.edu.c02.puzzleroom.GameBoard;

/**
 * A start field. When initialized, sets the player to its position. Then, it works like a `Path` field.
 */
public class FieldStart extends BaseField {
    public FieldStart(GameBoard gameBoard, char name, int row, int col) {
        super(gameBoard, name, row, col);
    }

    public void initialize() {
        gameBoard.getPlayer().setPosition(row, col);
    }

    public boolean enterField(Direction direction) {
        gameBoard.getPlayer().walkStep();
        setPlayerPositionToField();
        return true;
    }

    public boolean leaveField(Direction direction) {
        return true;
    }
}
