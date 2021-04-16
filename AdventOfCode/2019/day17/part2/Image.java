import java.util.*;

/*
 * TODO change this to use Cell instead of String. Then the
 * VacuumRobot wouldn't need to do the conversion.
 */

 /**
  * The camera's scanned image. Can then be used
  * to make a Map.
  */

public class Image
{
    public Image (boolean debug)
    {
        _image = new Vector<String>();
        _numberOfLines = 0;
        _debug = debug;
    }

    public void addData (String value)
    {
        if ((CellId.SCAFFOLDING_CODE.equals(value)) || (CellId.OPEN_SPACE_CODE.equals(value)) || (CellId.NEW_LINE_CODE.equals(value)))
        {
            _image.add(value);

            if (CellId.NEW_LINE_CODE.equals(value))
                _numberOfLines++;
        }
        else
        {
            if (CellId.isRobotCode(value))
                _image.add(value);
            else
            {
                if (_debug)
                    System.out.println("Invalid code: "+value);
            }
        }
    }

    public final String[] scannedLines ()
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
                case CellId.SCAFFOLDING_CODE:
                {
                    str += CellId.SCAFFOLDING;
                }
                break;
                case CellId.OPEN_SPACE_CODE:
                {
                    str += CellId.OPEN_SPACE;
                }
                break;
                case CellId.NEW_LINE_CODE:
                {
                    endOfLine = true;
                }
                break;
                default:
                    str += CellId.printRobotCode(val);
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

    public final Vector<String> getImage ()
    {
        return _image;
    }

    public final int numberOfLines ()
    {
        return _numberOfLines;
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
                case CellId.SCAFFOLDING_CODE:
                {
                    str += CellId.SCAFFOLDING;
                }
                break;
                case CellId.OPEN_SPACE_CODE:
                {
                    str += CellId.OPEN_SPACE;
                }
                break;
                case CellId.NEW_LINE_CODE:
                {
                    str += "\n";
                }
                break;
                default:
                    str += CellId.printRobotCode(val);
            }
        }

        return str;
    }

    private Vector<String> _image;
    private int _numberOfLines;
    private boolean _debug;
}