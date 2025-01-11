package at.edu.c02.puzzleroom.exceptions;

public class PuzzleRoomInvalidMoveException extends PuzzleRoomException {
    public PuzzleRoomInvalidMoveException() {
        super("Invalid move.");
    }
}
