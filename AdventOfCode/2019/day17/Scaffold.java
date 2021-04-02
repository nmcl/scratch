import java.util.*;

public class Scaffold
{
    public static final String SCAFFOLDING = "#";
    public static final String OPEN_SPACE = ".";

    // ASCII codes.

    public static final String SCAFFOLDING_CODE = "35";
    public static final String OPEN_SPACE_CODE = "46";
    public static final String NEW_LINE_CODE = "10";

    public Scaffold (boolean debug)
    {
        _image = new Vector<String>();
        _debug = debug;
    }

    public void addData (String value)
    {
        if ((SCAFFOLDING_CODE.equals(value)) || (OPEN_SPACE_CODE.equals(value)) || (NEW_LINE_CODE.equals(value)))
            _image.add(value);
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

    private Vector<String> _image;
    private boolean _debug;
}