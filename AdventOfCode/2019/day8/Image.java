public class Image
{
    public Image (int width, int height, String data)
    {
        _width = width;
        _height = height;
        _data = data;

        decodeImageData();
    }

    public Vector<Layer> getLayers ()
    {
        return null;
    }

    private void decodeImageData ()
    {

    }
    
    private int _width;
    private int _height;
    private String _data;
}