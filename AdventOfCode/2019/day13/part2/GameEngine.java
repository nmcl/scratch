import java.util.function.IntConsumer;

import java.util.*;

public class GameEngine
{
    public GameEngine (Vector<String> instructions, boolean debug)
    {
        _computer = new Intcode(instructions, INITIAL_INPUT, debug);
        _debug = debug;
        _theScreen = new Screen(_debug);
        _paddlePosition = null;
        _ballPosition = null;
    }

    public final boolean playGame ()
    {
        int x = 0;
        int y = 0;
        int id = TileId.EMPTY;

        /*
         * Initialise the screen.
         */

        while (!_computer.hasHalted())
        {
            while (!_computer.hasHalted() && !_computer.hasOutput())
            {
                _computer.singleStepExecution(INITIAL_INPUT);
            }

            if (!_computer.hasHalted())
            {
                x = Integer.parseInt(_computer.getOutput());

                while (!_computer.hasHalted() && !_computer.hasOutput())
                {
                    _computer.singleStepExecution(INITIAL_INPUT);
                }

                if (!_computer.hasHalted())
                {
                    y = Integer.parseInt(_computer.getOutput());

                    while (!_computer.hasHalted() && !_computer.hasOutput())
                    {
                        _computer.singleStepExecution(INITIAL_INPUT);
                    }

                    id = Integer.parseInt(_computer.getOutput());
                }
                else
                {
                    System.out.println("Error - computer halted after outputing x value!");

                    return false;
                }

                if (_debug)
                    System.out.println("Tile information: <"+x+", "+y+"> and "+TileId.idToString(id));

                Tile theTile = new Tile(new Coordinate(x, y), id));

                _theScreen.updateTile(theTile);

                if(theTile.getId() == TileId.BALL)
                    _ballPosition = theTile.getPosition();
                else
                {
                    if (theTile.getId() == TileId.PADDLE)
                        _paddlePosition = theTile.getPosition();
                }
            }
            else
            {
                if (_debug)
                    System.out.println("Computer halted.");

                return true;
            }
        }

        return true;
    }

    public final int getNumberOfBlocks ()
    {
        return _theScreen.numberOfBlocks();
    }

    private final int[] getInformation (int input)
    {
        int[] values = new int[3];

        while (!_computer.hasHalted() && !_computer.hasOutput())
        {
            _computer.singleStepExecution(input);
        }

        if (!_computer.hasHalted())
        {
            values[0] = Integer.parseInt(_computer.getOutput());

            while (!_computer.hasHalted() && !_computer.hasOutput())
            {
                _computer.singleStepExecution(input);
            }

            if (!_computer.hasHalted())
            {
                values[1] = Integer.parseInt(_computer.getOutput());

                while (!_computer.hasHalted() && !_computer.hasOutput())
                {
                    _computer.singleStepExecution(input);
                }

                values[2] = Integer.parseInt(_computer.getOutput());
            }
            else
            {
                System.out.println("Error - computer halted after outputing x value!");
            }
        }

        retrn values;
    }

    private Intcode _computer;
    private boolean _debug;
    private Screen _theScreen;
    private Coordinate _paddlePosition;
    private Coordinate _ballPosition;

    private static final int INITIAL_INPUT = 0; // nothing specified in the overview
}