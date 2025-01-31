package at.edu.c02.puzzleroom;

import at.edu.c02.puzzleroom.commands.CommandFastmove;
import at.edu.c02.puzzleroom.commands.CommandLoad;
import at.edu.c02.puzzleroom.commands.CommandMove;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomInvalidArgumentsException;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomInvalidMoveException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandTest {
    @Test
    public void movePositiveTest() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{"src/test/resources/simple.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();

        new CommandMove(new String[]{"right"}).execute(gameBoard);

        // Player should now be at 1 step and at column 2 (because they moved right once)
        assertEquals(1, player.getStepCount());
        assertEquals(1, player.getRow());
        assertEquals(2, player.getCol());
    }

    @Test(expected = PuzzleRoomInvalidMoveException.class)
    public void movePositiveTestNegative() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{"src/test/resources/simple.maze"}).execute(gameBoard);

        // Moving left should throw an InvalidMoveException
        new CommandMove(new String[]{"left"}).execute(gameBoard);
    }

    @Test(expected = PuzzleRoomInvalidArgumentsException.class)
    public void movePositiveTestNegative2() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{"src/test/resources/simple.maze"}).execute(gameBoard);

        // This should throw a PuzzleRoomInvalidArgumentsException
        new CommandMove(new String[]{"invalid"}).execute(gameBoard);
    }

    @Test
    public void fastMovePositiveTest() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{"src/test/resources/test.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();


        new CommandFastmove(new String[]{"d","r"}).execute(gameBoard);

        // Player should now be at 2 steps and at column 2 and row 2
        assertEquals(2, player.getStepCount());
        assertEquals(2, player.getRow());
        assertEquals(2, player.getCol());
    }

    @Test(expected = PuzzleRoomInvalidArgumentsException.class)
    public void fastMoveNegativeTest1() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{"src/test/resources/test.maze"}).execute(gameBoard);

        // This should throw a PuzzleRoomInvalidArgumentsException
        new CommandMove(new String[]{"d d d d"}).execute(gameBoard);
    }

    @Test(expected = PuzzleRoomInvalidArgumentsException.class)
    public void fastMoveNegativeTest2() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{"src/test/resources/test.maze"}).execute(gameBoard);

        // This should throw a PuzzleRoomInvalidArgumentsException
        new CommandMove(new String[]{"d m d d"}).execute(gameBoard);
    }

}
