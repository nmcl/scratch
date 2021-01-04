public class Verifier
{
    public static final String EXAMPLE_DATA = "123456789012";
    public static final int EXAMPLE_WIDTH = 3;
    public static final int EXAMPLE_HEIGHT = 2;

    public Verifier (boolean debug)
    {
        _theImage = new Image(EXAMPLE_WIDTH, EXAMPLE_HEIGHT, EXAMPLE_DATA);
        _debug = debug;

        if (_debug)
            System.out.println("Created image:\n"+_theImage);
    }

    public final boolean verify ()
    {
        if (_theImage.numberOfLayers() == 2)
            return true;
        else
            return false;
    }

    private Image _theImage;
    private boolean _debug;
}