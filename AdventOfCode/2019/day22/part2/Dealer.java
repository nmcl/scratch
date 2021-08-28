import java.io.*;
import java.util.*;
import java.math.*;

public class Dealer
{
    public Dealer (String commandFile, boolean debug)
    {
        _commands = new Vector<String>();
        _debug = debug;
        
        readCommands(commandFile);
    }

    public BigInteger dealCards (BigInteger sizeOfDeck, BigInteger shuffledTimes, BigInteger position)
    {
        BigInteger[] formula = new BigInteger[] {BigInteger.valueOf(1), BigInteger.valueOf(0)};
        Collections.reverse(_commands);

        _numberOfCards = sizeOfDeck;

        Enumeration<String> iter = _commands.elements();

        while (iter.hasMoreElements())
        {
            String command = iter.nextElement();

            if (command.startsWith(Commands.CUT))
                formula[1] = cut(command, formula[1]);
            else
            {
                if (command.startsWith(Commands.DEAL_INTO))
                    dealInto(command, formula);
                else
                {
                    if (command.startsWith(Commands.DEAL_WITH_INCREMENT))
                        dealWithIncrement(command, formula);
                    else
                    {
                        System.out.println("Unknown command: "+command);

                        return null;
                    }
                }
            }
        }

        BigInteger modded = formula[0].modPow(shuffledTimes, _numberOfCards);

        return modded.multiply(position)
                .add(formula[1].multiply(modded.add(_numberOfCards).subtract(BigInteger.valueOf(1)))
                        .multiply(formula[0].subtract(BigInteger.valueOf(1)).modPow(_numberOfCards.subtract(BigInteger.valueOf(2)), _numberOfCards))
                )
                .mod(_numberOfCards);
    }

    private BigInteger cut (String command, BigInteger func)
    {
        if (_debug)
            System.out.println("Command: "+command);

        return func.add(argument(command, Commands.CUT));
    }

    private void dealInto (String command, BigInteger[] func)
    {
        if (_debug)
            System.out.println("Command: "+command);

        func[0] = func[0].multiply(BigInteger.valueOf(-1));
        func[1] = func[1].add(BigInteger.valueOf(1)).multiply(BigInteger.valueOf(-1));
    }

    private void dealWithIncrement (String command, BigInteger[] func)
    {
        if (_debug)
            System.out.println("Command: "+command);

        BigInteger mult = argument(command, Commands.DEAL_WITH_INCREMENT).modPow(_numberOfCards.subtract(BigInteger.valueOf(2)), _numberOfCards);

        func[0] = func[0].multiply(mult);
        func[1] = func[1].multiply(mult);
    }

    private final BigInteger argument (String command, String type)
    {
        String paramString = command.substring(type.length()).trim();

        if (_debug)
            System.out.println("Parameter string: "+paramString);

        BigInteger value = BigInteger.valueOf(Long.valueOf(paramString));

        if (_debug)
            System.out.println("Argument: "+value);

        return value;
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
                
                if (_debug && (line != null))
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
    private BigInteger _numberOfCards;
    private boolean _debug;
}