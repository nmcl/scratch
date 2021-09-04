import java.util.*;

public class NIC
{
    public static final String INSTRUCTIONS = "instructions.txt";

    public static final long DESTINATION_ADDRESS = 255;

    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-help]");

                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
        }

        Network theNewtork = new Network(debug, INSTRUCTIONS);
        Packet thePacket = theNewtork.getFirstPacket(DESTINATION_ADDRESS);

        System.out.println("Y value of first packet sent to address "+DESTINATION_ADDRESS+" is "+thePacket.getY());
    }
}