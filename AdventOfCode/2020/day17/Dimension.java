import java.io.*;
import java.util.*;

public class Dimension
{
    public World (String dataFile, boolean debug)
    {
        _debug = debug;

        loadWorld(dataFile);
    }

    private void loadWorld (String inputFile)
    {
        BufferedReader reader = null;
        int h = 0;

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                if (_debug)
                    System.out.println("Loaded line: "+line);
                    /*
                for (int i = 0; i < line.length(); i++)
                {
                    if (CubeId.BUG == line.charAt(i))
                    {
                        if (_debug)
                            System.out.println("Bug at "+i+" "+h);

                        _theWorld[GridData.DEFAULT_LEVELS].addBug(new ThreeDPoint(i, h, 0));
                    }
                }*/

                h++;
            }
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

    private boolean _debug;
}