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

    public final int numberOfLayers ()
    {
        return _layers.size();
    }

    public final Vector<Layer> getLayers ()
    {
        return _layers;
    }

    public final Layer getRenderedLayer ()
    {
        Enumeration<Layer> iter = _layers.elements();
        Layer currentLayer = iter.nextElement();

        if (currentLayer == null)
        {
            System.out.println("No first layer!");

            return null;
        }

        while (iter.hasMoreElements())
        {
            Layer nextLayer = iter.nextElement();

            if (nextLayer == null)
            {
                System.out.println("Uneven number of layers!");
            }
            else
            {
                currentLayer = currentLayer.merge(nextLayer);
            }
        }

        return currentLayer;
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