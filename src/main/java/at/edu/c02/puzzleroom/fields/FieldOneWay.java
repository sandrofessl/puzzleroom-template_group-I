package at.edu.c02.puzzleroom.fields;

import at.edu.c02.puzzleroom.Direction;
import at.edu.c02.puzzleroom.GameBoard;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomException;

public class FieldOneWay extends BaseField {

    public FieldOneWay(GameBoard gameBoard, char name, int row, int col) {
        super(gameBoard, name, row, col);
    }

    @Override
    public void initialize() {

    }

    @Override
    public boolean enterField(Direction direction) {
        gameBoard.getPlayer().walkStep();
        setPlayerPositionToField();
        return true;
    }

    @Override
    public boolean leaveField(Direction direction) {
        if (name == '^' && direction.name().equals("Up")) {
            return true;
        } else if (name == '<' && direction.name().equals("Left")) {
            return true;
        } else if (name == '>' && direction.name().equals("Right")) {
            return true;
        } else if (name == 'v' && direction.name().equals("Down")) {
            return true;
        } else {
            return false;
        }
    }
}
