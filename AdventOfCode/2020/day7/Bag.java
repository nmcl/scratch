import java.util.*;

public class Bag
{
    public Bag (String type)
    {
        _type = type;
    }

    @Override
    public String toString ()
    {
        return "Bag colour: "+_type;
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_type);
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
            Bag temp = (Bag) obj;

            return tenp._type.equals(_tyoe);
        }

        return false;
    }


    private String _type;
}