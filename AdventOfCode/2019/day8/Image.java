import java.util.Vector;
import java.util.Enumeration;

public class Image
{
    public Image (int width, int height, String data)
    {
        _width = width;
        _height = height;
        _layerSize = _width * _height;
        _data = data;

        decodeImageData();
    }

    public Vector<Layer> getLayers ()
    {
        return null;
    }

    private void decodeImageData ()
    {
        char[] pixels = _data.toCharArray();
        int index = 0;

        do
        {
            char[] layerData = new char[_layerSize];

            System.arraycopy(pixels, index, layerData, 0, layerData.length);

            _layers.add(new Layer(_width, _height, layerData));

            index += _layerSize;
        }
        while (index < _data.length());
    }

    @Override
    public String toString ()
    {
        String str = "";
        int index = 1;
        Enumeration<Layer> iter = _layers.elements();

        while (iter.hasMoreElements())
        {
            Layer layer = iter.nextElement();

            str += "\nLayer "+index+"\n"+layer.toString();
            index++;
        }

        return str;
    }

    private int _width;
    private int _height;
    private int _layerSize;
    private String _data;
    Vector<Layer> _layers = new Vector<Layer>();
}