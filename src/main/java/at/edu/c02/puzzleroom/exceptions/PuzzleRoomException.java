package at.edu.c02.puzzleroom.exceptions;

public class PuzzleRoomException extends Exception {

    public PuzzleRoomException() {
    }

    public PuzzleRoomException(String arg0) {
        super(arg0);
    }


    public PuzzleRoomException(Throwable arg0) {
        super(arg0);
    }

    public PuzzleRoomException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
}
