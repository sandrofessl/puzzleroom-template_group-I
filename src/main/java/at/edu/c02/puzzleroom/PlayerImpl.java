package at.edu.c02.puzzleroom;

import at.edu.c02.puzzleroom.fields.Field;

public class PlayerImpl implements Player {
    private final GameBoard gameBoard;
    private int row;
    private int col;
    private int steps;

    public PlayerImpl(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean moveUp() {
        return moveTo(row - 1, col, Direction.Up);
    }

    public boolean moveDown() {
        return moveTo(row + 1, col, Direction.Down);
    }

    public boolean moveLeft() {
        return moveTo(row, col - 1, Direction.Left);
    }

    public boolean moveRight() {
        return moveTo(row, col + 1, Direction.Right);
    }

    private boolean moveTo(int row, int col, Direction direction) {
        // Do not allow movement after the player reached the "finish" field
        if (gameBoard.isFinished()) {
            return false;
        }
        // Get the current field and check if we can leave it
        Field currentField = gameBoard.getField(this.row, this.col);
        boolean canLeave = currentField.leaveField(direction);
        if(!canLeave) {
            return false;
        }

        // If we can leave, get the new field and try to enter it.
        // The new field's `enterField` position will update the player position
        // if it was successful.
        // It is possible that the new position is somewhere other than row/col
        // e.g. for teleporters, ice fields
        Field newField = gameBoard.getField(row, col);
        boolean canEnter = newField.enterField(direction);
        if(!canEnter) {
            return false;
        }
        return true;
    }

    public void walkStep() {
        walkSteps(1);
    }

    public void walkSteps(int amount) {
        steps += amount;
        // Negative step counts are not allowed, even when walking over a bonus field
        if(steps < 0) {
            steps = 0;
        }
    }

    public int getStepCount() {
        return this.steps;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
