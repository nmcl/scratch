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
                System.out.println("**checking x");
                _computer.singleStepExecution(INITIAL_INPUT);
            }

            x = Integer.parseInt(_computer.getOutput());

            if (!_computer.hasHalted())
            {
                while (!_computer.hasHalted() && !_computer.hasOutput())
                {
                    System.out.println("**checking y");
                    _computer.singleStepExecution(INITIAL_INPUT);
                }

                y = Integer.parseInt(_computer.getOutput());

                if (!_computer.hasHalted())
                {
                    while (!_computer.hasHalted() && !_computer.hasOutput())
                    {
                        System.out.println("**checking id");
                        _computer.singleStepExecution(INITIAL_INPUT);
                    }

                    id = Integer.parseInt(_computer.getOutput());
                }
                else
                {
                    System.out.println("Error - computer halted after y!");

                    return false;
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