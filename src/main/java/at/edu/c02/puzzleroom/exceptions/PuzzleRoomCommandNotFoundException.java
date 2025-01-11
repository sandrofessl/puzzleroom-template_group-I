package at.edu.c02.puzzleroom.exceptions;

public class PuzzleRoomCommandNotFoundException extends PuzzleRoomException {
    public PuzzleRoomCommandNotFoundException() {
        super("Command not found.");
    }
}
