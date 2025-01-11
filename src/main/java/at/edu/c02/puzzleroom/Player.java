package at.edu.c02.puzzleroom;

/**
 * The player is responsible for movement and keeping track of the number of steps taken.
 */
public interface Player {
    /**
     * Sets the player's current position
     *
     * @param row Player position: row
     * @param col Player position: column
     */
    void setPosition(int row, int col);

    /**
     * Moves the player up one field
     *
     * @return True if the movement was successful, false otherwise
     */
    boolean moveUp();

    /**
     * Moves the player down one field
     *
     * @return True if the movement was successful, false otherwise
     */
    boolean moveDown();

    /**
     * Moves the player left one field
     *
     * @return True if the movement was successful, false otherwise
     */
    boolean moveLeft();

    /**
     * Moves the player right one field
     *
     * @return True if the movement was successful, false otherwise
     */
    boolean moveRight();

    /**
     * Increase the player's step counter by 1.
     */
    void walkStep();

    /**
     * Increase the player's step counter by the given amount.
     * amount may be negative as well.
     *
     * @param amount The amount of steps to add
     */
    void walkSteps(int amount);

    /**
     * Returns the current step counter value. Used by the GameBoard to display
     * the "you won" message
     *
     * @return The amount of steps taken
     */
    int getStepCount();

    /**
     *
     * @return Player position: row
     */
    int getRow();

    /**
     *
     * @return Player position: column
     */
    int getCol();
}
