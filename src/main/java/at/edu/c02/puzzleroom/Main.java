package at.edu.c02.puzzleroom;
import java.io.IOException;


public class Main {
    /**
     * This is the main program entry point.
     * You should not have to change anything here.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("=== Puzzle Room ===");
        System.out.println("Enter 'quit' to exit the program");
        GameBoard gameBoard = new GameBoardImpl();
        gameBoard.run();
    }
}
