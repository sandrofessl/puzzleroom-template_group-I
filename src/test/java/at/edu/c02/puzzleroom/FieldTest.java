package at.edu.c02.puzzleroom;

import at.edu.c02.puzzleroom.commands.CommandLoad;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FieldTest {
    @Test
    public void startField() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        // We're using CommandLoad here - therefore it's not a full unit test, but allows us to test things easier
        // without having to duplicate the loading logic.
        // You will often find these constructs in "real life" applications (especially if tests were added later), when it's hard
        // to isolate everything.
        new CommandLoad(new String[]{"src/test/resources/startField.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();

        // Player should start in 2nd row, 1st column
        assertEquals(2, player.getRow());
        assertEquals(1, player.getCol());
    }

    @Test
    public void pathField() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        // Finish is reached when moving twice to the right
        new CommandLoad(new String[]{"src/test/resources/simple.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();

        // Player should start at 0 steps
        assertEquals(0, player.getStepCount());

        // Moving right should be possible, since there is a path field
        boolean success = player.moveRight();
        assertTrue(success);

        // Player should now be at 1 step
        assertEquals(1, player.getStepCount());
    }

    @Test
    public void wallField() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        // Finish is reached when moving twice to the right
        new CommandLoad(new String[]{"src/test/resources/simple.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();

        // Player should start at 0 steps
        assertEquals(0, player.getStepCount());

        // Moving left should not be possible, since there is a wall field
        boolean success = player.moveLeft();
        assertFalse(success);

        // Player should still be at 0 steps
        assertEquals(0, player.getStepCount());
    }

    @Test
    public void finishField() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        // Finish is reached when moving twice to the right
        new CommandLoad(new String[]{"src/test/resources/simple.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();

        // Game should not be finished after loading
        assertFalse(gameBoard.isFinished());
        player.moveRight();
        // Game should still not be finished after moving right once
        assertFalse(gameBoard.isFinished());
        player.moveRight();
        // Game should be finished after moving right twice
        assertTrue(gameBoard.isFinished());
    }

    @Test

    // bin mir nicht sicher, ob die beiden Tests korrekt sind? da wird nur gefragt,
    // ob er rechts geht, wenn man rechts auswählt.
    // bei der einbahn darf er ja nur auf einem bestimmten feld nur in eine richtung gehn.
    // also ich denke, man müsste ihn zuerst auf ein dreiecksfeld stellen und dann prüfen
    public void oneWayFieldMoveNotPossible() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{"src/test/resources/testoneway.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();

        player.moveRight();
        assertFalse(player.moveRight());

    }

    @Test
    public void oneWayFieldMovePossible() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{"src/test/resources/testoneway.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();

        player.moveRight();
        assertTrue(player.moveUp());
    }
    @Test
    public void iceFieldSlideToTargetRight() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{"src/test/resources/testiceRight.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();
        player.moveRight();
        assertTrue(player.moveRight());
    }
    @Test
    public void iceFieldSlideToTargetDownAndRight() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{"src/test/resources/testiceDownAndRight.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();
        player.moveDown();
        assertTrue(player.moveRight());
    }
}
