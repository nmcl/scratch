import java.util.*;

public class Command
{
    public static final String MASK = "mask";
    public static final String MEM = "mem";

    public Command (String mask, Vector<String> cmds)
    {
        _mask = maskl
        _commands = cmds;
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

    private String _mask;
    private Vector<String> _commands;
}