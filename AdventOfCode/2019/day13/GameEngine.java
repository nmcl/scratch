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
            _computer.singleStepExecution(INITIAL_INPUT);

            if (_computer.hasOutput())
            {
                x = Integer.parseInt(_computer.getOutput());
            }
            else
            {
                System.out.println("Error - no x value given!");

                return false;
            }

            if (!_computer.hasHalted())
            {
                _computer.singleStepExecution(INITIAL_INPUT);

                if (_computer.hasOutput())
                {
                    y = Integer.parseInt(_computer.getOutput());
                }
                else
                {
                    System.out.println("Error - no y value given!");

                    return false;
                }

                if (!_computer.hasHalted())
                {
                    _computer.singleStepExecution(INITIAL_INPUT);

                    if (_computer.hasOutput())
                    {
                        id = Integer.parseInt(_computer.getOutput());
                    }
                    else
                    {
                        System.out.println("Error - no id value given!");

                        return false;
                    }
                }

                System.out.println("**got "+x+" "+y+" "+TileId.idToString(id));
            }
            else
            {
                System.out.println("Error - computer halted after x!");

                return false;
            }
        }

        return true;
    }

    private Intcode _computer;
    private boolean _debug;

    private static final int INITIAL_INPUT = 0; // nothing specified in the overview
}