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
        _stick = new Joystick();
    }

    public final boolean playGame ()
    {
        /*
         * Initialise the screen. Run the game instructions
         * once.
         */

        int[] output = getOutput(INITIAL_INPUT);

        if (_debug)
            System.out.println("Tile information: <"+output[0]+", "+output[1]+"> and "+TileId.idToString(output[2]));

        Tile theTile = new Tile(new Coordinate(output[0], output[1]), output[2]);

        _theScreen.updateTile(theTile);

        if(theTile.getId() == TileId.BALL)
            _ballPosition = theTile.getPosition();
        else
        {
            if (theTile.getId() == TileId.PADDLE)
                _paddlePosition = theTile.getPosition();
        }

        /*
         * Where is the joystick?
         */

        if (_ballPosition.getX() > _paddlePosition.getX())
            _stick.setPosition(Joystick.TILTED_RIGHT);
        else
        {
            if (_ballPosition.getX() < _paddlePosition.getX())
                _stick.setPosition(Joystick.TILTED_LEFT);
        }

        return true;
    }

    public final int getNumberOfBlocks ()
    {
        return _theScreen.numberOfBlocks();
    }

    private final int[] getOutput (int input)
    {
        int[] values = new int[3];

        while (!_computer.hasPaused() && !_computer.hasOutput())
        {
            _computer.singleStepExecution(input);
        }

        if (!_computer.hasPaused())
        {
            values[0] = Integer.parseInt(_computer.getOutput());

            while (!_computer.hasPaused() && !_computer.hasOutput())
            {
                _computer.singleStepExecution(input);
            }

            if (!_computer.hasPaused())
            {
                values[1] = Integer.parseInt(_computer.getOutput());

                while (!_computer.hasPaused() && !_computer.hasOutput())
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
    private Joystick _stick;

    private static final int INITIAL_INPUT = 0; // nothing specified in the overview
}