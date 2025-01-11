package at.edu.c02.puzzleroom.fields;

import at.edu.c02.puzzleroom.GameBoard;

/**
 * This base class for a field implements some members/functions that are relevant
 * for all fields.
 * You can access all `protected` and `public` variables/functions in your fields.
 */
public abstract class BaseField implements Field {
    /**
     * Access the GameBoard, e.g. to call gameBoard.getField(...) to access different fields
     */
    protected final GameBoard gameBoard;
    /**
     * The field's current name that will be printed by the `show` command.
     * If the field name changes, then change this variable.
     */
    protected char name;
    /**
     * The field's original name, when it was loaded.
     * This will stay the same, even if the name of the field changes.
     */
    protected final char originalName;
    /**
     * The field's position: row
     */
    protected final int row;
    /**
     * The field's position: column
     */
    protected final int col;

    protected BaseField(GameBoard gameBoard, char name, int row, int col) {
        this.gameBoard = gameBoard;
        this.name = name;
        this.originalName = name;
        this.row = row;
        this.col = col;
    }

    /**
     * Sets the player's position to this field's position.
     * Usually happens when the player enters this field, but some fields may cause
     * the player to move somewhere else.
     */
    protected void setPlayerPositionToField() {
        gameBoard.getPlayer().setPosition(row, col);
    }

    /**
     * Returns the field's name, used by the `show` command
     * @return The field's current name
     */
    public char getName() {
        return name;
    }
}
