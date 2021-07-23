import java.util.*;

public class Grid
{
    public Grid (Vector<String> instructions, boolean debug)
    {
        _input = instructions;
        _debug = debub;
    }

    private Coordinate leftCoordinate (int y, int x)
    {
        Intcode computer = new Intcode(_input, _debug);

        computer.setInputs(""+x, ""+y);
        computer.executeProgram();

        Vector<String> outputs = computer.getOutputs();
        Enumeration<String> iter = outputs.elements();

        while (iter.hasMoreElements())
        {
            String value = iter.nextElement();

            if (DroneStatus.STATIONARY.equals(value))
            {
                x++;

                computer = new Intcode(_input, _debug);

                computer.setInputs(""+x, ""+y);
                computer.executeProgram();
            }
        }

        return new Coordinate(x, y);
    }

    private Vector<String> _input;
    private boolean _debug;
}