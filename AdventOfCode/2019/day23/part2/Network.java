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
                System.out.println("Computer "+i+" before run queue "+_theNetwork[i].getOutputs().size());

                _theNetwork[i].executeUntilInput();

                System.out.println("Computer "+i+" after run queue "+_theNetwork[i].getOutputs().size());
                System.out.println("Computer "+i+" ran");

                if (_theNetwork[i].waitingForInput())
                {
                    System.out.println("Computer "+i+" after queue "+_theNetwork[i].getOutputs().size());
                    System.out.println("Computer "+i+" standby");

                    _theNetwork[i].setInput(Packet.EMPTY_PACKET);
                    _theNetwork[i].executeUntilInput();
                    System.out.println("Computer "+i+" after second queue "+_theNetwork[i].getOutputs().size());
                }

                LinkedList<String> outputs = new LinkedList<String>(_theNetwork[i].getOutputs());

                System.out.println("Computer "+i+" output size "+outputs.size());

                while (!outputs.isEmpty())
                {
                    Packet thePacket = new Packet(outputs);
                
                    System.out.println("Computer "+i+" output size now "+outputs.size());

                    if (_debug)
                        System.out.println("Packet received from computer "+i+" is "+thePacket);

                    if (thePacket.getAddress() == NAT.ADDRESS)
                    {
                        if (_debug)
                            System.out.println("Packet "+thePacket);

                        theNAT.setPacket(thePacket);
                    }
                    else
                    {
                        if (thePacket.getAddress() < NETWORK_SIZE)
                            _theNetwork[(int) thePacket.getAddress()].setInputs(""+thePacket.getX(), ""+thePacket.getY());
                    }
                }
            }

            if (isIdle())
            {
                //if (_debug)
                    System.out.println("Comparing "+theNAT.getPacket().getY()+" and "+computerZeroY);

                if ((!theNAT.isEmpty()) && (theNAT.getPacket().getY() == computerZeroY))
                    found = true;

                _theNetwork[0].setInputs(""+theNAT.getPacket().getX(), ""+theNAT.getPacket().getY());
                computerZeroY = theNAT.getPacket().getY();
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
            System.out.println("Computer "+i+" queue "+_theNetwork[i].getInputs().size());

            if (!_theNetwork[i].getInputs().isEmpty())
                return false;
        }

        return true;
    }

    private Intcode[] _theNetwork;
    private boolean _debug;
}