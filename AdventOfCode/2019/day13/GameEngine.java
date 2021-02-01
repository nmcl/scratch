import java.util.function.IntConsumer;

import java.util.*;

public class GameEngine
{
    public GameEngine (Vector<String> instructions, boolean debug)
    {
        _computer = new Intcode(instructions, INITIAL_INPUT, debug);
        _debug = debug;
    }

    public final boolean playGame ()
    {
        int x = 0;
        int y = 0;
        int id = TileId.EMPTY;

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

                System.out.println("**got "+x+" "+y+" "+TileId.idToString(id));
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

    private Intcode _computer;
    private boolean _debug;
    private Screen _theScreen;

    private static final int INITIAL_INPUT = 0; // nothing specified in the overview
}