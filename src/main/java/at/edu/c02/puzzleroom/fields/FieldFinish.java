package at.edu.c02.puzzleroom.fields;

import at.edu.c02.puzzleroom.Direction;
import at.edu.c02.puzzleroom.GameBoard;

/**
 * A finish field. When the player enters this field, they win the game.
 */
public class FieldFinish extends BaseField {
    public FieldFinish(GameBoard gameBoard, char name, int row, int col) {
        super(gameBoard, name, row, col);
    }

    public void initialize() {
    }

    public boolean enterField(Direction direction) {
        gameBoard.getPlayer().walkStep();
        setPlayerPositionToField();
        gameBoard.setFinished();
        return true;
    }

    public boolean leaveField(Direction direction) {
        return true;
    }
}
