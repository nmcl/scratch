/*
 * If we run across a door we can't open because we don't
 * yet have the key, throw this exception.
 */

public class DoorLockedException extends Exception
{
    public DoorLockedException ()
    {
    }

    public DoorLockedException (String message, char door)
    {
        super(message);

        _door = door;
    }

    public char keyNeeded ()
    {
        return (char) (_door + CellId.DOOR_CODE);
    }

    private char _door;
}