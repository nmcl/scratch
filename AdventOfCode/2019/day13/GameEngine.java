import java.util.function.IntConsumer;

public class GameEngine
{
    public GameEngine (boolean debug)
    {
        _debug = debug;
    }

    private Intcode _computer;
    private boolean _debug;
}