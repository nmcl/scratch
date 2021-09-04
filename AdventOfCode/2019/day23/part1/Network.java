import java.util.*;

// network of machines.

public class Network
{
    public static final int NETWORK_SIZE = 50;

    public Network (boolean debug, String fileName)
    {
        _theNetwork = new Intcode[NETWORK_SIZE];
        _debug = debug;

        for (int i = 0; i < NETWORK_SIZE; i++)
            _theNetwork[i] = new Intcode(IntcodeUtil.readValues(fileName), ""+i, debug);
    }

    public final Packet getFirstPacket (long destination)
    {
        Packet toReturn = null;

        do
        {
            for (int i = 0; (i < NETWORK_SIZE) && (toReturn == null); i++)
            {
                _theNetwork[i].executeUntilInput();

                if (_theNetwork[i].waitingForInput())
                {
                    _theNetwork[i].setInput(Packet.EMPTY_PACKET);
                    _theNetwork[i].executeUntilInput();
                }
            
                LinkedList<String> outputs = new LinkedList<String>(_theNetwork[i].getOutputs());

                while (!outputs.isEmpty())
                {
                    Packet thePacket = new Packet(outputs);
                
                    if (_debug)
                        System.out.println("Packet received from computer "+i+" is "+thePacket);

                    if (thePacket.getAddress() == destination)
                        toReturn = thePacket;
                    else
                    {
                        if (thePacket.getAddress() < NETWORK_SIZE)
                            _theNetwork[(int) thePacket.getAddress()].setInputs(""+thePacket.getX(), ""+thePacket.getY());
                    }
                }
            }
        } while (toReturn == null);

        return toReturn;
    }

    private Intcode[] _theNetwork;
    private boolean _debug;
}