public class Layer
{
    public Layer (int width, int height, char[] data)
    {
        _width = width;
        _height = height;
        _theData = data;
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

    private int _width;
    private int _height;
    private char[] _theData;
}