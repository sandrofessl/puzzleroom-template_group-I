package at.edu.c02.puzzleroom.exceptions;

public class PuzzleRoomNoMazeLoadedException extends PuzzleRoomException {
    public PuzzleRoomNoMazeLoadedException() {
        super("No maze loaded.");
    }
}
