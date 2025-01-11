package at.edu.c02.puzzleroom;

import at.edu.c02.puzzleroom.commands.Command;
import at.edu.c02.puzzleroom.commands.CommandFactory;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomException;
import at.edu.c02.puzzleroom.fields.Field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class GameBoardImpl implements GameBoard {
    private Player player;
    private boolean quit = false;
    private boolean finished = false;
    private List<List<Field>> fields;

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandFactory commandFactory = new CommandFactory();
        while(!quit)
        {
            System.out.print("> ");
            // Read input and split it by " "
            String input = reader.readLine().toLowerCase();
            String[] inputParts = input.split(" ");
            // The first word is the command
            String commandString = inputParts[0];
            // All other words are the arguments
            String[] commandArgumentStrings = Arrays.copyOfRange(inputParts, 1, inputParts.length);
            // Try to create a command based on the string and try to execute it
            try {
                Command command = commandFactory.createCommand(commandString, commandArgumentStrings);
                command.execute(this);
            } catch (PuzzleRoomException e) {
                // If we encounter an expected error, simply print it and continue
                System.out.println(e.getMessage());
            }
        }
    }

    public void quit() {
        // Do not change the output here
        System.out.println("Goodbye!");

        // End the program by setting `quit` to true which stop's the endless loop
        // from the `run` function
        quit = true;
    }

    public void setFinished() {
        // Do not change the output here
        System.out.println("You finished the maze in " + player.getStepCount() + " steps.");
        // Set finished to true so that the player cannot move anymore
        this.finished = true;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void initialize(List<List<Field>> fields) throws PuzzleRoomException {
        this.player = new PlayerImpl(this);
        this.finished = false;
        this.fields = fields;
        // Initialize all fields after we've set the player and all fields.
        // During initialization, fields can read/access all other fields
        // and the player.

        // Iterate through all field rows...
        for(List<Field> fieldRow : fields)
        {
            // ...and then, go through each field in this row
            for(Field field : fieldRow)
            {
                field.initialize();
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Field getField(int row, int column) {
        return fields.get(row).get(column);
    }

    public List<List<Field>> getFields() {
        return this.fields;
    }
}
