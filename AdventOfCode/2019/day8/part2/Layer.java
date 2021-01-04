import java.util.Objects;

public class Layer
{
    public Layer (int width, int height, char[] data)
    {
        _width = width;
        _height = height;
        _theData = data;
    }

    public int numberOfPixels (int value)
    {
        int number = 0;

        for (int i = 0; i < _theData.length; i++)
        {
            if (Character.getNumericValue(_theData[i]) == value)
                number++;
        }

        return number;
    }

    public Layer merge (Layer layerBelow)
    {
        if (layerBelow == null)
            return null;

        char[] mergedData = new char[_theData.length];

        for (int i = 0; i < _theData.length; i++)
        {
            if ((_theData[i] == Colour.WHITE) || (_theData[i] == Colour.BLACK))
                mergedData[i] = _theData[i];
            else
                mergedData[i] = layerBelow._theData[i];
        }

       return new Layer(_width, _height, mergedData);
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_theData);
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
            Layer temp = (Layer) obj;

            if (_theData.length != temp._theData.length)
                return false;

            for (int i = 0; i < _theData.length; i++)
            {
                if (_theData[i] != temp._theData[i])
                    return false;
            }
        }

        return true;
    }

    @Override
    public String toString ()
    {
        String str = "";
        int index = 0;

        do
        {
            str += _theData[index];

            index++;

            if (index % _width == 0)
                str += "\n";
        }
        while (index < _theData.length);

        return str;
    }

    public String readableForm ()
    {
        String str = "";
        int index = 0;

        do
        {
            if (_theData[index] == Colour.WHITE)
                str += "#";
            else
            {
                if (_theData[index] == Colour.TRANSPARENT)
                    str += ".";
                else
                    str += " ";
            }
            
            index++;

            if (index % _width == 0)
                str += "\n";
        }
        while (index < _theData.length);

        return str;
    }

    private int _width;
    private int _height;
    private char[] _theData;
}