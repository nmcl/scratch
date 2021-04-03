import java.util.*;

public class Scaffold
{
    public static final String SCAFFOLDING = "#";
    public static final String OPEN_SPACE = ".";

    public static final char SCAFFOLDING_CHAR = '#';

    // ASCII codes.

    public static final String SCAFFOLDING_CODE = "35";
    public static final String OPEN_SPACE_CODE = "46";
    public static final String NEW_LINE_CODE = "10";

    public Scaffold (boolean debug)
    {
        _image = new Vector<String>();
        _numberOfLines = 0;
        _debug = debug;
    }

    public void addData (String value)
    {
        if ((SCAFFOLDING_CODE.equals(value)) || (OPEN_SPACE_CODE.equals(value)) || (NEW_LINE_CODE.equals(value)))
        {
            _image.add(value);

            if (NEW_LINE_CODE.equals(value))
                _numberOfLines++;
        }
        else
        {
            if (VacuumRobot.isRobotCode(value))
                _image.add(value);
            else
            {
                if (_debug)
                    System.out.println("Invalid code: "+value);
            }
        }
    }

    public void scanForIntersections ()
    {
        String[] lines = scannedLines();
        int lineLength = lines[0].length();

        System.out.println("number of lines: "+lines.length);
        System.out.println("and: "+lineLength);
        
        for (int i = 0; i < lines.length; i++)
        {
            for (int j = 0; j < lineLength; j++)
            {
                int lineAbove = i-1;
                int lineBelow = i+1;
                int left = j-1;
                int right = j+1;

                System.out.println("checking "+lineAbove+" "+lineBelow+" "+left+" "+right);

                if ((lineAbove >= 0) && (lineBelow < lines.length) && (left >= 0) && (right < lineLength))
                {
                    if (lines[lineAbove].charAt(j) == SCAFFOLDING_CHAR)
                    {
                        if (lines[lineBelow].charAt(j) == SCAFFOLDING_CHAR)
                        {
                            if (lines[i].charAt(left) == SCAFFOLDING_CHAR)
                            {
                                if (lines[i].charAt(right) == SCAFFOLDING_CHAR)
                                    System.out.println("Intersection");
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString ()
    {
        Enumeration<String> iter = _image.elements();
        String str = "";

        while (iter.hasMoreElements())
        {
            String val = iter.nextElement();

            switch (val)
            {
                case SCAFFOLDING_CODE:
                {
                    str += SCAFFOLDING;
                }
                break;
                case OPEN_SPACE_CODE:
                {
                    str += OPEN_SPACE;
                }
                break;
                case NEW_LINE_CODE:
                {
                    str += "\n";
                }
                break;
                default:
                    str += VacuumRobot.print(val);
            }
        }

        return str;
    }

    private String[] scannedLines ()
    {
        String[] lines = new String[_numberOfLines];
        Enumeration<String> iter = _image.elements();
        int lineNumber = 0;
        String str = "";

        while (iter.hasMoreElements())
        {
            String val = iter.nextElement();
            boolean endOfLine = false;

            switch (val)
            {
                case SCAFFOLDING_CODE:
                {
                    str += SCAFFOLDING;
                }
                break;
                case OPEN_SPACE_CODE:
                {
                    str += OPEN_SPACE;
                }
                break;
                case NEW_LINE_CODE:
                {
                    endOfLine = true;
                }
                break;
                default:
                    str += VacuumRobot.print(val);
            }

            if (endOfLine)
            {
                lines[lineNumber] = str;
                lineNumber++;

                if (_debug)
                    System.out.println("Line: "+str);

                str = "";
            }
        }

        return lines;
    }

    private final int numberOfLineElements ()
    {
        int sizeOfLines = 0;
        Enumeration<String> iter = _image.elements();
        boolean done = false;

        while (iter.hasMoreElements() && !done)
        {
            sizeOfLines++;

            String entry = iter.nextElement();

            if (NEW_LINE_CODE.equals(entry))
                done = true;
        }

        return sizeOfLines;
    }

    private Vector<String> _image;
    private int _numberOfLines;
    private boolean _debug;
}