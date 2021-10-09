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
            _theComputer.executeUntilInput();

            LinkedList<String> outputs = _theComputer.getOutputs();
            String theOutput = Util.outputToString(outputs);

            System.out.println(theOutput);
            System.out.println(Commands.getCommands());

            String input = "";

	        try
            {
		        InputStreamReader isReader = new InputStreamReader(System.in);
		        BufferedReader bufferedReader = new BufferedReader(isReader);

		        input = bufferedReader.readLine();
	        }
            catch (IOException ex)
            {
		        ex.printStackTrace();
	        }

            int option = Integer.parseInt(input);

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