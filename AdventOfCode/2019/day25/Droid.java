import java.util.*;
import java.io.*;

public class Droid
{
    public Droid (Vector<String> values, boolean debug)
    {
        _theComputer = new Intcode(values, debug);
        _debug = debug;
    }

    /*
     * Move around automatically. TBD.
     */

    public void traverse ()
    {
        System.out.println("Auto option not yet implemented! Trying manual option.");

        stepTraverse();  // TODO
    }

    public void stepTraverse ()
    {
        boolean finished = false;

        while (!finished)
        {
            _theComputer.executeUntilInput();

            LinkedList<String> outputs = _theComputer.consumeOutputs();
            String theOutput = Util.outputToString(outputs);

            System.out.println(theOutput);
            System.out.println(Commands.getCommands());

            String input = Util.getInput();

            switch (input)
            {
                case Commands.NORTH:
                {
                    _theComputer.setInputs(Util.stringToInput(Commands.NORTH, "\n"));
                }
                break;
                case Commands.SOUTH:
                {
                    _theComputer.setInputs(Util.stringToInput(Commands.SOUTH, "\n"));
                }
                break;
                case Commands.EAST:
                {
                    _theComputer.setInputs(Util.stringToInput(Commands.EAST, "\n"));
                }
                break;
                case Commands.WEST:
                {
                    _theComputer.setInputs(Util.stringToInput(Commands.WEST, "\n"));
                }
                break;
                case Commands.TAKE:
                {
                    System.out.println("\nWhich item to take?");

                    String item = Util.getInput();

                    _theComputer.setInputs(Util.stringToInput(Commands.TAKE+" ", item.trim()+"\n"));

		            System.out.println("\nTaken: " + item);
                }
                break;
                case Commands.DROP:
                {
                    System.out.println("\nWhich item to drop?");

                    String item = Util.getInput();

                    _theComputer.setInputs(Util.stringToInput(Commands.DROP+" ", item.trim()+"\n"));

		            System.out.println("\nDropped: " + item);
                }
                break;
                case Commands.INVENTORY:
                {
                    _theComputer.setInputs(Util.stringToInput(Commands.INVENTORY, "\n"));
                }
                break;
                case Commands.QUIT:
                {
                    finished = true;
                }
                break;
                default:
                {
                    System.out.println("Unrecognised input: "+input);
                }
            }
        }
    }

    private Intcode _theComputer;
    private boolean _debug;
}