package at.edu.c02.puzzleroom.exceptions;

public class PuzzleRoomInvalidFileException extends PuzzleRoomException {
    public PuzzleRoomInvalidFileException() {
        super("Invalid file.");
    }

    public PuzzleRoomInvalidFileException(String message) {
        super("Invalid file: " + message);
    }
}
