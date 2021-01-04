public class Verifier
{
    public static final String EXAMPLE_DATA = "123456789012";
    public static final int EXAMPLE_WIDTH = 3;
    public static final int EXAMPLE_HEIGHT = 2;

    public Verifier ()
    {
        _theImage = new Image(EXAMPLE_WIDTH, EXAMPLE_HEIGHT, EXAMPLE_DATA);
    }

    public final boolean verify ()
    {
        return true;
    }

    private Image _theImage;
}