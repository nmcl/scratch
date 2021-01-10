import java.util.*;
import java.io.*;

public class Plotter
{
    public static final char ASTEROID = '#';
    public static final char EMPTY = '.';

    public Plotter (String fileToLoad)
    {
        if (!loadData(fileToLoad))
            System.out.println("Error in loading data file: "+fileToLoad);

        _theMap = new Vector<MapEntry>();
    }

    private final boolean loadData (String file)
    {
        BufferedReader reader = null;
        boolean valid = true;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                char[] asChar = line.toCharArray();
                
                if (_width == 0)
                    _width = asChar.length;

                for (int i = 0; i < _width; i++)
                {
                    if (asChar[i] == Plotter.ASTEROID)
                    {
                        _theMap.add(new Asteroid(_height, i));
                    }
                    else
                    {
                        if (asChar[i] == Plotter.EMPTY)
                        {
                            _theMap.add(new Empty(_height, i));
                        }
                        else
                        {
                            System.out.println("Unknown character in data file: "+asChar[i]);

                            valid = false;
                        }
                    }
                }

                _height++;

            }
        }
        catch (Throwable ex)
        {
            valid = false;

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

        return valid;
    }

    @Override
    public String toString ()
    {
        return "";
    }

    private Vector<MapEntry> _theMap;
    private int _height = 0;
    private int _width = 0;
}