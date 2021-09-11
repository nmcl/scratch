public class Grid
{
    public static final int DEFAULT_WIDTH = 5;
    public static final int DEFAULT_HEIGHT = 5;

    public Grid ()
    {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    public Grid (int height, int width)
    {
        _height = height;
        _width = width;
    }

    private Tile[][] _theWorld;
    private int _height;
    private int _width;
}