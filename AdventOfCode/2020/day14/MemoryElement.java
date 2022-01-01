import java.util.Objects;

public class MemoryElement
{
    public MemoryElement (long address, long contents)
    {
        _address = address;
        _contents = contents;
    }

    @Override
    public String toString ()
    {
        return "MemoryElement < "+_address+", "+_contents+" >";
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_address, _co\);
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
            MemoryElement temp = (MemoryElement) obj;

            return ((_address == temp._address) && (_contents == temp._contents));
        }

        return false;
    }

    private long _address;
    private long _contents;
}