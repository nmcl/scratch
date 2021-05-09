import java.util.Objects;

public class MovementFunction
{
    public MovementFunction (String command, int numberOfCommands)
    {
        _command = command;
        _numberOfCommands = numberOfCommands;
        _name = "";
    }

    public String getCommand ()
    {
        return _command;
    }

    public String getName ()
    {
        return _name;
    }

    public void setName (String name)
    {
        _name = name;
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
        return "MovementFunction "+_name+": "+_command+" and size: "+getLength();
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
            MovementFunction temp = (MovementFunction) obj;

            return (_command.equals(temp._command));
        }

        return false;
    }

    private String _command;
    private int _numberOfCommands;
    private String _name;
}