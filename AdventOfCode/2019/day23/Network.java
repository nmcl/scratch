// network of machines.

public class Network
{
    public static final int NETWORK_SIZE = 50;

    public Network (boolean debug, String fileName)
    {
        _theNetwork = new Intcode[NETWORK_SIZE];
        _debug = debug;

        for (int i = 0; i < NETWORK_SIZE; i++)
            _theNetwork[i] = new Intcode(IntcodeUtil.readValues(fileName), debug);
    }

    private Intcode[] _theNetwork;
    private boolean _debug;
}