public class Grid
{
    public static final int DEFAULT_WIDTH = 5;
    public static final int DEFAULT_HEIGHT = 5;

    public Grid (String fileName)
    {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH, fileName);
    }

    public Grid (int height, int width, String fileName)
    {
        _height = height;
        _width = width;

    }

    private boolean loadWorld (String fileName)
    {
        boolean loaded = false;

        return loaded;
    }
    
    private Tile[][] _theWorld;
    private int _height;
    private int _width;
}