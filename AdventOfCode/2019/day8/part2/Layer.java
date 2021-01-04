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
            switch (_theData[i])  // work off this layer's colour ...
            {
                case Colour.BLACK:
                {
                    /*
                     * No need to look at the merging colour. Black
                     * overrides white or transparent.
                     */

                    mergedData[i] = Colour.BLACK;
                }
                break;
                case Colour.WHITE:
                {
                    mergedData[i] = Colour.WHITE;
                }
                break;
                case Colour.TRANSPARENT:
                {
                    mergedData[i] = layerBelow._theData[i];
                }
                break;
                default:
                {
                    System.out.println("Error - unknown pixel coloud: "+_theData[i]);

                    mergedData[i] = Colour.BLACK;  // make it black
                }
                break;
            }
        }

       return new Layer(_width, _height, mergedData);
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