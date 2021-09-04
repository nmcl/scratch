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

    public final long getRepeatY ()
    {
        boolean found = false;
        NAT theNAT = new NAT();
        long computerZeroY = -1;

        do
        {
            for (int i = 0; (i < NETWORK_SIZE) && (!found); i++)
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

                    if (thePacket.getAddress() == NAT.ADDRESS)
                        theNAT.setPacket(thePacket);
                    else
                    {
                        if (thePacket.getAddress() < NETWORK_SIZE)
                            _theNetwork[(int) thePacket.getAddress()].setInputs(""+thePacket.getX(), ""+thePacket.getY());
                    }
                }
            }

            if (isIdle())
            {
                if (theNAT.getPacket().getY() == computerZeroY)
                    found = true;
                else
                {
                    _theNetwork[0].setInputs(""+theNAT.getPacket().getX(), ""+theNAT.getPacket().getY());
                    computerZeroY = theNAT.getPacket().getY();
                }
            }   

        } while (!found);

        return computerZeroY;
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

    private final boolean isIdle ()
    {
        for (int i = 0; i < _theNetwork.length; i++)
        {
            if (!_theNetwork[i].waitingForInput())
                return false;
        }

        return true;
    }

    private Intcode[] _theNetwork;
    private boolean _debug;
}