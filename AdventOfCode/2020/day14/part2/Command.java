import java.util.*;

public class Command
{
    public static final String MASK = "mask";
    public static final String MEM = "mem";
    public static final String OPEN_BRACE = "[";
    public static final String CLOSE_BRACE = "]";
    public static final String EQUALS = "=";

    public static final char FLOATING_BIT = 'X';

    public static final int INTEGER_SIZE = 36;

    public Command (String mask, Vector<String> cmds, boolean debug)
    {
        _mask = mask;
        _commands = cmds;
        _debug = debug;
    }

    public void execute (Memory mem)
    {
        String theMask = _mask.substring(_mask.indexOf(EQUALS) +1).trim();

        if (_debug)
            System.out.println("Bitmask is: "+theMask);

        for (int i = 0; i < _commands.size(); i++)
        {
            int startIndex = _commands.elementAt(i).indexOf(OPEN_BRACE);
            int endIndex = _commands.elementAt(i).indexOf(CLOSE_BRACE);
            String addrStr = _commands.elementAt(i).substring(startIndex +1, endIndex);
            Long addr = Long.parseLong(addrStr);

            if (_debug)
                System.out.println("Memory address: "+addr);

            startIndex = _commands.elementAt(i).indexOf(EQUALS);

            String valueStr = _commands.elementAt(i).substring(startIndex +1).trim();
            Long value = Long.parseLong(valueStr);

            if (_debug)
                System.out.println("Value: "+value);

            Vector<String> addresses = applyMask(theMask, addr);
        }
    }

    @Override
    public String toString ()
    {
        String str = _mask;

        for (int i = 0; i < _commands.size(); i++)
        {
            str += "\n" + _commands.elementAt(i);
        }

        return str;
    }

    private Vector<String> applyMask (String mask, long address)
    {
        char[] theMaskArray = mask.toCharArray();
        Vector<String> masks = new Vector<String>();
        int count = 0;

        for (int i = 0; i < theMaskArray.length; i++)
        {
            if (theMaskArray[i] == FLOATING_BIT)
                count++;
        }

        if (count > 0)
        {
            int[] options = new int[count];
        }
        else
            masks.add(mask);


        return masks;
    }

    private String _mask;
    private Vector<String> _commands;
    private boolean _debug;
}