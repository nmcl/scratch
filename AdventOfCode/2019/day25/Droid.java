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

    }

    public void stepTraverse ()
    {
        boolean finished = false;

        while (!finished)
        {
            System.out.println("here");

            _theComputer.executeUntilInput();

            LinkedList<String> outputs = _theComputer.getOutputs();
            String theOutput = Util.outputToString(outputs);

            System.out.println(theOutput);
            System.out.println(Commands.getCommands());

            String input = Util.getInput();

            int option;
            
            try
            {
                option = Integer.parseInt(input);
            }
            catch (Throwable ex)
            {
                option = 8;  // quit on error
            }

            System.out.println("option "+option);

            switch (option)
            {
                case 1:
                {
                    _theComputer.setInput(Commands.NORTH+"\n");
                }
                break;
                case 2:
                {
                    _theComputer.setInput(Commands.SOUTH+"\n");
                }
                break;
                case 3:
                {
                    _theComputer.setInput(Commands.EAST+"\n");
                }
                break;
                case 4:
                {
                    _theComputer.setInput(Commands.WEST+"\n");
                }
                break;
                case 5:
                {
                    System.out.println("Which item to pick up?");

                    String item = Util.getInput();

                    _theComputer.setInputs(Util.stringToInput(Commands.TAKE, item.trim()));

		            System.out.println("Picked up: " + item);
                }
                break;
                case 6:
                {
                    System.out.println("Which item to drop?");

                    String item = Util.getInput();

                    _theComputer.setInputs(Util.stringToInput(Commands.DROP, item.trim()));

		            System.out.println("Dropped: " + item);
                }
                break;
                case 7:
                {
                    _theComputer.setInput(Commands.INVENTORY+"\n");
                }
                break;
                case 8:
                {
                    finished = true;
                }
                break;
                default:
                {
                    System.out.println("Unrecognised input: "+option);
                }
            }
        }
    }

    private Intcode _theComputer;
    private boolean _debug;
}