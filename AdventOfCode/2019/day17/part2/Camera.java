import java.util.*;

public class Camera
{
    private static final String INITIAL_INPUT = "";  // no input

    public Camera (Vector<String> values, boolean debug)
    {
        _theComputer = new Intcode(values, INITIAL_INPUT, debug);
        _theImage = new Image(debug);
        _debug = debug;
    }

    public final Image takePicture ()
    {
        while (!_theComputer.hasHalted())
        {
            _theComputer.singleStepExecution();

            if (_theComputer.hasOutput())
            {
                String output = _theComputer.getOutput();

                _theImage.addData(output);
            }
        }

        return _theImage;
    }

    public Vector<Integer> getAlignmentParameters ()
    {
        Vector<Integer> params = new Vector<Integer>();
        Vector<Coordinate> intersections = scanForIntersections();
        Enumeration<Coordinate> ptr = intersections.elements();

        while (ptr.hasMoreElements())
        {
            Coordinate coord = ptr.nextElement();
            int alignmentParam = coord.getX() * coord.getY();

            if (_debug)
                System.out.println("Alignment Parameter: "+alignmentParam+" from "+coord);

            params.add(alignmentParam);
        }

        return params;
    }

    public Vector<Coordinate> scanForIntersections ()
    {
        String[] lines = _theImage.scannedLines();
        int lineLength = lines[0].length(); // all lines are the same length
        Vector<Coordinate> intersections = new Vector<Coordinate>();

        if (_debug)
        {
            System.out.println("Number of lines: "+lines.length);
            System.out.println("Line length: "+lineLength);
        }
        
        for (int i = 0; i < lines.length -1; i++)
        {
            if (_debug)
                System.out.println("Line "+i+" is "+lines[i]);

            for (int j = 0; j < lineLength; j++)
            {
                if (lines[i].charAt(j) == CellId.SCAFFOLDING_CHAR)
                {
                    int lineAbove = i-1;
                    int lineBelow = i+1;
                    int left = j-1;
                    int right = j+1;

                    if ((lineAbove >= 0) && (lineBelow < lines.length -1) && (left >= 0) && (right < lineLength))
                    {
                        if (lines[lineAbove].charAt(j) == CellId.SCAFFOLDING_CHAR)
                        {
                            if (lines[lineBelow].charAt(j) == CellId.SCAFFOLDING_CHAR)
                            {
                                if (lines[i].charAt(left) == CellId.SCAFFOLDING_CHAR)
                                {
                                    if (lines[i].charAt(right) == CellId.SCAFFOLDING_CHAR)
                                    {
                                        String str = lines[i];

                                        lines[i] = str.substring(0, j) + CellId.INTERSECTION_CHAR + str.substring(j+1);

                                        intersections.add(new Coordinate(j, i));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < lines.length; i++)
            System.out.println(lines[i]);

        return intersections;
    }

    @Override
    public String toString ()
    {
        return _theImage.toString();
    }

    private Intcode _theComputer;
    private Image _theImage;
    private boolean _debug;
}