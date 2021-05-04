import java.util.Objects;

public class MovementRoutine
{
    public MovementRoutine (String command, int numberOfCommands)
    {
        _command = command;
        _numberOfCommands = numberOfCommands;

        if ((command == null) || (command.equals("")))
        {
            System.out.println("OOPS");
            System.exit(0);
        }
    }

    public String getCommand ()
    {
        return _command;
    }

    public int getLength ()
    {
        return ((_command == null) ? 0 : _command.length());
    }
    
    public int numberOfCommands ()
    {
        return _numberOfCommands;
    }

    @Override
    public String toString ()
    {
        return _command;
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_command, _numberOfCommands);
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            MovementRoutine temp = (MovementRoutine) obj;

            return (_command.equals(temp._command));
        }

        return false;
    }

    private String _command;
    private int _numberOfCommands;
}