import java.util.*;
import java.io.*;

public class Map
{
    public  Map (String fileToLoad)
    {
        _theMap = new Vector<MapEntry>();

        if (!loadData(fileToLoad))
            System.out.println("Error in loading data file: "+fileToLoad);
    }

    public final int getHeight ()
    {
        return _height;
    }

    public final int getWidth ()
    {
        return _width;
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
                    if (asChar[i] == Asteroid.ASTEROID)
                    {
                        _theMap.add(new Asteroid(_height, i));
                    }
                    else
                    {
                        if (asChar[i] == Empty.EMPTY)
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
        int xAxis = 0;
        Enumeration<MapEntry> iter = _theMap.elements();
        String str = "";

        while (iter.hasMoreElements())
        {
            if (xAxis < _width)
                xAxis++;
            else
            {
                str += "\n";
                xAxis = 0;
            }

            str += iter.nextElement().toString();
        }

        return str;
    }

    private Vector<MapEntry> _theMap;
    private int _height = 0;
    private int _width = 0;
}