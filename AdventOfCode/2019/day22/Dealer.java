import java.io.*;
import java.util.*;

public class Dealer
{
    public Dealer(String commandFile, boolean debug)
    {
        _commands = new Vector<String>();
        _debug = debug;
        
        readCommands(commandFile);
    }

    public boolean dealCards (int sizeOfDeck)
    {
        Deck theDeck = new Deck(sizeOfDeck, _debug);
        Enumeration<String> iter = _commands.elements();

        while (iter.hasMoreElements())
        {
            String command = iter.nextElement();

            if (command.startsWith(Commands.CUT))
                cut(command, theDeck);
            else
            {
                if (command.startsWith(Commands.DEAL_INTO))
                    dealInto(command, theDeck);
                else
                {
                    if (command.startsWith(Commands.DEAL_WITH_INCREMENT))
                        dealWithIncrement(command, theDeck);
                    else
                    {
                        System.out.println("Unknown command: "+command);

                        return false;
                    }
                }
            }
        }
        return false;
    }

    private final void readCommands(String inputFile)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;

        try
        {       
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;
            
            do
            {
                line = reader.readLine();
                
                if (_debug)
                    System.out.println("Read command: " + line);

                if (line != null)
                    _commands.add(line);
            }
            while (line != null);
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }
    }

    private Vector<String> _commands;
    private boolean _debug;
}