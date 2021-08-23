import java.io.*;
import java.util.*;

public class Dealer {
    public Dealer(String commandFile, boolean debug) {
        _commands = readCommands(commandFile);
        _debug = debug;
    }

    private final Vector<String> readCommands(String inputFile) {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<String> commands = new Vector<String>();

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null) {
                if (_debug)
                    System.out.println("Read command: " + line);

                commands.add(line);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Throwable ex) {
            }
        }

        return commands;
    }

    private Vector<String> _commands;
    private boolean _debug;
}