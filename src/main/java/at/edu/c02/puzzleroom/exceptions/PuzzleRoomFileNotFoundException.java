package at.edu.c02.puzzleroom.exceptions;

public class PuzzleRoomFileNotFoundException extends PuzzleRoomException {
    public PuzzleRoomFileNotFoundException() {
        super("File not found.");
    }
}
