package at.edu.c02.puzzleroom.fields;

import at.edu.c02.puzzleroom.Direction;
import at.edu.c02.puzzleroom.GameBoard;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomException;

public class FieldIce extends BaseField {

    public FieldIce(GameBoard gameBoard, char name, int row, int col) {
        super(gameBoard, name, row, col);
    }

    @Override
    public void initialize() throws PuzzleRoomException {

    }

    @Override
    public boolean enterField(Direction direction) {
        Field nextField = null;
        switch (direction) {
            case Up -> nextField = gameBoard.getField(row - 1, col);
            case Down -> nextField = gameBoard.getField(row + 1, col);
            case Left -> nextField = gameBoard.getField(row, col - 1);
            case Right -> nextField = gameBoard.getField(row, col + 1);
        }
        if (nextField.getName() == '#') {
            gameBoard.getPlayer().walkStep();
            setPlayerPositionToField();
            return false;
        } else {
            nextField.enterField(direction);
            return true;
        }
    }

    @Override
    public boolean leaveField(Direction direction) {
        return true;
    }
}
