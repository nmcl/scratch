import java.util.*;

public class Grid
{
    public static final int GRID_FUDGE = 2000;

    public Grid (Vector<String> instructions, boolean debug)
    {
        _input = instructions;
        _theGrid = new Vector<CoordinatePair>();
        _debug = debug;

        findSquare();
    }

    public final Vector<CoordinatePair> getBeamGrid ()
    {
        return _theGrid;
    }

    private final void findSquare ()
    {
        int startY = 100;  // far enough in
        int startX = 0;

        Coordinate leftCoord = leftCoordinate(startY, startX);
        Coordinate rightCoord = rightCoordinate(leftCoord.getY(), leftCoord.getX());

        for (int i = startY + 1; i < GRID_FUDGE; i++)
        {
            leftCoord = leftCoordinate(i, leftCoord.getX());
            rightCoord = rightCoordinate(i, rightCoord.getX());

            _theGrid.add(new CoordinatePair(leftCoord, rightCoord));
        }
    }

    private Coordinate leftCoordinate (int y, int x)
    {
        Intcode computer = new Intcode(_input, _debug);

        computer.setInputs(""+x, ""+y);
        computer.executeProgram();

        Vector<String> outputs = computer.getOutputs();
        Enumeration<String> iter = outputs.elements();
        boolean beingPulled = false;

        while (iter.hasMoreElements() && !beingPulled)
        {
            String value = iter.nextElement();

            if (DroneStatus.BEING_PULLED.equals(value))
                beingPulled = true;
        }

        if (!beingPulled)  // keep going until we start being pulled
            return leftCoordinate(y, x +1);
        else
            return new Coordinate(x, y);
    }

    private Coordinate rightCoordinate (int y, int x)
    {
        Intcode computer = new Intcode(_input, _debug);

        computer.setInputs(""+x, ""+y);
        computer.executeProgram();

        Vector<String> outputs = computer.getOutputs();
        Enumeration<String> iter = outputs.elements();
        boolean beingPulled = true;

        while (iter.hasMoreElements() && beingPulled)
        {
            String value = iter.nextElement();

            if (DroneStatus.STATIONARY.equals(value))
                beingPulled = false;
        }

        if (beingPulled)  // keep going until we stop being pulled
            return rightCoordinate(y, x +1);
        else
            return new Coordinate(x -1, y);
    }

    private Vector<String> _input;
    private Vector<CoordinatePair> _theGrid;
    private boolean _debug;
}