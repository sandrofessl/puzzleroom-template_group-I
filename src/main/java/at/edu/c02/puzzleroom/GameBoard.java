package at.edu.c02.puzzleroom;

import at.edu.c02.puzzleroom.exceptions.PuzzleRoomException;
import at.edu.c02.puzzleroom.fields.Field;

import java.io.IOException;
import java.util.List;

/**
 * The GameBoard represents a game and has references to the player and the fields.
 */
public interface GameBoard {
    /**
     * Run the game, reading player input and executing commands
     * @throws IOException If the player input cannot be read
     */
    void run() throws IOException;

    /**
     * Quit the program
     */
    void quit();

    /**
     * Set the game state to "finished", called when the game is won.
     */
    void setFinished();

    /**
     * Initialize a new puzzle room with the given fields
     * @param fields All fields representing the puzzle room
     */
    void initialize(List<List<Field>> fields) throws PuzzleRoomException;

    /**
     *
     * @return The currently active player
     */
    Player getPlayer();

    /**
     * Get the field on the given position
     * @param row Field position: row
     * @param column Field position: column
     * @return The field
     */
    Field getField(int row, int column);

    /**
     * Returns all fields, used by the show command
     * @return All fields
     */
    List<List<Field>> getFields();

    /**
     * Returns whether the game is finished (won)
     * @return True if the finish was reached, false otherwise
     */
    boolean isFinished();
}
