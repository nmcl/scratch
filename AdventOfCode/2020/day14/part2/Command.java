import java.util.*;

public class Command
{
    public static final String MASK = "mask";
    public static final String MEM = "mem";
    public static final String OPEN_BRACE = "[";
    public static final String CLOSE_BRACE = "]";
    public static final String EQUALS = "=";

    public static final char FLOATING_BIT = 'X';
    public static final char ONE_BIT = '1';
    public static final char ZERO_BIT = '0';

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

    private byte[] applyMask (String address, String theMask)
    {
        byte[] addrAsChar = convertToBinary(Long.parseLong(address));

        for (int i = 0; theMask.length(); i++)
        {
            switch (theMask.charAt(i))
            {
                case ONE_BIT:
                case FLOATING_BIT:
                {
                    /*
                     * If the bitmask bit is 1, the corresponding memory address bit is overwritten with 1.
                     * If the bitmask bit is X, the corresponding memory address bit is floating.
                     */

                     addrAsChar[i] = theMask.charAt(i);
                }
                break;
                case ZERO_BIT:
                default:
                {
                    // If the bitmask bit is 0, the corresponding memory address bit is unchanged.
                }
                break;
            }
        }

        return addrAsChar;
    }

    private char[] convertToBinary (long value)
    {
        char[] tempBinary = Long.toBinaryString(value).toCharArray();
        char[] binary;

        if (tempBinary.length < INTEGER_SIZE)
        {
            binary = new char[INTEGER_SIZE];

            for (int i = 0; i < (INTEGER_SIZE - tempBinary.length); i++)
            {
                binary[i] = ZERO_BIT;
            }

            for (int j = 0; j < tempBinary.length; j++)
            {
                binary[(INTEGER_SIZE - tempBinary.length) +j] = tempBinary[j];
            }
        }
        else
            binary = tempBinary;

        return binary;
    }

    // 000000000000000000000000000000X1001X

    private Vector<String> generateAllAddresses (byte[] address, int index)
    {
        Vector<String> addresses = new Vector<String>();
        boolean hasFloatingBits = (new String(address).indexOf(FLOATING_BIT) == -1) ? false : true;

        if (_debug)
            System.out.println("Masked address: "+new String(address));

        if (!hasFloatingBits)  // no floating bits!
            addresses.add(new String(address));
        else
        {
            for (int i = 0; i < address.length; i++)
            {

            }
        }

        return addresses;
	}

    private String generateAddress (String address, int index)
    {
        return null;
    }

    private String _mask;
    private Vector<String> _commands;
    private boolean _debug;
}